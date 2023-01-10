package com.example.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_sequence",
                       sequenceName = "notification_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "notification_sequence")
    private Long id;
    private Long toCustomerId;
    private String content;
    private String toEmail;
    private LocalDateTime sentAt;
}
