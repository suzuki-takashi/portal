package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Pwreissueinfo;
import com.portal.z.common.domain.repository.PwreissueinfoMapper;

/**
 * PwreissueinfoService
 *
 */
@Transactional
@Service
public class PwreissueinfoService {

    @Autowired
    PwreissueinfoMapper pwreissueinfoMapper;

    /**
     * insert用メソッド.
     * 
     * @param pwreissueinfo pwreissueinfo
     * @return insertOne
     */
    public boolean insert(Pwreissueinfo pwreissueinfo) {
        return pwreissueinfoMapper.insertOne(pwreissueinfo);
    }

    /**
     * １件取得用メソッド.
     * 
     * @param token token
     * @return selectOne
     */
    public Pwreissueinfo selectOne(String token) {
        // selectOne実行
        return pwreissueinfoMapper.selectOne(token);
    }

    /**
     * １件削除用メソッド.
     * 
     * @param token
     * @return deleteOne
     */
    public boolean deleteOne(String token) {
        return pwreissueinfoMapper.deleteOne(token);
    }
    
    /**
     * １件取得用メソッド.
     * 
     * @param token token
     * @return selectOne
     */
    public Pwreissueinfo selectOneByUserid(String user_id) {
        // selectOne実行
        return pwreissueinfoMapper.selectOneByUserid(user_id);
    }
    
    /**
     * １件更新用メソッド.
     * 
     * @param user_id     user_id
     * @param token       token
     * @param secret      secret
     * @param rawPassword rawPassword
     * @return resetPassword
     */
    public boolean resetPassword(String user_id, String token, String secret, String rawPassword) {
        // resetPassword実行
        return pwreissueinfoMapper.resetPassword(user_id, token, secret, rawPassword);
    }
}
