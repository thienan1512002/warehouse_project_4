/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.GoodsMaster;

/**
 *
 * @author nhta1
 */
public interface GoodsMasterRepository extends JpaRepository<GoodsMaster, String> {
    
    @Query("SELECT o FROM GoodsMaster o WHERE o.ic_id =:id")
    public List<GoodsMaster> findByIcId(@Param("id") int ic_id);
}
