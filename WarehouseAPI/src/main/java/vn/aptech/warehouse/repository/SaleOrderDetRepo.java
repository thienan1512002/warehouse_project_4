/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.SaleOrderDet;

/**
 *
 * @author nhta1
 */
public interface SaleOrderDetRepo extends JpaRepository<SaleOrderDet, Integer> {

    @Query("SELECT o FROM SaleOrderDet o WHERE o.so_id=:id")
    public List<SaleOrderDet> findBySoId(@Param("id") String so_id);
    
     @Query("SELECT o FROM SaleOrderDet o WHERE o.so_id LIKE %:id% and o.good_data.goods_no=:goods_no")
    public SaleOrderDet findBySoId(@Param("id") String so_id,@Param("goods_no")String goods_no);
}
