package com.sixrq.primeservice.service;

import com.sixrq.primeservice.model.PrimesResult;
import org.junit.Before;
import org.junit.Test;

import static com.sixrq.primeservice.service.Algorithm.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PrimeServiceTest {

    private PrimeService service;

    @Before
    public void setup() {
        service = new PrimeService();
    }

    @Test
    public void testPrimeNumbersToTen() throws InterruptedException {
        PrimesResult expectedResult = new PrimesResult(10);
        expectedResult.addPrime(2);
        expectedResult.addPrime(3);
        expectedResult.addPrime(5);
        expectedResult.addPrime(7);

        assertThat(service.calculatePrimes(10, functional), is(equalTo(expectedResult)));
    }

    @Test
    public void testPrimeNumbersToTenTwice() throws InterruptedException {
        PrimesResult expectedResult = new PrimesResult(10);
        expectedResult.addPrime(2);
        expectedResult.addPrime(3);
        expectedResult.addPrime(5);
        expectedResult.addPrime(7);

        assertThat(service.calculatePrimes(10, functional), is(equalTo(expectedResult)));
        assertThat(service.calculatePrimes(10, functional), is(equalTo(expectedResult)));
    }

    @Test
    public void testPrimeNumbersToTenAndTwenty() throws InterruptedException {
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

        assertThat(service.calculatePrimes(10, functional), is(equalTo(expectedResultToTen)));
        assertThat(service.calculatePrimes(20, functional), is(equalTo(expectedResultToTwenty)));
    }

    @Test
    public void testPrimeNumbersToTwentyAndTen() throws InterruptedException {
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

        assertThat(service.calculatePrimes(20, functional), is(equalTo(expectedResultToTwenty)));
        assertThat(service.calculatePrimes(10, functional), is(equalTo(expectedResultToTen)));
    }

    @Test
    public void testPrimeNumbersToTwentyImperativeAndTen() throws InterruptedException {
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

        assertThat(service.calculatePrimes(20, imperative), is(equalTo(expectedResultToTwenty)));
        assertThat(service.calculatePrimes(10, functional), is(equalTo(expectedResultToTen)));
    }

    @Test
    public void testPrimeNumbersCorrectWhenInitialIsPrime() throws InterruptedException {
        PrimesResult expectedResult = new PrimesResult(7);
        expectedResult.addPrime(2);
        expectedResult.addPrime(3);
        expectedResult.addPrime(5);
        expectedResult.addPrime(7);

        assertThat(service.calculatePrimes(7, functional), is(equalTo(expectedResult)));
    }

    @Test
    public void oneIsNotPrime() {
        assertThat(PrimeService.isPrime(1), is(equalTo(false)));
    }

    @Test
    public void twoIsPrime() {
        assertThat(PrimeService.isPrime(2), is(equalTo(true)));
    }

    @Test
    public void fourIsNotPrime() {
        assertThat(PrimeService.isPrime(4), is(equalTo(false)));
    }

    @Test
    public void fiveIsPrime() {
        assertThat(PrimeService.isPrime(5), is(equalTo(true)));
    }

    @Test
    public void eightIsNotPrime() {
        assertThat(PrimeService.isPrime(8), is(equalTo(false)));
    }

    @Test
    public void oneHundredAndSeventySevenIsNotPrime() {
        assertThat(PrimeService.isPrime(177), is(equalTo(false)));
    }

    @Test
    public void oneHundredAndSeventyNineIsPrime() {
        assertThat(PrimeService.isPrime(179), is(equalTo(true)));
    }

    @Test
    public void oneIsNotPrimeImperative() {
        assertThat(PrimeService.isPrimeImperative(1), is(equalTo(false)));
    }

    @Test
    public void twoIsPrimeImperative() {
        assertThat(PrimeService.isPrimeImperative(2), is(equalTo(true)));
    }

    @Test
    public void fourIsNotPrimeImperative() {
        assertThat(PrimeService.isPrimeImperative(4), is(equalTo(false)));
    }

    @Test
    public void fiveIsPrimeImperative() {
        assertThat(PrimeService.isPrimeImperative(5), is(equalTo(true)));
    }

    @Test
    public void eightIsNotPrimeImperative() {
        assertThat(PrimeService.isPrimeImperative(8), is(equalTo(false)));
    }

    @Test
    public void oneHundredAndSeventySevenIsNotPrimeImperative() {
        assertThat(PrimeService.isPrimeImperative(177), is(equalTo(false)));
    }
}