package com.nemo.trianglechallenge.validator;

import com.nemo.trianglechallenge.domain.Triangle;
import org.junit.Test;

import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TriangleValidatorTest {

    private final TriangleValidator validator = new TriangleValidator();
    private Predicate<Triangle> isValid = validator::validate;

    @Test
    public void givenCorrectSides_thenValidationPasses() {
        Triangle triangle = new Triangle(1, 2, 3);
        assertThat(isValid.test(triangle), is(Boolean.TRUE));
    }

    @Test
    public void givenOneNegativeSide_thenValidationFails() {
        Triangle triangle = new Triangle(1, 1, -1);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenTwoNegativeSides_thenValidationFails() {
        Triangle triangle = new Triangle(-1, -1, 2);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenAllNegativeSides_thenValidationFails() {
        Triangle triangle = new Triangle(-1, -2, -1);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenOneZeroSide_thenValidationFails() {
        Triangle triangle = new Triangle(1, 0, 1);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenAllZeroSides_thenValidationFails() {
        Triangle triangle = new Triangle(0, 0, 0);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenASideBiggerThanSumOfTwoOthers_thenValidationFails() {
        Triangle triangle = new Triangle(5, 1, 2);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenBSideBiggerThanSumOfTwoOthers_thenValidationFails() {
        Triangle triangle = new Triangle(1, 5, 2);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenCSideBiggerThanSumOfTwoOthers_thenValidationFails() {
        Triangle triangle = new Triangle(2, 1, 5);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

    @Test
    public void givenBigSideLength_thenNoOverflow_thenValidationPasses() {
        Triangle triangle = new Triangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertThat(isValid.test(triangle), is(Boolean.TRUE));
    }

    @Test
    public void givenOneSideBiggerThanSumOfTwoOthers_givenTwoOthersAreNegative_thenValidationFails() {
        Triangle triangle = new Triangle(3, -1, -4);
        assertThat(isValid.test(triangle), is(Boolean.FALSE));
    }

}
