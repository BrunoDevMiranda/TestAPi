package com.bruno.test.adapter.controller;

import com.bruno.test.adapter.dto.BandaDTO;
import com.bruno.test.data.Banda;
import com.bruno.test.service.BandService;
import jakarta.servlet.Servlet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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

   @PostMapping
    public ResponseEntity<BandaDTO> save(@RequestBody BandaDTO obj){
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
               .buildAndExpand(service.save(obj)
                       .getId())
               .toUri();
        return ResponseEntity.created(uri).build();
   }


}
