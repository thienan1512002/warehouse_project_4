/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.Unqualified;

/**
 *
 * @author nhta1
 */
public interface UnqualifiedService {
    List<Unqualified> findAll(String wh_code);
    
    Unqualified findById(int id);
    
    Unqualified save(Unqualified unqualified);
    
    List<Unqualified> findItem(String wh_code);
}
