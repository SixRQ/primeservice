package com.sixrq.primeservice.rest;

import com.sixrq.primeservice.model.PrimesResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimeController {

    @RequestMapping("/")
    public String index() {
        return "This is a Spring Boot service";
    }

    @RequestMapping(value="/testPrime", produces={"application/json","application/xml"})
    @ResponseBody
    public PrimesResult test() {
        PrimesResult result = new PrimesResult(10);
        result.addPrime(2);
        result.addPrime(3);
        result.addPrime(5);
        result.addPrime(7);
        return result;
    }
}
