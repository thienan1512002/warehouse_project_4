/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Unqualified;
import vn.aptech.warehouse.repository.UnqualifiedRepository;
import vn.aptech.warehouse.service.UnqualifiedService;

/**
 *
 * @author nhta1
 */
@Service
public class UnqualifiedServiceImpl implements UnqualifiedService {

    @Autowired
    private UnqualifiedRepository repo;
    
    @Override
    public List<Unqualified> findAll(String wh_code) {
       return repo.findBySiCode(wh_code);
    }

    @Override
    public Unqualified findById(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Unqualified save(Unqualified unqualified) {
       return repo.save(unqualified);
    }

    @Override
    public List<Unqualified> findItem(String wh_code) {
        return repo.findItem(wh_code);
    }
    
}
