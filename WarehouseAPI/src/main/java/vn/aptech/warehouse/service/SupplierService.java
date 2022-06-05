/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.warehouse.entity.Supplier;

/**
 *
 * @author thien
 */
public interface SupplierService {
    
    List<Supplier> findAll();
    
    List<Supplier> findByTaxCode(String TaxCode);
    
    List<Supplier> findByName(String sup_name);
    
    Optional<Supplier> findByCode(String sup_code);
    
    Supplier save(Supplier sup);
}
