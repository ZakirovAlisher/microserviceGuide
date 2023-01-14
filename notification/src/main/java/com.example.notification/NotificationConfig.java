package com.example.notification;

import com.example.clients.notification.NotificationConstants;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class NotificationConfig {

   private final NotificationConstants notificationConstants;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(notificationConstants.getInternalExchange());
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationConstants.getNotificationQueue());
    }

    @Bean
    public Binding internalNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(notificationConstants.getInternalNotificationRoutingKey());
    }
}
