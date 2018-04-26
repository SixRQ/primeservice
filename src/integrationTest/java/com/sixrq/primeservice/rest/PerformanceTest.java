package com.sixrq.primeservice.rest;

import com.sixrq.primeservice.service.PrimeService;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PerformanceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceTest.class);

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new PrimeController(new PrimeService())).build();
    }

    @Test
    public void profilePrimesToTen() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(10, false);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes To Ten in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneHundredThousand() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000, false);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes to One Hundred Thousand in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneMillion() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(1000000, false);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes To One Million in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneHundredThousandWithSecondCallToCache() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000, false);
            performRestServiceCall(100000, false);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Cached Primes To One Hundred Thousand in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileThirtyConcurrentRequestsToOneHundredThousand() throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(30);
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            for(int threadCount = 0; threadCount < 30; threadCount++) {
                executeThreadedCall(executors, latch, false);
            }
            latch.await();
        } finally {
            stopWatch.stop();
            executors.shutdown();
            LOGGER.info("Finished Thirty Concurrent Threads test in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileThirtyRequestsToOneHundredThousandUsingFiveThreads() throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(30);
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            for(int threadCount = 0; threadCount < 30; threadCount++) {
                executeThreadedCall(executors, latch, false);
            }
            latch.await();
        } finally {
            stopWatch.stop();
            executors.shutdown();
            LOGGER.info("Finished Thirty Requests With a Thread Pool of Five in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileTenConsecutiveRequestsToOneThousand() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
            performRestServiceCall(1000, false);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Ten Consecutve requests in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToTenImperative() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(10, true);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes To Ten in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneHundredThousandImperative() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000, true);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes to One Hundred Thousand in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneMillionImperative() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(1000000, true);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes To One Million in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneHundredThousandImperativeWithSecondCallToCache() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000, true);
            performRestServiceCall(100000, true);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Cached Primes To One Hundred Thousand in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileThirtyConcurrentRequestsToOneHundredThousandImperative() throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(30);
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            for(int threadCount = 0; threadCount < 30; threadCount++) {
                executeThreadedCall(executors, latch, true);
            }
            latch.await();
        } finally {
            stopWatch.stop();
            executors.shutdown();
            LOGGER.info("Finished Thirty Concurrent Threads test in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileThirtyRequestsToOneHundredThousandUsingImperativeFiveThreads() throws InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(30);
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            for(int threadCount = 0; threadCount < 30; threadCount++) {
                executeThreadedCall(executors, latch, true);
            }
            latch.await();
        } finally {
            stopWatch.stop();
            executors.shutdown();
            LOGGER.info("Finished Thirty Requests With a Thread Pool of Five in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileTenConsecutiveRequestsToOneThousandImperative() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
            performRestServiceCall(1000, true);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Ten Consecutve requests in " + stopWatch.getTime() + "ms");
        }
    }

    private void executeThreadedCall(ExecutorService executors, CountDownLatch latch, boolean imperative) {
        executors.submit(() -> {
            try {
                performRestServiceCall(100000, imperative);
                latch.countDown();
            } catch (Exception e) {
                LOGGER.error("Unexpected exception received", e);
                fail("Unexpected exceptions running multi-threaded test");
            }
        });
    }

    private void performRestServiceCall(int initial, boolean imperative) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/" + initial + "?imperative=" + imperative).accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()));
    }

}