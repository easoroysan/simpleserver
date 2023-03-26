package com.demo.simpleserver.customer;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            Customer roy = new Customer(
                    "Roysan",
                    "Easo",
                    "roysan@email.com",
                    LocalDate.of(1990, Month.JANUARY, 4));

            Customer teri = new Customer(
                    "Teresa",
                    "Roundy",
                    "teresa@email.com",
                    LocalDate.of(1995, Month.JANUARY, 26));

            repository.saveAll(List.of(roy, teri));
        };
    }

}
