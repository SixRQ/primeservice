package com.sixrq.primeservice.rest;

import com.sixrq.primeservice.model.ErrorResponse;
import com.sixrq.primeservice.model.PrimesResult;
import com.sixrq.primeservice.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
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
    public PrimesResult primes(@PathVariable("initial") int initial, @RequestParam(value = "imperative", required = false, defaultValue = "false") boolean imperative) throws InterruptedException {
        return service.calculatePrimes(initial, imperative);
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
