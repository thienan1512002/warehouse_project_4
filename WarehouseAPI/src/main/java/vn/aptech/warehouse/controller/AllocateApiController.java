/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
import vn.aptech.warehouse.entity.AllocateRequest;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.service.AllocateRequestService;
import vn.aptech.warehouse.service.WarehouseService;

/**
 *
 * @author nhta1
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin()
public class AllocateApiController {
    
    
    @Autowired
    private AllocateRequestService service;
    
    @Autowired
    private WarehouseService whService;
    
    @GetMapping(value="/allocate")
    public List<AllocateRequest> findAll(){
        Warehouse wh = whService.findWHByWhCode("WH001");
        return service.findbyConfirm(true,wh);
    }
    
    @GetMapping(value="/allocate/{id}")
    public AllocateRequest findById(@PathVariable("id") int id){
        return service.findById(id);
    }
}
