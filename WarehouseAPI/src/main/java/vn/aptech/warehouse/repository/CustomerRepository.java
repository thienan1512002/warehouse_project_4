/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.Customer;

/**
 *
 * @author nhta1
 */
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
    @Query("SELECT o FROM Customer o WHERE o.cust_code =:cust_code")
    public Customer findByCustCode(@Param("cust_code") String cust_code);
}
