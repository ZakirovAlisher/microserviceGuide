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
        log.info("Consumed {}", request);
        notificationService.sendNotification(request);
    }

}
