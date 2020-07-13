package com.wk.oauth.mapper.base;

import com.wk.oauth.model.base.CredentialsAuthorities;
import com.wk.oauth.model.base.CredentialsAuthoritiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CredentialsAuthoritiesMapper {
    long countByExample(CredentialsAuthoritiesExample example);

    int deleteByExample(CredentialsAuthoritiesExample example);

    int insert(CredentialsAuthorities record);

    int insertSelective(CredentialsAuthorities record);

    List<CredentialsAuthorities> selectByExample(CredentialsAuthoritiesExample example);

    int updateByExampleSelective(@Param("record") CredentialsAuthorities record, @Param("example") CredentialsAuthoritiesExample example);

    int updateByExample(@Param("record") CredentialsAuthorities record, @Param("example") CredentialsAuthoritiesExample example);
}