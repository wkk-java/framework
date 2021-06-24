package com.wk.learning.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public final static String QUEUE_USER = "queue.user";

    public final static String QUEUE_USERGROUP = "queue.user.group";

    public final static String EXCHANGE_USER = "exchange.user";

    public final static String QUEUE_USER_DEAD_LETTER = "queue.user.dead_letter";
    public final static String EXCHANGE_USER_DEAD_LETTER = "exchange.user.dead_letter";

    // 声明死信队列
    @Bean(name = QUEUE_USER_DEAD_LETTER)
    public Queue userDeadLetterQueue(){
        return new Queue(QUEUE_USER_DEAD_LETTER);
    }

    @Bean(name = QUEUE_USER)
    public Queue userQueue(){
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", EXCHANGE_USER_DEAD_LETTER);
//       x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", QUEUE_USER);
        return QueueBuilder.durable(QUEUE_USER).withArguments(args).build();
    }

//    @Bean(name = QUEUE_USER)
//    public Queue userQueue() {
//        return new Queue(QUEUE_USER, true, false, false);
//    }

    @Bean(name = QUEUE_USERGROUP)
    public Queue userGroupQueue() {
        return new Queue(QUEUE_USERGROUP, true, false, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_USER);
    }

    @Bean
    public Binding bindingUserQueueExchange(@Qualifier(value = QUEUE_USER) Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_USER);
    }

    @Bean
    public Binding bindingUserGroupQueueExchange(@Qualifier(value = QUEUE_USERGROUP) Queue queue, TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(queue).to(exchange)
                .with(QUEUE_USERGROUP);
//                .with(QUEUE_USER + ".#");
    }

    @Bean
    public Binding bindingUserDeadLetterQueueExchange(@Qualifier(value = QUEUE_USER_DEAD_LETTER) Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_USER);
    }
}