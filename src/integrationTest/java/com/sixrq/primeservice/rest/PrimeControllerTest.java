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

import javax.servlet.RequestDispatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPrimeAsJson() throws Exception {
        String expectedResult = "{\"initial\":10,\"primes\":[2,3,5,7]}";

        mvc.perform(MockMvcRequestBuilders.get("/primes/10").accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void testInvalidInputAsJson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/10.5").accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isBadRequest()));
    }

    @Test
    public void testErrorReturnsMeaningfulJson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
                .requestAttr("org.springframework.web.servlet.DispatcherServlet.EXCEPTION", new Exception("Some error text"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("status", is(400)))
                .andExpect(jsonPath("message", is("Exception: Some error text")));
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
                .andExpect((status().isOk()))
                .andExpect(content().xml(expectedResult));
    }
}