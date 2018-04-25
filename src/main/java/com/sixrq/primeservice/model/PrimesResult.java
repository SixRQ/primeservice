package com.sixrq.primeservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
import java.util.TreeSet;

@XmlRootElement
public class PrimesResult {
    private final int initial;
    private final Set<Integer> primes;

    public PrimesResult(int initial) {
        this.initial = initial;
        primes = new TreeSet<>();
    }

    public int getInitial() {
        return initial;
    }

    public Set<Integer> getPrimes() {
        return primes;
    }

    public void addPrime(int prime) {
        primes.add(prime);
    }
}
