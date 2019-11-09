package com.nemo.trianglechallenge.service;

import com.nemo.trianglechallenge.domain.Triangle;
import com.nemo.trianglechallenge.domain.TriangleType;

public interface TriangleService {

    TriangleType getTriangleType(Triangle triangle);

}
