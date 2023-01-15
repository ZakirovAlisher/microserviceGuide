package com.example.notification;

import com.example.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = {
            "${rabbitmq.queues.notification}"
    })
    public void consume(NotificationRequest request) {
        log.info("Consumed Notification {}", request);
        notificationService.sendNotification(request);
    }

    @RabbitListener(queues = {
            "${rabbitmq.queues.notificationFraud}"
    })
    public void consumeFraud(NotificationRequest request) {
        log.info("Consumed Fraud Notification {}", request);
        notificationService.sendNotification(request);
    }
}
