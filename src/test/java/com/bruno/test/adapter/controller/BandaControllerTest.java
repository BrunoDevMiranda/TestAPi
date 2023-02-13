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
    private BandServiceImpel serviceImpel;
    @Mock
    private ModelMapper mapper;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        start();
    }

    @Test
    void findById(){

    }

    @Test
    void findall(){
    }


    public void start() {
        banda = new Banda(ID, NAME, GENERO, EMAIL);
        bandaDTO = new BandaDTO(ID, NAME, GENERO, EMAIL);



    }



 }