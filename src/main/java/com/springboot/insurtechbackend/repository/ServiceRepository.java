package com.springboot.insurtechbackend.repository;

import com.springboot.insurtechbackend.model.AutoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<AutoService, Integer> {
    List<AutoService> findAllByType(int type);
    List<AutoService> findAll();

}
