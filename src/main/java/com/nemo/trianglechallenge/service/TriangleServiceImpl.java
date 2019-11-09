package com.nemo.trianglechallenge.service;

import com.nemo.trianglechallenge.domain.Triangle;
import com.nemo.trianglechallenge.domain.TriangleType;
import com.nemo.trianglechallenge.qualifier.TriangleQualifier;
import org.springframework.stereotype.Service;

@Service
public class TriangleServiceImpl implements TriangleService {

    private final TriangleQualifier qualifier;

    public TriangleServiceImpl(TriangleQualifier qualifier) {
        this.qualifier = qualifier;
    }

    @Override
    public TriangleType getTriangleType(Triangle triangle) {
        return qualifier.qualifyTriangleType(triangle);
    }

}
