package com.bruno.test.repository;

import com.bruno.test.data.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Banda, Integer> {

    Optional<Banda> findByEmail(String email);
}
