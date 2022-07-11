/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.service.SupplierService;

/**
 *
 * @author thien
 */
@RestController
@RequestMapping("/api/suppliers/")
public class SuppliersController {
    @Autowired
    private SupplierService service;
    
    @GetMapping(value="/")
    public List<Supplier> findAll(){
        return service.findAll();
    }

    @GetMapping(value="/details/{id}")
    public Optional<Supplier> findBySupCode(@PathVariable("id") String sup_code){
        return service.findBySupCode(sup_code);
    }
    
    @PostMapping(value="")
    public ResponseEntity addSupplier(@RequestBody Supplier sup){
       Supplier supplier = service.save(sup);
       return ResponseEntity.ok(supplier);
    }
    
    @PutMapping(value="/update/{id}")
     public ResponseEntity updateSupplier(@PathVariable("id") String sup_code,@RequestBody Supplier sup){
       Supplier supplier = service.save(sup);
       return ResponseEntity.ok(supplier);
    }
}
