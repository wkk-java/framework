package com.wk.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: vince
 * create at: 2020/7/11 02:08
 * @description: test
 */
public class test {
    public static void main(String[] args) {
        String finalSecret = new BCryptPasswordEncoder().encode("123456");
        System.out.println(finalSecret);
    }
}
