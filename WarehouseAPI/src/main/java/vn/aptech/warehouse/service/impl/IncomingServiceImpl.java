/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Incoming;
import vn.aptech.warehouse.repository.IncomingRepo;
import vn.aptech.warehouse.service.IncomingService;

/**
 *
 * @author nhta1
 */
@Service
public class IncomingServiceImpl implements IncomingService{
    
    @Autowired
    private IncomingRepo repo;
    
    @Override
    public List<Incoming> findByClosed(boolean closed) {
        return repo.findByClosed(closed);
    }

    @Override
    public Incoming findByIcId(int id) {
        return repo.findIncoming(id);
    }

    @Override
    public Incoming save(Incoming incoming) {
        return repo.save(incoming);
    }
    
}
