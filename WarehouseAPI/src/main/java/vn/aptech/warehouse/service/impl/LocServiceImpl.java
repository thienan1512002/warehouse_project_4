/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.repository.LocRepository;
import vn.aptech.warehouse.service.LocService;

/**
 *
 * @author nhta1
 */
@Service
public class LocServiceImpl implements LocService{

    @Autowired
    private LocRepository repo;
    
    @Override
    public List<Location> findByWhCode(String wh_code) {
        return repo.findLocbyWhCode(wh_code);
    }

    @Override
    public Optional<Location> findById(String loc_code) {
       return repo.findById(loc_code);
    }

    @Override
    public Location save(Location loc) {
        return repo.save(loc);
    }
    
}
