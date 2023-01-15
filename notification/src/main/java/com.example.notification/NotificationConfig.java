package com.example.notification;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    @Value("${rabbitmq.queues.notificationFraud}")
    private String notificationQueueFraud;

    @Value("${rabbitmq.routing-keys.internal-notificationFraud}")
    private String internalNotificationRoutingKeyFraud;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(internalExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationQueue);
    }

    @Bean
    public Binding internalNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(internalNotificationRoutingKey);
    }

    @Bean
    public Queue notificationQueueFraud() {
        return new Queue(notificationQueueFraud);
    }

    @Bean
    public Binding internalNotificationFraudBinding() {
        return BindingBuilder
                .bind(notificationQueueFraud())
                .to(internalTopicExchange())
                .with(internalNotificationRoutingKeyFraud);
    }

}
