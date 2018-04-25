package com.sixrq.primeservice.service;

import com.sixrq.primeservice.model.PrimesResult;

import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.IntStream;


public class PrimeService {
    private SortedSet<Integer> cachedPrimes = new ConcurrentSkipListSet<>();

    public PrimesResult calculatePrimes(int limit) {
        PrimesResult result = new PrimesResult(limit);
        if (!cachedPrimes.isEmpty() && cachedPrimes.last() > limit) {
            cachedPrimes.stream().filter(num -> num < limit).forEach(result::addPrime);
        } else {
            int start = cachedPrimes.isEmpty() ? 0 : cachedPrimes.last() + 1;
            cachedPrimes.stream().filter(num -> num < start).forEach(result::addPrime);
            IntStream.rangeClosed(start, limit).filter(this::isPrime).forEach(result::addPrime);
        }
        cachedPrimes.addAll(result.getPrimes());

        return result;
    }

    boolean isPrime(int candidate) {
        return candidate > 1 && (candidate < 4 || IntStream.rangeClosed(2, candidate/2).noneMatch(num -> candidate%num == 0));
    }
}
