package com.wk.learning.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public final static String QUEUE_USER = "queue.user";

    public final static String QUEUE_USERGROUP = "queue.user.group";

    public final static String EXCHANGE_USER_TOPIC = "exchange.user";
    public final static String EXCHANGE_USER_DIREICT = "exchange.user.direict";
    public final static String EXCHANGE_USER_FANOUT = "exchange.user.fanout";

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
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_USER_TOPIC);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_USER_DIREICT);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE_USER_FANOUT);
    }


    @Bean
    public Binding bindingUserQueueDirectExchange(@Qualifier(value = QUEUE_USER) Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_USER);
    }

    @Bean
    public Binding bindingUserQueueExchange(@Qualifier(value = QUEUE_USER) Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_USER);
    }

    @Bean
    public Binding bindingUserQueueFanoutExchange(@Qualifier(value = QUEUE_USER) Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingUserGroupQueueFanoutExchange(@Qualifier(value = QUEUE_USERGROUP) Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingUserGroupQueueExchange(@Qualifier(value = QUEUE_USERGROUP) Queue queue,  TopicExchange exchange) {
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