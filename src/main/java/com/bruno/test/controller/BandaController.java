package com.bruno.test.controller;

import com.bruno.test.data.Banda;
import com.bruno.test.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/{band}")
public class BandaController {
    @Autowired
    private BandService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<Banda>findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
   }
}
