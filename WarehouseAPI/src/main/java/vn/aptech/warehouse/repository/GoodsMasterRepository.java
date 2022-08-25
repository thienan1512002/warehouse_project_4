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
import vn.aptech.warehouse.entity.Warehouse;

/**
 *
 * @author nhta1
 */
public interface GoodsMasterRepository extends JpaRepository<GoodsMaster, String> {
    
    @Query("SELECT o FROM GoodsMaster o WHERE o.ic_id =:id")
    public List<GoodsMaster> findByIcId(@Param("id") int ic_id);
    @Query("SELECT o FROM GoodsMaster o WHERE o.pt_id=:id")
    public GoodsMaster findByPtId(@Param("id") int pt_id);
    
    @Query("SELECT o FROM GoodsMaster o WHERE o.passed=:passed and o.loc_code=:loc_code")
    public List<GoodsMaster> findUnallocated(@Param("passed") boolean pass, @Param("loc_code")String loc_code);
    
    @Query("SELECT o FROM GoodsMaster o WHERE o.pt_qty > 0 and o.good_data.goods_no =:id and o.loc_code!=:loc_code")
    public List<GoodsMaster> findGoodsLists(@Param("id") String id , @Param("loc_code")String loc_code);
     @Query("SELECT o FROM GoodsMaster o WHERE o.loc_code!=:loc_code and o.warehouse =:warehouse")
    public List<GoodsMaster> findIncome(@Param("loc_code")String loc_code,@Param("warehouse")Warehouse warehouse);
    
    
}
