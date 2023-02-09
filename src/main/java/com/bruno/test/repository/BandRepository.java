package com.bruno.test.repository;

import com.bruno.test.data.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Banda, Integer> {

}
