package com.portal.z.common.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import com.portal.z.common.domain.model.Pwreissueinfo;

/**
 * PwreissueinfoMapper
 *
 */
@Mapper
public interface PwreissueinfoMapper {

    /**
     * 登録用メソッド
     * 
     * @param Pwreissueinfo Pwreissueinfo
     * @return insertOne
     */
    public boolean insertOne(Pwreissueinfo Pwreissueinfo);

    /**
     * １件検索用メソッド
     * 
     * @param token token
     * @return selectOne
     */
    public Pwreissueinfo selectOne(String token);

    /**
     * １件削除用メソッド
     * 
     * @param token token
     * @return deleteOne
     */
    public boolean deleteOne(String token);

    /**
     * １件検索用メソッド
     * 
     * @param user_id user_id
     * @return selectOneByUserid
     */
    public Pwreissueinfo selectOneByUserid(String user_id);
    
    /**
     * １件更新用メソッド
     * 
     * @param user_id     user_id
     * @param token       token
     * @param secret      secret
     * @param rawPassword rawPassword
     * @return resetPassword
     */
    public boolean resetPassword(String user_id, String token, String secret, String rawPassword);

}
