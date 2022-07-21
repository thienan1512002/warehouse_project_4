/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.warehouse.entity.GoodsMaster;

/**
 *
 * @author nhta1
 */
public interface GoodsMasterRepository extends JpaRepository<GoodsMaster, String> {
    
}
