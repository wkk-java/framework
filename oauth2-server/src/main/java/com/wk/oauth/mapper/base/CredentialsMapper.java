package com.wk.oauth.mapper.base;

import com.wk.oauth.model.base.Credentials;
import com.wk.oauth.model.base.CredentialsExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CredentialsMapper {
    long countByExample(CredentialsExample example);

    int deleteByExample(CredentialsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Credentials record);

    int insertSelective(Credentials record);

    List<Credentials> selectByExample(CredentialsExample example);

    Credentials selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Credentials record, @Param("example") CredentialsExample example);

    int updateByExample(@Param("record") Credentials record, @Param("example") CredentialsExample example);

    int updateByPrimaryKeySelective(Credentials record);

    int updateByPrimaryKey(Credentials record);
}