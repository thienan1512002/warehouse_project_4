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
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.service.SupplierService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService service;
    
     @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("suppliers", service.findAll());
        return "supplier/index";
    }
    
    @PostMapping(value="/save")
    public ResponseEntity save(@RequestBody Supplier supplier){
        Supplier sl = service.save(supplier);
        return ResponseEntity.ok(200);
    } 
}
