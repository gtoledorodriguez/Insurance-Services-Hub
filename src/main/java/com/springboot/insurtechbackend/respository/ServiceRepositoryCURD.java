package com.springboot.insurtechbackend.respository;

import com.springboot.insurtechbackend.model.AutoService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepositoryCURD extends CrudRepository<AutoService, Integer> {
    List<AutoService> findAll();
}
