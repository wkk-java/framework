package com.wk.sys.controller;

import com.wk.common.exception.BusinessRuntimeException;
import com.wk.common.exception.ExceptionType;
import com.wk.sys.feign.FeignServiceTest;
import com.wk.sys.mapper.base.SysRoleMapper;
import com.wk.sys.mapper.base.SysUserMapper;
import com.wk.sys.mapper.base.SysUserRoleMapper;
import com.wk.sys.model.base.SysRole;
import com.wk.sys.model.base.SysRoleExample;
import com.wk.sys.model.base.SysUser;
import com.wk.sys.model.base.SysUserExample;
import com.wk.sys.model.base.SysUserExt;
import com.wk.sys.model.base.SysUserRole;
import com.wk.sys.model.base.SysUserRoleExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: vince
 * create at: 2019/12/31 18:29
 * @description: 用户控制器
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    private FeignServiceTest feignServiceTest;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @GetMapping(value = "/getInfo")
    public String getAppName() {
        log.info("begin.....");
        return feignServiceTest.getAppName();
    }

    @GetMapping(value = "/find")
    public String find() {
        log.info("begin find.....");
        return feignServiceTest.find();
    }


    @GetMapping(value = "/loadUserByUsername")
    public SysUser loadUserByUsername(@RequestParam("loginName") String loginName) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(loginName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "用户名错误");
        }
        SysUser sysUser = sysUsers.get(0);
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return sysUser;
        }
        List<String> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(roleIdList);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        List<String> sysRoleNames = sysRoles.stream().map(SysRole::getName).collect(Collectors.toList());

        SysUserExt sysUserExt = new SysUserExt();
        BeanUtils.copyProperties(sysUser, sysUserExt);
        sysUserExt.setSysRoles(sysRoles);
        sysUserExt.setSysRoleNames(sysRoleNames);

        return sysUserExt;
    }

    @GetMapping(value = "/getUserInfo")
    public SysUser getUserInfo(@RequestParam("loginName") String loginName, @RequestParam("pwd") String pwd) {
        try{
            Thread.sleep(5000);
        } catch (Exception ex) {
            log.error("错误信息：{0}", ex);
        }
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(loginName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "用户名错误");
        }
        SysUser sysUser = sysUsers.get(0);
        if ("1".equals(sysUser.getIsFirstLoginModifyPasswrod())){
            throw new BusinessRuntimeException(ExceptionType.REMARK, "还没修改密码...");
        }
        if (!pwd.equals(sysUser.getPassword())) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "密码错误");
        }
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectByExample(sysUserRoleExample);
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return sysUser;
        }
        List<String> roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(roleIdList);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        List<String> sysRoleNames = sysRoles.stream().map(SysRole::getName).collect(Collectors.toList());

        SysUserExt sysUserExt = new SysUserExt();
        BeanUtils.copyProperties(sysUser, sysUserExt);
        sysUserExt.setSysRoles(sysRoles);
        sysUserExt.setSysRoleNames(sysRoleNames);

        return sysUserExt;
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody SysUser sysUser) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andLoginNameEqualTo(sysUser.getLoginName());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "用户名错误");
        }

        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setDelFlag(false);
        sysUserMapper.insertSelective(sysUser);
        return "success";
    }

    @PostMapping(value = "/addBatch")
    public String addBatch(@RequestBody List<SysUser> sysUserList) {
        if (sysUserList != null) {
            for (SysUser sysUser : sysUserList) {
                sysUser.setId(UUID.randomUUID().toString());
                sysUser.setDelFlag(false);
                sysUserMapper.insertSelective(sysUser);
            }
        }
        return "success";
    }



    @GetMapping(value = "/getUserList")
    public List<SysUser> getUserList() {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andDelFlagEqualTo(false);
        return sysUserMapper.selectByExample(sysUserExample);
    }


}
