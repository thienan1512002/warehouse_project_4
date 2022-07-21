/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.GoodData;
import vn.aptech.warehouse.repository.GoodDataRepository;
import vn.aptech.warehouse.service.GoodDataService;

/**
 *
 * @author Jack
 */
@Service
public class GoodDataServiceImpl implements GoodDataService{

    @Autowired
    private GoodDataRepository repo;
    @Override
    public List<GoodData> findAll() {
        return repo.findAll(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
    }

    @Override
    public GoodData save(GoodData goodData) {
        return repo.save(goodData);
    }

    @Override
    public GoodData findByNo(String no) {
        return repo.findByNo(no); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
