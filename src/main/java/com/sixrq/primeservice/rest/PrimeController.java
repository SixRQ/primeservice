package com.sixrq.primeservice.rest;

import com.sixrq.primeservice.model.ErrorResponse;
import com.sixrq.primeservice.model.PrimesResult;
import com.sixrq.primeservice.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public PrimesResult primes(@PathVariable("initial") int initial) throws InterruptedException {
        return service.calculatePrimes(initial);
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
