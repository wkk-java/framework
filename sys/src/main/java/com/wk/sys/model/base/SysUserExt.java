package com.wk.sys.model.base;

import lombok.Data;

import java.util.List;

/**
 * @author: vince
 * create at: 2020/7/4 18:44
 * @description: 用户信息扩展类
 */
@Data
public class SysUserExt extends SysUser {
    private List<SysRole> sysRoles;
    private List<String> sysRoleNames;
}
