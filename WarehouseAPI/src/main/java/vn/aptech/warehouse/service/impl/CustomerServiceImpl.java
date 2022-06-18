/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Customer;
import vn.aptech.warehouse.repository.CustomerRepository;
import vn.aptech.warehouse.service.CustomerService;

/**
 *
 * @author nhta1
 */
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository repo;
    @Override
    public List<Customer> findAll() {
       return repo.findAll();
    }

    @Override
    public Optional<Customer> findByCustomerCode(String customerCode) {
        return repo.findById(customerCode);
    }

    @Override
    public Customer save(Customer customer) {
        return repo.save(customer);
    }
    
}
