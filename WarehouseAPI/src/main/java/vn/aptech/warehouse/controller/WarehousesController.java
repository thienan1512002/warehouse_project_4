package vn.aptech.warehouse.controller;

import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.service.WarehouseService;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/warehouses")
public class WarehousesController {

    @Autowired
    private WarehouseService service;

    @GetMapping(value = "")
    public List<Warehouse> findAll(){
        return service.findAll();
    }
    
     @PostMapping(value = "")
    public ResponseEntity addWarehouse(@RequestBody Warehouse warehouse) throws URISyntaxException {
        Warehouse wh = service.save(warehouse);
        return ResponseEntity.ok(wh);
    }
    
    @GetMapping(value = "/{id}")
    public Optional<Warehouse> findByWhCode(@PathVariable("id") String wh_code){
        return service.findByWhCode(wh_code);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity updateWarehouse(@PathVariable("id") String wh_code , @RequestBody Warehouse warehouse){
        service.save(warehouse);
        return ResponseEntity.ok(200);
    }
}
