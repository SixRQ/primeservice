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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PerformanceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceTest.class);

    @Autowired
    private MockMvc mvc;

    @Test
    public void profileSimpleRequest() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(10);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished simple test in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileSimpleLargeRequest() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished simple test in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileSimpleExtremeRequest() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(1000000);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished simple test in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileTwoLargeRequests() throws Exception {
        StopWatch stopWatch = StopWatch.createStarted();

        try {
            performRestServiceCall(100000);
            performRestServiceCall(100000);
        } finally {
            stopWatch.stop();
            LOGGER.info("Finished simple test in " + stopWatch.getTime() + "ms");
        }
    }

    @Test
    public void profileMultipleSimpleRequests() throws Exception {
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
            LOGGER.info("Finished simple test in " + stopWatch.getTime() + "ms");
        }
    }

    private void performRestServiceCall(int initial) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/" + initial).accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()));
    }

}
