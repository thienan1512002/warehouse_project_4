/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.Supplier;

/**
 *
 * @author thien
 */
public interface SupplierRepository extends JpaRepository<Supplier, Object>{
    @Query("SELECT b FROM Supplier b WHERE b.sup_code=:sup_code")
    Supplier findBySupCode(@Param("sup_code")String sup_code);
}
