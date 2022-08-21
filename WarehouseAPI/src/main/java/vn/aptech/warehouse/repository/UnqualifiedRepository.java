/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.Unqualified;

/**
 *
 * @author nhta1
 */
public interface UnqualifiedRepository extends JpaRepository<Unqualified, Integer>{
    
    @Query("SELECT o FROM Unqualified o WHERE o.si_code =:si_code")
    List<Unqualified> findBySiCode(@Param("si_code")String si_code);
    
}
