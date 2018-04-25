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
    public void testPrimeNumbersToTenTwice() {
        PrimesResult expectedResult = new PrimesResult(10);
        expectedResult.addPrime(2);
        expectedResult.addPrime(3);
        expectedResult.addPrime(5);
        expectedResult.addPrime(7);

        assertThat(service.calculatePrimes(10), is(equalTo(expectedResult)));
        assertThat(service.calculatePrimes(10), is(equalTo(expectedResult)));
    }

    @Test
    public void testPrimeNumbersToTenAndTwenty() {
        PrimesResult expectedResultToTen = new PrimesResult(10);
        expectedResultToTen.addPrime(2);
        expectedResultToTen.addPrime(3);
        expectedResultToTen.addPrime(5);
        expectedResultToTen.addPrime(7);
        PrimesResult expectedResultToTwenty = new PrimesResult(20);
        expectedResultToTwenty.addPrime(2);
        expectedResultToTwenty.addPrime(3);
        expectedResultToTwenty.addPrime(5);
        expectedResultToTwenty.addPrime(7);
        expectedResultToTwenty.addPrime(11);
        expectedResultToTwenty.addPrime(13);
        expectedResultToTwenty.addPrime(17);
        expectedResultToTwenty.addPrime(19);

        assertThat(service.calculatePrimes(10), is(equalTo(expectedResultToTen)));
        assertThat(service.calculatePrimes(20), is(equalTo(expectedResultToTwenty)));
    }

    @Test
    public void testPrimeNumbersToTwentyAndTen() {
        PrimesResult expectedResultToTen = new PrimesResult(10);
        expectedResultToTen.addPrime(2);
        expectedResultToTen.addPrime(3);
        expectedResultToTen.addPrime(5);
        expectedResultToTen.addPrime(7);
        PrimesResult expectedResultToTwenty = new PrimesResult(20);
        expectedResultToTwenty.addPrime(2);
        expectedResultToTwenty.addPrime(3);
        expectedResultToTwenty.addPrime(5);
        expectedResultToTwenty.addPrime(7);
        expectedResultToTwenty.addPrime(11);
        expectedResultToTwenty.addPrime(13);
        expectedResultToTwenty.addPrime(17);
        expectedResultToTwenty.addPrime(19);

        assertThat(service.calculatePrimes(20), is(equalTo(expectedResultToTwenty)));
        assertThat(service.calculatePrimes(10), is(equalTo(expectedResultToTen)));
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