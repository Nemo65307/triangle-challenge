package com.nemo.trianglechallenge.rest;

import com.nemo.trianglechallenge.domain.TriangleType;
import com.nemo.trianglechallenge.service.TriangleService;
import com.nemo.trianglechallenge.validator.TriangleValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TriangleController.class)
public class TriangleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TriangleService service;

    @MockBean
    private TriangleValidator validator;

    @Test
    public void givenValidTriangle_thenReturnsCorrectResponse() throws Exception {
        when(validator.validate(any())).thenReturn(Boolean.TRUE);
        when(service.getTriangleType(any())).thenReturn(TriangleType.ISOSCELES);

        mvc.perform(get("/get-type?sideA=2&sideB=2&sideC=2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{response: " + TriangleType.ISOSCELES.toString() + "}"));
    }

    @Test
    public void givenInvalidTriangle_thenReturnsBadRequest() throws Exception {
        when(validator.validate(any())).thenReturn(Boolean.FALSE);

        mvc.perform(get("/get-type?sideA=-1&sideB=1&sideC=-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenUnexpectedException_thenReturnsInternalServerError() throws Exception {
        when(validator.validate(any())).thenThrow(RuntimeException.class);

        mvc.perform(get("/get-type?sideA=2&sideB=1&sideC=3"))
                .andExpect(status().isInternalServerError());
    }

}
