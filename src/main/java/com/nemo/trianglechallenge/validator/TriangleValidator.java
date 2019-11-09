package com.nemo.trianglechallenge.validator;

import com.nemo.trianglechallenge.domain.Triangle;
import org.springframework.stereotype.Component;

@Component
public class TriangleValidator {

    public boolean validate(Triangle triangle) {
        return !isAnySideTooShort(triangle) && !violatesTriangleInequality(triangle);
    }

    private boolean isAnySideTooShort(Triangle t) {
        return t.getSideA() <= 0 || t.getSideB() <= 0 || t.getSideC() <= 0;
    }

    private boolean violatesTriangleInequality(Triangle t) {
        return t.getSideA() > t.getSideB() + t.getSideC() ||
                t.getSideB() > t.getSideA() + t.getSideC() ||
                t.getSideC() > t.getSideA() + t.getSideB();
    }

}
