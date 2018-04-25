package com.sixrq.primeservice.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPrimeAsJson() throws Exception {
        String expectedResult = "{\"initial\":10,\"primes\":[2,3,5,7]}";

        mvc.perform(MockMvcRequestBuilders.get("/primes/10").accept(MediaType.APPLICATION_JSON))
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.content().json(expectedResult));
    }

    @Test
    public void testPrimeAsXML() throws Exception {
        String expectedResult = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<PrimesResult xmlns=\"\">" +
                "    <initial>10</initial>" +
                "    <primes>" +
                "        <primes>2</primes>" +
                "        <primes>3</primes>" +
                "        <primes>5</primes>" +
                "        <primes>7</primes>" +
                "    </primes>" +
                "</PrimesResult>";

        mvc.perform(MockMvcRequestBuilders.get("/primes/10").accept(MediaType.APPLICATION_XML))
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.content().xml(expectedResult));
    }
}