package vn.aptech.warehouse.controller;

import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.service.WarehouseService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity addBook(@RequestBody Warehouse warehouse) throws URISyntaxException {
        Warehouse wh = service.save(warehouse);
        return ResponseEntity.ok(wh);
    }
}
