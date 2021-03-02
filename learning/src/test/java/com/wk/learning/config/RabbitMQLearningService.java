package com.wk.learning.config;

import com.alibaba.fastjson.JSONObject;
import com.wk.order.entity.base.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQLearningService {

    //监听器监听指定的Queue
    @RabbitListener(queues = RabbitMQConfig.TOPIC_USER_QUEUE)
    public void testUserListener(OrderInfo message) {
        log.info("user message:{}", JSONObject.toJSONString(message));
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_USERGROUP_QUEUE)
    public void testUserGroupListener(OrderInfo message) {
        log.info("user_group message:{}", JSONObject.toJSONString(message));
    }
}