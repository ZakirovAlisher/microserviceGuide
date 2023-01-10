package com.example.notification;

import com.example.clients.notification.NotificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
public record NotificationController(NotificationService service) {

    @PostMapping()
    public void sendNotification(@RequestBody NotificationRequest request) {
        service.sendNotification(request);
    }
}
