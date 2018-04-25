package com.sixrq.primeservice;

import com.sixrq.primeservice.service.PrimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    PrimeService service() {
        return new PrimeService();
    }
}
