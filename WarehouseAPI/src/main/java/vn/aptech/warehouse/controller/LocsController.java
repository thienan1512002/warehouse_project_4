/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.service.LocService;

/**
 *
 * @author nhta1
 */
@RestController
@RequestMapping(value="/api/locs")
public class LocsController {
    
    @Autowired
    private LocService service;
    
    @GetMapping(value="/{wh_code}")
    public List<Location> findAll(@PathVariable("wh_code") String wh_code){
        return service.findByWhCode(wh_code);
    }
    
    @GetMapping(value="/details/{id}")
    public Optional<Location> findById(@PathVariable("id") String loc_code){
        return service.findById(loc_code);
    }
    
    @PostMapping(value="")
    public ResponseEntity addLocation(@RequestBody Location loc){
       Location location = service.save(loc);
       return ResponseEntity.ok(location);
    }
    
    @PutMapping(value="/update/{id}")
     public ResponseEntity updateLocation(@PathVariable("id") String loc_code,@RequestBody Location loc){
       Location location = service.save(loc);
       return ResponseEntity.ok(location);
    }
}
