/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.IssueOrder;
import vn.aptech.warehouse.repository.IssueOrderRepo;
import vn.aptech.warehouse.service.IssueOrderService;

/**
 *
 * @author nhta1
 */
@Service
public class IssueOrderServiceImpl implements IssueOrderService {

    @Autowired
    private IssueOrderRepo repo;
    
    @Override
    public List<IssueOrder> findByConfirm(boolean closed, String si_code) {
        return repo.findByClosed(closed, si_code);
    }

    @Override
    public IssueOrder findById(int id) {
       return repo.findById(id);
    }

    @Override
    public IssueOrder save(IssueOrder issueOrder) {
        return repo.save(issueOrder);
    }
    
}
