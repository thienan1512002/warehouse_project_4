/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.AllocateRequest;
import vn.aptech.warehouse.entity.Warehouse;

/**
 *
 * @author nhta1
 */
public interface AllocateRequestRepo extends JpaRepository<AllocateRequest, Integer>{
    
    @Query("SELECT o FROM AllocateRequest o WHERE o.confirm=:confirm and o.warehouse =:warehouse")
    public List<AllocateRequest> findByConfirm(@Param("confirm") boolean confirm , @Param("warehouse") Warehouse warehouse);
    
    @Query(value="SELECT o FROM AllocateRequest o WHERE o.confirm=true and month(o.movement_time) between :startMonth and :endMonth and o.warehouse =:warehouse")
    public List<AllocateRequest> findInAMonth(@Param("warehouse") Warehouse warehouse ,@Param("startMonth")int startMonth,@Param("endMonth")int endMonth);
    
    @Query("SELECT o FROM AllocateRequest o WHERE o.alc_id=:id")
    public AllocateRequest findById(@Param("id") int id);
}
