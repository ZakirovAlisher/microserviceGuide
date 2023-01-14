package com.example.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    public Long customerId;
    public String email;
    public String message;
}
