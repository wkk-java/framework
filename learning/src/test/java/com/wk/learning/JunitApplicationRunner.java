package com.wk.learning;

import io.seata.spring.boot.autoconfigure.SeataAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableAutoConfiguration(exclude = SeataAutoConfiguration.class)
public class JunitApplicationRunner {
    @BeforeClass
    public static void processBeforClass() {
        log.info("processBeforClass开始执行");
        //从系统环境变量中获取密码
        System.setProperty("spring.profiles.active", "dev");
        //mock数据
    }

    @AfterClass
    public static void processAfterClass() {
        log.info("processAfterClass开始执行");
        //销毁数据
    }

    public static void main(String[] args) throws Exception {
        System.out.println(System.getenv("SECURITY_USER_PASSWORD"));
    }
}
