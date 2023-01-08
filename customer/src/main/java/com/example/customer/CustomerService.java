package com.example.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(final CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                                    .firstName(customerRequest.firstName())
                                    .lastName(customerRequest.lastName())
                                    .email(customerRequest.email())
                                    .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = this.restTemplate
                .getForObject("http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("FRAUDSTER");
        }


    }
}
