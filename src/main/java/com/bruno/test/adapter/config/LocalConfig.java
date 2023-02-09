package com.bruno.test.adapter.config;

import com.bruno.test.data.Banda;
import com.bruno.test.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private BandRepository repository;
    @Bean
    public void startDB(){
        Banda band1 = new Banda(1,"Metallica","HeavyMetal");
        Banda band2 = new Banda(2,"Megadeath","HeavyMetal");
        Banda band3 = new Banda(3,"Slayer","HeavyMetal");
        Banda band4 = new Banda(4,"Anthrax","HeavyMetal");

        repository.saveAll(List.of(band1,band2,band3,band4));
    }
}
