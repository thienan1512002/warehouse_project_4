package vn.aptech.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,String> {
    @Query("SELECT o FROM Warehouse o WHERE o.wh_code=:wh_code")
    Warehouse findByWhCode(@Param("wh_code")String wh_code);
    
}
