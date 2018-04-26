package com.sixrq.primeservice.rest;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Autowired
    private MockMvc mvc;

    @Test
    public void profilePrimesToTen() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(10);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes To Ten in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneHundredThousand() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes to One Hundred Thousand in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneMillion() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(1000000);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Primes To One Million in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profilePrimesToOneHundredThousandWithSecondCallToCache() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000);
            performRestServiceCall(100000);
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
                executeThreadedCall(executors, latch);
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
                executeThreadedCall(executors, latch);
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
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
            performRestServiceCall(1000);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished Ten Consecutve requests in " + stopWatch.getTime() + "ms");
        }
    }

    private void executeThreadedCall(ExecutorService executors, CountDownLatch latch) {
        executors.submit(() -> {
            try {
                performRestServiceCall(100000);
                LOGGER.info("Completed threaded call");
                latch.countDown();
            } catch (Exception e) {
                LOGGER.error("Unexpected exception received", e);
                fail("Unexpected exceptions running multi-threaded test");
            }
        });
    }

    private void performRestServiceCall(int initial) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/" + initial).accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()));
    }

}
