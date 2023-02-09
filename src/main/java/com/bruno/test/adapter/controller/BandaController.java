package com.bruno.test.adapter.controller;

import com.bruno.test.adapter.dto.BandaDTO;
import com.bruno.test.data.Banda;
import com.bruno.test.service.BandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/{band}")
public class BandaController {
    @Autowired
    private BandService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping(value = "/{id}")
    public ResponseEntity<BandaDTO>findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),BandaDTO.class));
   }

   @GetMapping
   public ResponseEntity<List<BandaDTO>> findAll(){
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(x-> mapper.map(x, BandaDTO.class)).collect(Collectors.toList()));

   }
}
