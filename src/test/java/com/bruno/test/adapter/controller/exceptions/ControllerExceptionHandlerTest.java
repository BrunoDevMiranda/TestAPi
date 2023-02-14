package com.bruno.test.adapter.controller.exceptions;

import com.bruno.test.exceptions.DataIntegratyViolationException;
import com.bruno.test.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ControllerExceptionHandlerTest {
    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntiny() {
        ResponseEntity<StandError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException("Objeto não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandError.class, response.getBody().getClass());
        assertEquals("Objeto não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/band/2", response.getBody());
        assertNotEquals(LocalDateTime.now(), response.getBody());


    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandError> response = exceptionHandler
                .dataIntegrityViolationException(
                        new DataIntegratyViolationException("Email já cadastrado"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandError.class, response.getBody().getClass());
        assertEquals("Email já cadastrado", response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}