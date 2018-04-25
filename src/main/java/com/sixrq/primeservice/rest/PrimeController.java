package com.sixrq.primeservice.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeController {

    @RequestMapping("/")
    public String index() {
        return "This is a Spring Boot service";
    }
}
