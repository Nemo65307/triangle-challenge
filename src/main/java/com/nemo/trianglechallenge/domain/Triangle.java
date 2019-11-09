package com.nemo.trianglechallenge.domain;

public class Triangle {

    private final long sideA, sideB, sideC;

    public Triangle(long sideA, long sideB, long sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public long getSideA() {
        return sideA;
    }

    public long getSideB() {
        return sideB;
    }

    public long getSideC() {
        return sideC;
    }
}
