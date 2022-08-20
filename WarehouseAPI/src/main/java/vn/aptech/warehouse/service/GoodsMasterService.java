/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.entity.Warehouse;

/**
 *
 * @author nhta1
 */
public interface GoodsMasterService {
    List<GoodsMaster> findAll(Warehouse wh);
    
    List<GoodsMaster> findByIcId(int ic_id);
    
    GoodsMaster save(GoodsMaster goodsMaster);
    
    GoodsMaster findByPtId(int pt_id);
    
    List<GoodsMaster> findUnallocated(boolean closed , String loc_code);
    
    List<GoodsMaster> findByInventory(String id);
}
