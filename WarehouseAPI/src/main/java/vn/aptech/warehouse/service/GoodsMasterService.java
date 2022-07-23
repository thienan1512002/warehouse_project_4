/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.GoodsMaster;

/**
 *
 * @author nhta1
 */
public interface GoodsMasterService {
    List<GoodsMaster> findAll(String wh);
    
    List<GoodsMaster> findByIcId(int ic_id);
}
