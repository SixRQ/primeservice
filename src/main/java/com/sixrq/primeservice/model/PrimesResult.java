package com.sixrq.primeservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;
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

    public void addAllPrimes(Set<Integer> primeNumbers) {
        primes.addAll(primeNumbers);
    }

    @Override
    public String toString() {
        return "PrimesResult{" +
                "initial=" + initial +
                ", primes=" + primes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimesResult result = (PrimesResult) o;
        return initial == result.initial &&
                Objects.equals(primes, result.primes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(initial, primes);
    }
}
