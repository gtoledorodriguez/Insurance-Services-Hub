package com.springboot.insurtechbackend.respository;

import com.springboot.insurtechbackend.model.AutoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class textRepository {
    @Autowired
    ServiceRepositoryCURD serviceRepositoryCURD;
    @Test
    public void main1() {
//        Iterable<AutoService> sdf =serviceRepositoryCURD.findAll();
        Optional<AutoService> sdf1 =serviceRepositoryCURD.findById(1);
        System.out.println(sdf1.get());
    }
}
