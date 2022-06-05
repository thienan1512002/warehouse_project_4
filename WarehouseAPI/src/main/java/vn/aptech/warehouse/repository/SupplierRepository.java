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
    @Query("SELECT o from supplier o where o.TaxCode=:TaxCode")
    List<Supplier> findByTaxCode(@Param("TaxCode")String TaxCode);
    
    @Query("SELECT a from supplier a where a.sup_name=:sup_name")
    List<Supplier> findByName(@Param("sup_name")String sup_name);
    
    @Query("SELECT b from supplier b where b.sup_code=:sup_code")
    Optional<Supplier> findByCode(@Param("sup_code")String sup_code);
}
