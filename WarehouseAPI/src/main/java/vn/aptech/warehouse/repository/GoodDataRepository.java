/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.GoodData;

/**
 *
 * @author Jack
 */
public interface GoodDataRepository extends JpaRepository<GoodData, String>{
    @Query("SELECT u FROM GoodData u WHERE u.goods_no = ?1")
    GoodData findByNo(String no);
    @Query("SELECT u FROM GoodData u WHERE u.goods_name like %:name%")
    List<GoodData> findByName(@Param("name")String name);
    @Query("SELECT u FROM GoodData u WHERE u.goods_name like %:name%")
    GoodData findByGoodName(@Param("name")String name);
}
