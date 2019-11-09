package com.nemo.trianglechallenge.qualifier;

import com.nemo.trianglechallenge.domain.Triangle;
import com.nemo.trianglechallenge.domain.TriangleType;
import org.springframework.stereotype.Component;

@Component
public class TriangleQualifier {

    public TriangleType qualifyTriangleType(Triangle triangle) {
        if (areAllSidesEqual(triangle)) {
            return TriangleType.EQUILATERAL;
        } else if (areAtLeastTwoSidesEqual(triangle)) {
            return TriangleType.ISOSCELES;
        } else {
            return TriangleType.SCALENE;
        }
    }

    private boolean areAllSidesEqual(Triangle t) {
        return t.getSideA() == t.getSideB() && t.getSideB() == t.getSideC();
    }

    private boolean areAtLeastTwoSidesEqual(Triangle t) {
        return t.getSideA() == t.getSideB() ||
                t.getSideB() == t.getSideC() ||
                t.getSideC() == t.getSideA();
    }

}
