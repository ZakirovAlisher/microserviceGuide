package com.example.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routKey) {
        log.info("Publishing to {}, routKey: {}, payload: {}", exchange, routKey, payload);
        amqpTemplate.convertAndSend(exchange, routKey, payload);
        log.info("Published to {}, routKey: {}, payload: {}", exchange, routKey, payload);
    }
}
