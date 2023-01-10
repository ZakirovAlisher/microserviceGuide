package com.example.customer;

public record CustomerResponse(Long id, String email, String name, Boolean isFraudster) {
}
