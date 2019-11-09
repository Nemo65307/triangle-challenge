package com.nemo.trianglechallenge.domain;

public enum TriangleType {
    EQUILATERAL("Equilateral"),
    ISOSCELES("Isosceles"),
    SCALENE("Scalene");

    private final String stringValue;

    TriangleType(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
