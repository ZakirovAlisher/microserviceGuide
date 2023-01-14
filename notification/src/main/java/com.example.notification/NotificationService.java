package com.example.notification;

import com.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {
    public void sendNotification(NotificationRequest request){
        Notification notification = Notification.builder()
                                                .toCustomerId(request.customerId)
                                                .sentAt(LocalDateTime.now())
                                                .content(request.message)
                                                .toEmail(request.email)
                                                .build();
        repository.save(notification);
    }
}
