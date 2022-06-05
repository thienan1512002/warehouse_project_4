/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.repository.SupplierRepository;
import vn.aptech.warehouse.service.SupplierService;

/**
 *
 * @author thien
 */
public class SupplierServiceImpl implements SupplierService{
    @Autowired
    private SupplierRepository repo;
    
    @Override
    public List<Supplier> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Supplier> findByTaxCode(String TaxCode) {
        return repo.findByTaxCode(TaxCode);
    }

    @Override
    public List<Supplier> findByName(String sup_name) {
        return repo.findByName(sup_name);
    }

    @Override
    public Optional<Supplier> findByCode(String sup_code) {
        return repo.findByCode(sup_code);
    }

    @Override
    public Supplier save(Supplier sup) {
        return repo.save(sup);
    }
    
}
