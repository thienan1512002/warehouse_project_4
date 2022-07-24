/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.Location;

/**
 *
 * @author nhta1
 */
public interface LocRepository extends JpaRepository<Location, String>{
    @Query("SELECT o from Location o where o.wh_code=:wh_code")
    List<Location> findLocbyWhCode(@Param("wh_code")String whCode);
    @Query("SELECT o FROM Location o WHERE o.loc_code=:loc_code")
    Location findLocByLocCode(@Param("loc_code")String loc_code);
}
