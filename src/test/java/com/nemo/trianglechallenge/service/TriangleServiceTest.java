package com.nemo.trianglechallenge.service;

import com.nemo.trianglechallenge.domain.Triangle;
import com.nemo.trianglechallenge.domain.TriangleType;
import com.nemo.trianglechallenge.qualifier.TriangleQualifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TriangleServiceTest {

    @Mock
    private TriangleQualifier qualifier;

    @Test
    public void givenCorrectTriangle_thenReturnsCorrespondingTriangleType() {
        Triangle triangle = new Triangle(3, 2, 1);
        when(qualifier.qualifyTriangleType(eq(triangle))).thenReturn(TriangleType.SCALENE);
        TriangleType type = new TriangleServiceImpl(qualifier).getTriangleType(triangle);
        assertThat(type.toString(), is(TriangleType.SCALENE.toString()));
    }

}
