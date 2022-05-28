package vn.aptech.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.warehouse.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,String> {
}
