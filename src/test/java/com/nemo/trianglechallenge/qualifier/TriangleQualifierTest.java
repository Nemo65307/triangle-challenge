package com.nemo.trianglechallenge.qualifier;

import com.nemo.trianglechallenge.domain.Triangle;
import com.nemo.trianglechallenge.domain.TriangleType;
import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TriangleQualifierTest {

    private final TriangleQualifier qualifier = new TriangleQualifier();
    private Function<Triangle, TriangleType> qualification = qualifier::qualifyTriangleType;

    @Test
    public void givenAllSidesEqual_thenReturnEquilateral() {
        Triangle triangle = new Triangle(1, 1, 1);
        assertThat(qualification.apply(triangle), is(TriangleType.EQUILATERAL));
    }

    @Test
    public void givenASideUnequal_thenReturnIsosceles() {
        Triangle triangle = new Triangle(0, 1, 1);
        assertThat(qualification.apply(triangle), is(TriangleType.ISOSCELES));
    }

    @Test
    public void givenBSideUnequal_thenReturnIsosceles() {
        Triangle triangle = new Triangle(1, 0, 1);
        assertThat(qualification.apply(triangle), is(TriangleType.ISOSCELES));
    }

    @Test
    public void givenCSideUnequal_thenReturnIsosceles() {
        Triangle triangle = new Triangle(1, 1, 0);
        assertThat(qualification.apply(triangle), is(TriangleType.ISOSCELES));
    }

    @Test
    public void givenNoEqualSides_thenReturnScalene() {
        Triangle triangle = new Triangle(1, 2, 3);
        assertThat(qualification.apply(triangle), is(TriangleType.SCALENE));
    }

    @Test
    public void givenInvalidTriangle_thenReturnScalene() {
        Triangle triangle = new Triangle(0, 1, 5);
        assertThat(qualification.apply(triangle), is(TriangleType.SCALENE));
    }

}
