/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.AllocateRequest;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.repository.AllocateRequestRepo;
import vn.aptech.warehouse.service.AllocateRequestService;

/**
 *
 * @author nhta1
 */
@Service
public class AllocateRequestServiceImpl implements AllocateRequestService{

    @Autowired
    private AllocateRequestRepo repo;
    
    
    @Override
    public List<AllocateRequest> findbyConfirm(boolean confirm, Warehouse warehouse) {
        return repo.findByConfirm(confirm, warehouse);
    }

    @Override
    public AllocateRequest save(AllocateRequest allocateRequest) {
        return repo.save(allocateRequest);
    }

    @Override
    public AllocateRequest findById(int id) {
        return repo.findById(id);
    }

    @Override
    public void delete(AllocateRequest allocateRequest) {
        repo.delete(allocateRequest);
    }
    
}
