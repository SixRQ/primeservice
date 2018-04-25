package com.sixrq.primeservice.rest;

import com.sixrq.primeservice.model.PrimesResult;
import com.sixrq.primeservice.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeController {

    private final PrimeService service;

    @Autowired
    public PrimeController(PrimeService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String index() {
        return "This is a Spring Boot service";
    }

    @RequestMapping(value= "/primes/{initial}", produces={"application/json","application/xml"})
    @ResponseBody
    public PrimesResult primes(@PathVariable("initial") int initial) {
        return service.calculatePrimes(initial);
    }
}
