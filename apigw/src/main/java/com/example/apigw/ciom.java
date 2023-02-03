package com.example.apigw;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/apigw")
@Slf4j
public record ciom() {
    @PostMapping
    public void registerCustomer(@RequestBody String customerRequest) {
        log.info("new customer reg {}", customerRequest);

    }
}
