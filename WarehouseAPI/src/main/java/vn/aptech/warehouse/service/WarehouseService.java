package vn.aptech.warehouse.service;

import vn.aptech.warehouse.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
     List<Warehouse> findAll();

     Optional<Warehouse> findByWhCode(String whCode);

     Warehouse save(Warehouse warehouse);

}
