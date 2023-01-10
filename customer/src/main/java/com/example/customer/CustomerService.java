package com.example.customer;

import com.example.clients.fraud.FraudCheckResponse;
import com.example.clients.fraud.FraudClient;
import com.example.clients.notification.NotificationClient;
import com.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient,
                              NotificationClient notificationClient) {
    public CustomerResponse registerCustomer(final CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                                    .firstName(customerRequest.firstName())
                                    .lastName(customerRequest.lastName())
                                    .email(customerRequest.email())
                                    .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        notificationClient.sendNotification(
                NotificationRequest.builder()
                                   .customerId(customer.getId())
                                   .email(customer.getEmail())
                                   .build());


        return new CustomerResponse(customer.getId(),
                                    customer.getEmail(),
                                    customer.getFirstName() + " " + customer.getLastName(),
                                    fraudCheckResponse.isFraudster());
    }
}
