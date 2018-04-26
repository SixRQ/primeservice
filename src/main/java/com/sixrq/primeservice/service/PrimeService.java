package com.sixrq.primeservice.service;

import com.sixrq.primeservice.model.PrimesResult;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public PrimesResult calculatePrimes(int limit) throws InterruptedException {

        PrimesResult result = new PrimesResult(limit);
        if (!cachedPrimes.isEmpty() && cachedPrimes.last() > limit) {
            populateWithCachedValues(result, limit);
        } else {
            int start = cachedPrimes.isEmpty() ? 0 : cachedPrimes.last() + 1;
            populateWithCachedValues(result, start);
            calculateUncachedPrimes(start, limit, result);
        }
        cachedPrimes.addAll(result.getPrimes());

        return result;
    }

    private void calculateUncachedPrimes(int start, int limit, PrimesResult result) throws InterruptedException {
        int chunkSize = (limit - start) / 100 < 1000 ? 1000 : (limit - start) / 100;
        int chunks = (limit - start) % chunkSize == 0 ? (limit - start) / chunkSize : (limit - start) / chunkSize + 1;
        CountDownLatch latch = new CountDownLatch(chunks);

        for (int chunk = 0; chunk < chunks; chunk++) {
            int processingChunk = chunk;
            executors.execute(() -> {
                int chunckStart = start + processingChunk * chunkSize;
                int chunckLimit = chunckStart + chunkSize > limit ? limit : chunckStart + chunkSize;

                result.addAllPrimes(IntStream.rangeClosed(chunckStart, chunckLimit).filter(PrimeService::isPrime).boxed().collect(Collectors.toList()));
                latch.countDown();
            });
        }
        latch.await();
    }

    private void populateWithCachedValues(PrimesResult result, int start) {
        result.addAllPrimes(cachedPrimes.stream().filter(num -> num < start).collect(Collectors.toList()));
    }

    static boolean isPrime(int candidate) {
        return candidate > 1 && (candidate < 4 || IntStream.rangeClosed(2, candidate / 2).noneMatch(num -> candidate % num == 0));
    }
}
