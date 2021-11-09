package com.wk.learning.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.wk.learning.config.RabbitMQConfig;
import com.wk.order.entity.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class RabbitMQLearningService {

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.QUEUE_USER)
    public void testUserListener(Message message, Channel channel) throws IOException {
        log.info("收到user消息:{}", JSONObject.toJSONString(message));
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.QUEUE_USERGROUP)
    public void testUserGroupListener(Message message, Channel channel) throws IOException {
        log.info("user_group message:{}", JSONObject.toJSONString(message));
        String messageId = message.getMessageProperties().getMessageId();
        String txt = new String(message.getBody());
        log.info("消息编号:{},消息消费：{}", messageId, txt);
        try {
            OrderInfo orderInfo = JSONObject.parseObject(txt, OrderInfo.class);
            if (orderInfo == null || "o_1".equals(orderInfo.getId())) {
                throw new Exception("网络中断,调用超时!");
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            log.info("ack 完毕消息:{}", txt);
        } catch (Exception ex) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            log.error("ack 失败消息:{}", txt);
        }
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.QUEUE_USER_DEAD_LETTER)
    public void receiveDeadLetter(Message message, Channel channel) throws IOException, InterruptedException {
        log.info("收到死信消息:{}", new String(message.getBody()));
        Thread.sleep(5000l);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}