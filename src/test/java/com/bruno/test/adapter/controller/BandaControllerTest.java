package com.bruno.test.adapter.controller;

import com.bruno.test.adapter.dto.BandaDTO;
import com.bruno.test.data.Banda;
import com.bruno.test.exceptions.DataIntegratyViolationException;
import com.bruno.test.exceptions.ObjectNotFoundException;
import com.bruno.test.repository.BandRepository;
import com.bruno.test.service.BandServiceImpel;
import jakarta.servlet.annotation.MultipartConfig;
import org.h2.command.dml.MergeUsing;
import org.hibernate.event.spi.PreInsertEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BandaControllerTest {

    public static final Integer ID = 1;
    public static final String METALLICA = "Metallica";
    public static final String NAME = METALLICA;
    public static final String GENERO = "Trash Metal";
    public static final String EMAIL = "metallica@gmail.com";
    private Banda banda;
    private BandaDTO bandaDTO;


    @InjectMocks
    private BandaController controller;
    @Mock
    private BandServiceImpel service;
    @Mock
    private ModelMapper mapper;
    public static final int INDEX = 0;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        start();
    }

    @Test
    void whenFindById(){
        when(service.findById(anyInt())).thenReturn(banda);
        when(mapper.map(any(), any())).thenReturn(bandaDTO);

        ResponseEntity<BandaDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(BandaDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(GENERO, response.getBody().getGenero());
        assertEquals(EMAIL, response.getBody().getEmail());
    }
    @Test
    void findallTheReturnaListOfBandaDTO(){
        when(service.findAll()).thenReturn(List.of(banda));
        when(mapper.map(any(), any())).thenReturn(bandaDTO);

        ResponseEntity<List<BandaDTO>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(BandaDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(GENERO, response.getBody().get(INDEX).getGenero());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
    }

    @Test
    void whenSaveTheReturnCreated(){
        when(service.save(any())).thenReturn(banda);

        Banda response = controller.save(bandaDTO);
        assertEquals(Banda.class, response.getClass());
    }

    @Test
    void whenUpdateTheReturnUpdate(){
        when(service.update(bandaDTO)).thenReturn(banda);
        when(mapper.map(any(), any())).thenReturn(bandaDTO);

        ResponseEntity<BandaDTO> response = controller.update(ID, bandaDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(BandaDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(GENERO, response.getBody().getGenero());
    }
    @Test
    void whenDeleteTheReturnSucess(){
        doNothing().when(service).delete(anyInt());

        ResponseEntity<BandaDTO> response = controller.delete(ID);
         assertNotNull(response);
         assertEquals(ResponseEntity.class, response.getClass());
         assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
         verify(service, times(1)).delete(anyInt());


    }


    public void start() {
        banda = new Banda(ID, NAME, GENERO, EMAIL);
        bandaDTO = new BandaDTO(ID, NAME, GENERO, EMAIL);



    }



 }