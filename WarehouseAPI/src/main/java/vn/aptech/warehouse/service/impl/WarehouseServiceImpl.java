package vn.aptech.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.repository.WarehouseRepository;
import vn.aptech.warehouse.service.WarehouseService;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository repo;

    @Override
    public List<Warehouse> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Warehouse> findByWhCode(String whCode) {
        return repo.findById(whCode);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return repo.save(warehouse);
    }

    @Override
    public Warehouse findWHByWhCode(String wh_code) {
        return repo.findByWhCode(wh_code);
    }
}
