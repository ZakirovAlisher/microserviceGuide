package com.example.customer;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.clients.fraud.FraudCheckResponse;
import com.example.clients.fraud.FraudClient;
import com.example.clients.notification.NotificationClient;
import com.example.clients.notification.NotificationConstants;
import com.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              RestTemplate restTemplate,
                              FraudClient fraudClient,
                              RabbitMQMessageProducer messageProducer) {
    public CustomerResponse registerCustomer(final CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                                    .firstName(customerRequest.firstName())
                                    .lastName(customerRequest.lastName())
                                    .email(customerRequest.email())
                                    .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        final NotificationRequest notificationRequest = NotificationRequest
                .builder()
                .customerId(customer.getId())
                .email(customer.getEmail())
                .message(String.format("Hello %s!", customer.getFirstName() + " " + customer.getLastName()))
                .build();

       messageProducer.publish(notificationRequest,
                               "bookexchanger",
                               "key-value");

//        MyThread thread = new MyThread(customer, notificationClient);
//        new Thread(thread).start();


        return new CustomerResponse(customer.getId(),
                                    customer.getEmail(),
                                    customer.getFirstName() + " " + customer.getLastName(),
                                    fraudCheckResponse.isFraudster());
    }
}

// class MyThread implements Runnable {
//
//    Customer customer;
//    NotificationClient notificationClient;
//    MyThread(Customer customer, NotificationClient notificationClient) {
//        this.customer = customer;
//        this.notificationClient = notificationClient;
//    }
//    public void run(){
//        notificationClient.sendNotification(
//                NotificationRequest.builder()
//                                   .customerId(customer.getId())
//                                   .email(customer.getEmail())
//                                   .build());
//    }
//}