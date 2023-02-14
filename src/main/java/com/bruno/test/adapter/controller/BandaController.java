package com.bruno.test.adapter.controller;

import com.bruno.test.adapter.dto.BandaDTO;

import com.bruno.test.data.Banda;
import com.bruno.test.service.BandService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/{band}")
public class BandaController {
    public static final String ID = "/{id}";
    @Autowired
    private BandService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping(value = ID)
    public ResponseEntity<BandaDTO>findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),BandaDTO.class));
   }

   @GetMapping
   public ResponseEntity<List<BandaDTO>> findAll(){
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(x-> mapper.map(x, BandaDTO.class)).collect(Collectors.toList()));
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public Banda save(@RequestBody BandaDTO obj){
        return service.save(obj);
   }

   @PutMapping(value = ID)
    public ResponseEntity<BandaDTO> update(@PathVariable Integer id, @RequestBody BandaDTO obj){
        obj.setId(id);

        return ResponseEntity.ok().body(mapper.map( service.update(obj), BandaDTO.class));
   }
   @DeleteMapping(value = ID)
   public ResponseEntity<BandaDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
   }




}
