/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.IssueOrder;

/**
 *
 * @author nhta1
 */
public interface IssueOrderRepo extends JpaRepository<IssueOrder, Integer>{
    
    @Query("SELECT o FROM IssueOrder o WHERE o.closed =:closed AND o.si_code=:si_code AND o.quantity > 0")
    List<IssueOrder> findByClosed(@Param("closed") boolean closed , @Param("si_code") String si_code);
    @Query("SELECT o FROM IssueOrder o WHERE o.id=:id")
    IssueOrder findById(@Param("id") int id);
    @Query("SELECT o FROM IssueOrder o WHERE month(o.movemen_date) between :startMonth and :endMonth and o.si_code=:si_code")
    List<IssueOrder> findInAMonth(@Param("startMonth")int startMonth,@Param("endMonth")int endMonth,@Param("si_code") String si_code);
}
