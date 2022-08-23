/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.SaleOrder;
import vn.aptech.warehouse.repository.SaleOrderRepo;
import vn.aptech.warehouse.service.SaleOrderService;

/**
 *
 * @author nhta1
 */
@Service
public class SaleOrderServiceImpl implements SaleOrderService {
    
    @Autowired
    private SaleOrderRepo repo;

    @Override
    public List<SaleOrder> findByComplete() {
       return repo.findByCompleted();
    }

    @Override
    public SaleOrder save(SaleOrder saleOrder) {
        return repo.save(saleOrder);
    }

    @Override
    public SaleOrder findBySoId(String so_id) {
        return repo.findBySoId(so_id);
    }

    @Override
    public List<SaleOrder> findAll() {
        return repo.findAll();
    }
    
}
