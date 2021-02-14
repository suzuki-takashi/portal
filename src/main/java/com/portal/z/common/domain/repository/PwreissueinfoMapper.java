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

}
