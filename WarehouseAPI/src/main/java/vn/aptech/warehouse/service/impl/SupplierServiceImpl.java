/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.repository.SupplierRepository;
import vn.aptech.warehouse.service.SupplierService;

/**
 *
 * @author thien
 */
@Service
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierRepository repo;
    
    @Override
    public List<Supplier> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Supplier> findBySupCode(String sup_code) {
        return repo.findBySupCode(sup_code);
    }

    @Override
    public Supplier save(Supplier sup) {
        return repo.save(sup);
    }
    
}
