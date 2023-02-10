package com.bruno.test.adapter.controller;

import com.bruno.test.adapter.dto.BandaDTO;
import com.bruno.test.data.Banda;
import com.bruno.test.exceptions.ObjectNotFoundException;
import com.bruno.test.repository.BandRepository;
import com.bruno.test.service.BandServiceImpel;
import org.hibernate.event.spi.PreInsertEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BandaControllerTest {
    public static final Integer ID = 1;
    public static final String METALLICA = "Metallica";
    public static final String NAME = METALLICA;
    public static final String GENERO = "Trash Metal";
    public static final String EMAIL = "metallica@gmail.com";
    @InjectMocks
    private BandServiceImpel service;
    @Mock
    private BandRepository repository;
    @Mock
    private ModelMapper mapper;

    private Banda banda;
    private BandaDTO bandaDTO;
    private Optional<Banda> optionalBanda;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        start();

    }

    @Test
    void findByIdThenReturnAnUserInstace() {
        when(repository.findById(anyInt())).thenReturn(optionalBanda);
        Banda response = service.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Banda.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try{
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }

    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    public void start() {
        banda = new Banda(ID, NAME, GENERO, EMAIL);
        bandaDTO = new BandaDTO(ID, NAME, GENERO, EMAIL);
        optionalBanda = Optional.of(new Banda(ID, NAME, GENERO, EMAIL));


    }
}