package com.sixrq.primeservice.service;

import com.sixrq.primeservice.model.PrimesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class PrimeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrimeService.class);

    private final int numberOfThreads = 100;

    private SortedSet<Integer> cachedPrimes = new ConcurrentSkipListSet<>();
    private ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads);

    public PrimesResult calculatePrimes(int limit, boolean imperative) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        PrimesResult result = new PrimesResult(limit);
        if (!cachedPrimes.isEmpty() && cachedPrimes.last() > limit) {
            result.addAllPrimes(getPrimesFromCache(limit));
        } else {
            int start = cachedPrimes.isEmpty() ? 0 : cachedPrimes.last() + 1;
            result.addAllPrimes(getPrimesFromCache(start));
            result.addAllPrimes(calculateUncachedPrimes(start, limit, imperative));
        }
        cachedPrimes.addAll(result.getPrimes());

        LOGGER.debug("Time to calculate primes: " + (System.currentTimeMillis() - startTime) + "ms");
        return result;
    }

    private Set<Integer> calculateUncachedPrimes(int start, int limit, boolean imperative) throws InterruptedException {
        Set<Integer> result = new ConcurrentSkipListSet<>();
        int chunkSize = (limit - start) / 100 < 1000 ? 1000 : (limit - start) / 100;
        int chunks = (limit - start) % chunkSize == 0 ? (limit - start) / chunkSize : (limit - start) / chunkSize + 1;
        CountDownLatch latch = new CountDownLatch(chunks);

        for (int chunk = 0; chunk < chunks; chunk++) {
            int processingChunk = chunk;
            executors.execute(() -> {
                int chunckStart = start + processingChunk * chunkSize;
                int chunckLimit = chunckStart + chunkSize > limit ? limit : chunckStart + chunkSize;

                if (imperative) {
                    result.addAll(IntStream.rangeClosed(chunckStart, chunckLimit).filter(PrimeService::isPrimeImperative).boxed().collect(Collectors.toSet()));
                } else {
                    result.addAll(IntStream.rangeClosed(chunckStart, chunckLimit).filter(PrimeService::isPrime).boxed().collect(Collectors.toSet()));
                }
                latch.countDown();
            });
        }
        latch.await();
        return result;
    }

    private Set<Integer> getPrimesFromCache(int start) {
        return cachedPrimes.stream().filter(num -> num < start).collect(Collectors.toSet());
    }

    static boolean isPrime(int candidate) {
        return candidate > 1 && (candidate < 4 || (candidate%2 != 0) && IntStream.rangeClosed(3, candidate / 2).noneMatch(num -> candidate % num == 0));
    }

    static boolean isPrimeImperative(int candidate) {
        return candidate > 1 && (candidate < 4 || (candidate%2 != 0 && checkPrimeImperativeStyle(candidate)));
    }

    private static boolean checkPrimeImperativeStyle(int candidate) {
        for(int index = 3; index <= candidate/2; index = index + 2) {
            if (candidate%index == 0) {
                return false;
            }
        }
        return true;
    }
}
