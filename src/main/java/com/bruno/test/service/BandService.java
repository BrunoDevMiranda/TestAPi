package com.bruno.test.service;

import com.bruno.test.data.Banda;

import java.util.List;

public interface BandService {
    Banda findById(Integer id);
    List<Banda> findAll();
}
