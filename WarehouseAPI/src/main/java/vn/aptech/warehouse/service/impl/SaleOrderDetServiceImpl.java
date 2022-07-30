/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.SaleOrderDet;
import vn.aptech.warehouse.repository.SaleOrderDetRepo;
import vn.aptech.warehouse.service.SaleOrderDetService;

/**
 *
 * @author nhta1
 */

@Service
public class SaleOrderDetServiceImpl implements SaleOrderDetService {

    @Autowired
    private SaleOrderDetRepo repo;
    
    @Override
    public List<SaleOrderDet> findBySoId(String so_id) {
        return repo.findBySoId(so_id);
    }

    @Override
    public SaleOrderDet save(SaleOrderDet saleOrderDet) {
        return repo.save(saleOrderDet);
    }

    @Override
    public void delete(SaleOrderDet det) {
        repo.delete(det);
    }
    
}
