package com.sixrq.primeservice.service;

import com.sixrq.primeservice.model.PrimesResult;

import java.util.stream.IntStream;


public class PrimeService {

    public PrimesResult calculatePrimes(int limit) {
        PrimesResult result = new PrimesResult(limit);
        IntStream.rangeClosed(0, limit).filter(this::isPrime).forEach(result::addPrime);
        return result;
    }

    boolean isPrime(int candidate) {
        return candidate > 1 && (candidate < 4 || IntStream.rangeClosed(2, candidate/2).noneMatch(num -> candidate%num == 0));
    }
}
