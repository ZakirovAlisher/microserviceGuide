package com.example.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public record FraudHistoryService(FraudCheckHistoryRepository repository) {
    public boolean isFraudsterCustomer(Long customerId) {
        Random rd = new Random();
        boolean isFraudster = rd.nextBoolean();
        repository.save(FraudCheckHistory.builder()
                                .isFraudster(isFraudster).customerId(customerId).createdAt(LocalDateTime.now())
                                         .build());
        return isFraudster;
    }

}
