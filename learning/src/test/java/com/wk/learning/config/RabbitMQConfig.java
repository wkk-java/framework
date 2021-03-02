package com.wk.learning.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public final static String TOPIC_USER_QUEUE = "topic.user.queue";

    public final static String TOPIC_USERGROUP_QUEUE = "topic.user.group.queue";

    public final static String TOPIC_EXCHANGE = "topic.user.exchange";

    @Bean(name = TOPIC_USER_QUEUE)
    public Queue initUserQueue() {
        return new Queue(TOPIC_USER_QUEUE, true, false, false);
    }

    @Bean(name = TOPIC_USERGROUP_QUEUE)
    public Queue initGroupQueue() {
        return new Queue(TOPIC_USERGROUP_QUEUE, true, false, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier(value = TOPIC_USER_QUEUE) Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.user.queue");
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier(value = TOPIC_USERGROUP_QUEUE) Queue queue, TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(queue).to(exchange).with("topic.user.#");
    }
}