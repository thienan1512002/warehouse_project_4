/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.warehouse.entity.Customer;

/**
 *
 * @author nhta1
 */
public interface CustomerService {
    List<Customer> findAll();
    
    Optional<Customer> findByCustomerCode(String customerCode);
    
    Customer save(Customer customer);
}
