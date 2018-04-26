package com.sixrq.primeservice.rest;

import com.sixrq.primeservice.model.ErrorResponse;
import com.sixrq.primeservice.model.PrimesResult;
import com.sixrq.primeservice.service.Algorithm;
import com.sixrq.primeservice.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class PrimeController implements ErrorController {

    private static final String PATH = "/error";
    private final PrimeService service;

    @Autowired
    public PrimeController(PrimeService service) {
        this.service = service;
    }

    @RequestMapping(value= "/primes/{initial}", produces={"application/json","application/xml"})
    @ResponseBody
    public ResponseEntity<PrimesResult> primes(@PathVariable("initial") int initial,
                                               @RequestParam(value = "algorithm", required = false, defaultValue = "functional") String algorithm) throws InterruptedException {
        if (!Arrays.stream(Algorithm.values()).map(Enum::toString).collect(Collectors.toList()).contains(algorithm)) {
            algorithm = "functional";
        }
        return new ResponseEntity<>(service.calculatePrimes(initial, Algorithm.valueOf(algorithm)), HttpStatus.OK);
    }

    @RequestMapping(PATH)
    @ResponseBody
    public ErrorResponse error(HttpServletRequest request, HttpServletResponse response) {
        RequestAttributes attributes = new ServletRequestAttributes(request);
        return new ErrorResponse(attributes);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
