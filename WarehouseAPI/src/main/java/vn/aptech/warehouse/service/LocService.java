/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.warehouse.entity.Location;

/**
 *
 * @author nhta1
 */
public interface LocService {
    List<Location> findByWhCode(String wh_code);
    
    Optional<Location> findById(String wh_code);
    
    Location save(Location loc);
    
    Location findByLocCode(String loc_code);
}
