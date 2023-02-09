package com.bruno.test.service;

import com.bruno.test.adapter.dto.BandaDTO;
import com.bruno.test.data.Banda;
import com.bruno.test.exceptions.ObjectNotFoundException;
import com.bruno.test.repository.BandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BandServiceImpel implements BandService {

    @Autowired
    private BandRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Banda findById(Integer id) {
        Optional<Banda> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Banda não foi encontrada"));
    }

    @Override
    public List<Banda> findAll() {
        return repository.findAll();
    }

    @Override
    public Banda save(BandaDTO obj) {
        return repository.save(mapper.map(obj, Banda.class));
    }
}
