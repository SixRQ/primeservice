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
    public void testPrimeImperativeFalseAsJson() throws Exception {
        String expectedResult = "{\"initial\":10,\"primes\":[2,3,5,7]}";

        mvc.perform(MockMvcRequestBuilders.get("/primes/10?imperative=false").accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void testPrimeImperativeAsJson() throws Exception {
        String expectedResult = "{\"initial\":10,\"primes\":[2,3,5,7]}";

        mvc.perform(MockMvcRequestBuilders.get("/primes/10?imperative=true").accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void testPrimesToOneThousandAsJson() throws Exception {
        String expectedResult = "{\"initial\":1000,\"primes\":[2,3,5," +
                "7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73," +
                "79,83,89,97,101,103,107,109,113,127,131,137,139,149," +
                "151,157,163,167,173,179,181,191,193,197,199,211,223,227," +
                "229,233,239,241,251,257,263,269,271,277,281,283,293,307," +
                "311,313,317,331,337,347,349,353,359,367,373,379,383,389,397," +
                "401,409,419,421,431,433,439,443,449,457,461,463,467,479,487," +
                "491,499,503,509,521,523,541,547,557,563,569,571,577,587," +
                "593,599,601,607,613,617,619,631,641,643,647,653,659,661," +
                "673,677,683,691,701,709,719,727,733,739,743,751,757,761," +
                "769,773,787,797,809,811,821,823,827,829,839,853,857,859," +
                "863,877,881,883,887,907,911,919,929,937,941,947,953,967," +
                "971,977,983,991,997]}";

        mvc.perform(MockMvcRequestBuilders.get("/primes/1000").accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void testPrimesToOneThousandImperativeAsJson() throws Exception {
        String expectedResult = "{\"initial\":1000,\"primes\":[2,3,5," +
                "7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73," +
                "79,83,89,97,101,103,107,109,113,127,131,137,139,149," +
                "151,157,163,167,173,179,181,191,193,197,199,211,223,227," +
                "229,233,239,241,251,257,263,269,271,277,281,283,293,307," +
                "311,313,317,331,337,347,349,353,359,367,373,379,383,389,397," +
                "401,409,419,421,431,433,439,443,449,457,461,463,467,479,487," +
                "491,499,503,509,521,523,541,547,557,563,569,571,577,587," +
                "593,599,601,607,613,617,619,631,641,643,647,653,659,661," +
                "673,677,683,691,701,709,719,727,733,739,743,751,757,761," +
                "769,773,787,797,809,811,821,823,827,829,839,853,857,859," +
                "863,877,881,883,887,907,911,919,929,937,941,947,953,967," +
                "971,977,983,991,997]}";

        mvc.perform(MockMvcRequestBuilders.get("/primes/1000?imperative=true").accept(MediaType.APPLICATION_JSON))
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

    @Test
    public void testPrimeImperativeAsXML() throws Exception {
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

        mvc.perform(MockMvcRequestBuilders.get("/primes/10?imperative=true").accept(MediaType.APPLICATION_XML))
                .andExpect((status().isOk()))
                .andExpect(content().xml(expectedResult));
    }
}