package com.portal.z.pwreissue.domain.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portal.z.common.domain.model.Env;
import com.portal.z.common.domain.model.Pwreissueinfo;
import com.portal.z.common.domain.service.EnvService;
import com.portal.z.common.domain.service.PwreissueinfoService;
import com.portal.z.common.domain.util.Constants;

/**
 * パスワード再設定画面用のService
 *
 */
@Transactional
@Service
public class PwreissueService {

    @Autowired
    private PwreissueinfoService pwreissueinfoService;

    @Autowired
    private EnvService envService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     *  パスワード再発行情報に追加する。
     * @param user_id　user_id
     * @return　仮パスワード
     */
    public String insertPwreissueinfo(String user_id) {

        // トークンを生成
        String token = UUID.randomUUID().toString();

        // 仮パスワード条件設定
        List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1));

        // 仮パスワード生成
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String rowSecret = passwordGenerator.generatePassword(10, rules);

        // 仮パスワード暗号化
        String encodeSecret = passwordEncoder.encode(rowSecret);

        // 仮パスワード有効期限の初期値を設定
        int PASS_UPDATE_NXT = Constants.PASS_UPDATE_NXT; // パスワード有効期限月数

        // 環境マスタに登録したパスワード有効期限月数を取得
        Env env = envService.selectIntOne("PASS_UPDATE_NXT");

        if (env != null) {
            PASS_UPDATE_NXT = Integer.parseInt(env.getEnv_txt());
        }

        // 仮パスワード有効期限を計算
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, PASS_UPDATE_NXT); // 月加算
        Date passwordUpdateDate = cal.getTime();

        // パスワード再発行情報インスタンスの生成
        Pwreissueinfo pwreissueinfo = new Pwreissueinfo();

        // パスワード再発行情報クラスに設定
        pwreissueinfo.setToken(token);
        pwreissueinfo.setUser_id(user_id);
        pwreissueinfo.setSecret(encodeSecret);
        pwreissueinfo.setExpirydate(passwordUpdateDate);

        // パスワード再発行情報に追加
        pwreissueinfoService.insert(pwreissueinfo);

        return rowSecret;

    }
}
