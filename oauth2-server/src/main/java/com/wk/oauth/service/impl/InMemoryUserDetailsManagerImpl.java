//package com.wk.oauth.impl;
//
//import com.wk.oauth.model.OauthSysUser;
//import com.wk.oauth.model.SysUser;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author: vince
// * create at: 2020/7/6 00:07
// * @description:
// */
//@Log4j2
//@Service
//public class InMemoryUserDetailsManagerImpl extends InMemoryUserDetailsManager {
//    //可配置在数据库中查询
//    InMemoryUserDetailsManagerImpl(){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        password 方案一：明文存储，用于测试，不能用于生产
////        String finalPassword = "123456";
////        password 方案二：用 BCrypt 对密码编码
////        String finalPassword = bCryptPasswordEncoder.encode("123456");
//        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
//        String finalPassword = "{bcrypt}$2a$10$tMSDLeQjFehV8Z1BxoU3quISNyLXXMDI8pdA3RKdwqv1ujuZ6AAQG";
//        List<OauthSysUser> userList = SysUser.getUserList();
//        userList.stream().forEach(user -> {
//            List<String> authorities = user.getAuthorities();
//            User.UserBuilder password = User.withUsername(user.getUsername()).password(user.getPassword());
//            if (authorities != null && !authorities.isEmpty()) {
//                authorities.forEach(str -> password.authorities(str));
//            }
//            super.createUser(password.build());
//        });
////        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
//    }
//}
