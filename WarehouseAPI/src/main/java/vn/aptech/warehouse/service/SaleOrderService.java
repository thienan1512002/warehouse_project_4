/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.SaleOrder;

/**
 *
 * @author nhta1
 */
public interface SaleOrderService {
    List<SaleOrder> findByComplete();
    
    SaleOrder findBySoId(String so_id);
    
    SaleOrder save(SaleOrder saleOrder);
    
    List<SaleOrder> findAll();
}
