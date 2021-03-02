package com.wk.learning;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: vince
 * create at: 2021/2/24 下午4:26
 * @description: junit测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JunitApplicationRunner {
    @BeforeClass
    public static void processBeforClass() {
        log.info("processBeforClass开始执行");
//        System.setProperty("SECURITY_USER_PASSWORD", "");
        System.setProperty("spring.profiles.active", "dev");
        //mock数据
    }

    @AfterClass
    public static void processAfterClass() {
        log.info("processAfterClass开始执行");
        //销毁数据
    }
}
