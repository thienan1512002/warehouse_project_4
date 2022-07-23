/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.Incoming;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.entity.vm.IncomingVm;
import vn.aptech.warehouse.service.IncomingService;
import vn.aptech.warehouse.service.SupplierService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/Incoming")
public class IncomingController {
    
    @Autowired
    private IncomingService service;
    @Autowired
    private SupplierService supService;
    
    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("sups", supService.findAll());
        model.addAttribute("incomings", service.findAll());
        return "incoming/index";
    }
    
    @PostMapping(value="/save")
    public ResponseEntity save(@RequestBody IncomingVm incoming){
        Supplier sup = supService.findBySupCode(incoming.getSup_code());
        Incoming newIncome = new Incoming();
        newIncome.setDelivery_date(incoming.getDelivery_date());
        newIncome.setSupplier(sup);
        newIncome.setDriver(incoming.getDriver());
        newIncome.setVehicle(incoming.getVehicle());
        newIncome.setClosed(incoming.isClosed());
        
        Incoming add = service.save(newIncome);
        
        return ResponseEntity.ok(200);
    }
    
    
}
