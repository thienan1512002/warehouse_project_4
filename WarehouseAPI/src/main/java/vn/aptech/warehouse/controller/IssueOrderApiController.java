/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this licens
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.IssueOrder;
import vn.aptech.warehouse.service.IssueOrderService;

/**
 *
 * @author nhta1
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin()
public class IssueOrderApiController {
    
    @Autowired
    private IssueOrderService service;
    
    @GetMapping(value="/issue")
    public List<IssueOrder> findByClosed(boolean closed , String si_code){
        return service.findByConfirm(false, "WH001");
    }
    
    @GetMapping(value="/issue/{id}")
    public IssueOrder findById(@PathVariable("id") int id){
        return service.findById(id);
    }
    
}
