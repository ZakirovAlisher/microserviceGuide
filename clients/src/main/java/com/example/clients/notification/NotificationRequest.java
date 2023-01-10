package com.example.clients.notification;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotificationRequest {
    public Long customerId;
    public String email;
}
