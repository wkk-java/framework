package com.wk.learning.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.wk.learning.JunitApplicationRunner;
import com.wk.learning.config.RabbitMQConfig;
import com.wk.order.entity.ext.OrderInfoExt;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RabbitMQLearning extends JunitApplicationRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimple() throws Exception {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            Object result = rabbitTemplate.convertSendAndReceive(RabbitMQConfig.EXCHANGE_USER_DIREICT, RabbitMQConfig.QUEUE_USER, JSONObject.toJSONString(OrderInfoExt.getTestBean("o_" + i)));
            log.info("send result:{}", result);
        }
        Thread.sleep(10000);
    }

}
