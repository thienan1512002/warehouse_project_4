/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.SaleOrderDet;

/**
 *
 * @author nhta1
 */
public interface SaleOrderDetService {
    List<SaleOrderDet> findBySoId(String so_id);
    
    SaleOrderDet save(SaleOrderDet saleOrderDet);
    
    void delete(SaleOrderDet det);
}
