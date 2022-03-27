package com.defers.springboot.finances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancesApplication {

    private static final Logger log = LoggerFactory.getLogger(FinancesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FinancesApplication.class, args);
    }

}
