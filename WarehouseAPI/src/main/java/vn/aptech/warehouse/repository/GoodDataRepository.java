/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.aptech.warehouse.entity.GoodData;

/**
 *
 * @author Jack
 */
public interface GoodDataRepository extends JpaRepository<GoodData, String>{
    @Query("SELECT u FROM GoodData u WHERE u.goods_no = ?1")
    GoodData findByNo(String no);
}
