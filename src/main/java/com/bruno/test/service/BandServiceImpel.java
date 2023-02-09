package com.bruno.test.service;

import com.bruno.test.data.Banda;
import com.bruno.test.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BandServiceImpel implements BandService {

    @Autowired
    private BandRepository repository;
    @Override
    public Banda findById(Integer id) {
        Optional<Banda> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
