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
import vn.aptech.warehouse.entity.Customer;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.service.CustomerService;

/**
 *
 * @author nhta1
 */
@RestController
@RequestMapping(value="/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    
    @GetMapping(value = "")
    public List<Customer> findAll(){
        return service.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Customer findByCustomerCode(@PathVariable("id")String customerCode){
        return service.findByCustomerCode(customerCode);
    }
    
    @PostMapping(value="/")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        Customer cust = service.save(customer);
        return ResponseEntity.ok(cust);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") String customerCode , @RequestBody Customer customer){
        service.save(customer);
        return ResponseEntity.ok(200);
    }
}
