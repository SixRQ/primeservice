package com.sixrq.primeservice.service;

import com.sixrq.primeservice.model.PrimesResult;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PrimeServiceTest {

    private PrimeService service;

    @Before
    public void setup() {
        service = new PrimeService();
    }

    @Test
    public void testPrimeNumbersToTen() {
        PrimesResult expectedResult = new PrimesResult(10);
        expectedResult.addPrime(2);
        expectedResult.addPrime(3);
        expectedResult.addPrime(5);
        expectedResult.addPrime(7);

        assertThat(service.calculatePrimes(10), is(equalTo(expectedResult)));
    }

    @Test
    public void testPrimeNumbersCorrectWhenInitialIsPrime() {
        PrimesResult expectedResult = new PrimesResult(7);
        expectedResult.addPrime(2);
        expectedResult.addPrime(3);
        expectedResult.addPrime(5);
        expectedResult.addPrime(7);

        assertThat(service.calculatePrimes(7), is(equalTo(expectedResult)));
    }

    @Test
    public void oneIsNotPrime() {
        assertThat(service.isPrime(1), is(equalTo(false)));
    }

    @Test
    public void twoIsPrime() {
        assertThat(service.isPrime(2), is(equalTo(true)));
    }

    @Test
    public void fourIsNotPrime() {
        assertThat(service.isPrime(4), is(equalTo(false)));
    }

    @Test
    public void oneHundredAndSeventySevenIsNotPrime() {
        assertThat(service.isPrime(177), is(equalTo(false)));
    }

    @Test
    public void oneHundredAndSeventyNineIsPrime() {
        assertThat(service.isPrime(179), is(equalTo(true)));
    }
}