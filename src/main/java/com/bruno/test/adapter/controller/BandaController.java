package com.bruno.test.adapter.controller;

import com.bruno.test.adapter.dto.BandaDTO;

import com.bruno.test.service.BandService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<BandaDTO> save(@RequestBody BandaDTO obj){
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
               .buildAndExpand(service.save(obj)
                       .getId())
               .toUri();
        return ResponseEntity.created(uri).build();
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
