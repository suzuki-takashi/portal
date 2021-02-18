package com.portal.z.pwreissue.controller;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.portal.z.common.domain.model.Pwreissueinfo;
import com.portal.z.common.domain.service.PwreissueinfoService;
import com.portal.z.pwreissue.domain.model.PwreissueForm;
import com.portal.z.pwreissue.domain.service.PwreissueService;

/**
 * パスワード再設定画面用Controller
 *
 */
@Controller
public class PwreissueController {
    
//    @Autowired
//    Pwreissueinfo pwreissueinfo;
    
    @Autowired
    PwreissueService pwreissueService;
    
    @Autowired
    PwreissueinfoService pwreissueinfoService;

    // パスワード暗号化
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 画面表示.
     * 
     * @param form  form
     * @param model model
     * @return z/createReissueInfoForm
     */
    @GetMapping("/pwreissue")
    public String getPwreissue(@ModelAttribute PwreissueForm form, Model model) {

        return "z/pwreissue";
    }

    /**
     * パスワード再設定情報登録
     * 
     * @param model         model
     * @param form          form
     * @param bindingResult
     * @return パスワード再設定画面
     * @throws ParseException ParseException
     */
    @PostMapping("/pwreissue")
    public String postPwreissue(Model model, @ModelAttribute @Validated PwreissueForm form, BindingResult bindingResult)
            throws ParseException {

        // 入力チェックに引っかかった場合、パスワード再設定画面に戻る
        if (bindingResult.hasErrors()) {
            return getPwreissue(form, model);
        }

        // パスワード再設定用情報を登録する。
        String result = pwreissueService.insertPwreissueinfo(form.getUser_id());

        model.addAttribute("result1", "パスワード再設定用のURLを、ご入力いただいたユーザIDに送信しました。");
        model.addAttribute("result2", "このURLにアクセスし、以下の仮パスワードでログインしてください。");
        model.addAttribute("result3", "仮パスワード：" + result);

        // パスワード再設定用情報を検索する。
        Pwreissueinfo pwreissueinfo = pwreissueinfoService.selectOneByUserid(form.getUser_id());

        // パスワード再設定画面のURLを生成
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080/");
        uriBuilder.pathSegment("resetpassword").queryParam("form").queryParam("token",
                pwreissueinfo.getToken());
        String passwordResetUrl = uriBuilder.build().encode().toUriString();

        System.out.println("test:" + passwordResetUrl);

        // パスワード再設定画面のURLをメールで送信する。

        return getPwreissue(form, model);

    }
}
