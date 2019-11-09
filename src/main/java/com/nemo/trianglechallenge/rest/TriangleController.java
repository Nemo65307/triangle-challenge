package com.nemo.trianglechallenge.rest;

import com.nemo.trianglechallenge.domain.Triangle;
import com.nemo.trianglechallenge.domain.TriangleType;
import com.nemo.trianglechallenge.service.TriangleService;
import com.nemo.trianglechallenge.validator.TriangleValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Predicate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TriangleController {

    private final TriangleService service;
    private final TriangleValidator validator;

    public TriangleController(TriangleService service, TriangleValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @RequestMapping(value = "/get-type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringResponseWrapper> getTriangleType(TriangleDto triangleDto) {
        Predicate<Triangle> isValid = validator::validate;
        Triangle triangle = DtoEntityAdapter.convertToEntity(triangleDto);
        if (isValid.test(triangle)) {
            TriangleType type = service.getTriangleType(triangle);
            return new ResponseEntity<>(
                    new StringResponseWrapper(type.toString()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new StringResponseWrapper("Triangle is invalid"), HttpStatus.BAD_REQUEST);
        }
    }

    private class StringResponseWrapper {
        private String response;

        public StringResponseWrapper() {
        }

        StringResponseWrapper(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

    }

    private class TriangleDto {
        private long sideA, sideB, sideC;

        public TriangleDto() {
        }

        public long getSideA() {
            return sideA;
        }

        public void setSideA(long sideA) {
            this.sideA = sideA;
        }

        public long getSideB() {
            return sideB;
        }

        public void setSideB(long sideB) {
            this.sideB = sideB;
        }

        public long getSideC() {
            return sideC;
        }

        public void setSideC(long sideC) {
            this.sideC = sideC;
        }
    }

    private static class DtoEntityAdapter {
        private static Triangle convertToEntity(TriangleDto triangleDto) {
            return new Triangle(triangleDto.sideA, triangleDto.sideB, triangleDto.sideC);
        }
    }
}
