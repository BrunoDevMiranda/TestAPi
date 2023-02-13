package com.bruno.test.service;

import com.bruno.test.adapter.dto.BandaDTO;
import com.bruno.test.data.Banda;
import com.bruno.test.exceptions.DataIntegratyViolationException;
import com.bruno.test.exceptions.ObjectNotFoundException;
import com.bruno.test.repository.BandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BandServiceImpelTest {

    public static final Integer ID = 1;
    public static final String METALLICA = "Metallica";
    public static final String NAME = METALLICA;
    public static final String GENERO = "Trash Metal";
    public static final String EMAIL = "metallica@gmail.com";
    public static final int INDEX = 0;
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
    void findByIdThenReturnAnBandInstace() {
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
    void whenFindAllThenReturnAnListOfBand() {
        when(repository.findAll()).thenReturn(List.of(banda));
        List<Banda> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Banda.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(GENERO, response.get(INDEX).getGenero());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
    }



    @Test
    void whenSaveThenReturnSuccess() {
        when(repository.save(any())).thenReturn(banda);

        Banda response = service.save(bandaDTO);

        assertNotNull(response);
        assertEquals(Banda.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(GENERO,response.getGenero());
    }

    @Test
    void whenSaveThenReturnAnDataIntergrityViolationException(){
        when(repository.findByEmail(anyString())).thenReturn(optionalBanda);
        try{
            optionalBanda.get().setId(2);
            service.save(bandaDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void update() {

        when(repository.save(any())).thenReturn(banda);

        Banda response = service.update(bandaDTO);

        assertNotNull(response);
        assertEquals(Banda.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(GENERO,response.getGenero());

    }

    @Test
    void whenUpdateThenReturnAnDataIntergrityViolationException(){
        when(repository.findByEmail(anyString())).thenReturn(optionalBanda);
        try{
            optionalBanda.get().setId(2);
            service.update(bandaDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void deleteWithSucess() {
        when(repository.findById(anyInt())).thenReturn(optionalBanda);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());

    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            service.delete(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
        }

    }

    public void start() {
        banda = new Banda(ID, NAME, GENERO, EMAIL);
        bandaDTO = new BandaDTO(ID, NAME, GENERO, EMAIL);
        optionalBanda = Optional.of(new Banda(ID, NAME, GENERO, EMAIL));


    }
}