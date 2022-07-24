/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.Supplier;

/**
 *
 * @author thien
 */
public interface SupplierService {
    
    List<Supplier> findAll();
    
    Supplier findBySupCode(String sup_code);
    
    Supplier save(Supplier sup);
}
