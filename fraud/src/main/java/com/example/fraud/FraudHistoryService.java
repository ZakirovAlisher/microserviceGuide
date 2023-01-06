package com.example.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudHistoryService(FraudCheckHistoryRepository repository) {
    public boolean isFraudsterCustomer(Long customerId) {
        repository.save(FraudCheckHistory.builder()
                                .isFraudster(false).customerId(customerId).createdAt(LocalDateTime.now())
                                         .build());
        return false;
    }

}
