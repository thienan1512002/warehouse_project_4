/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.service.WarehouseService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    
    @Autowired
    private WarehouseService service;
    
     @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("warehouses", service.findAll());
        return "warehouse/index";
    }
    
    @PostMapping(value="/save")
    public ResponseEntity save(@RequestBody Warehouse warehouse){
       
        boolean duplicate = false;
        if (warehouse.getWh_code().equals("") || warehouse.getWh_desc().equals("")){
            return ResponseEntity.ok(500);
        }
        List<Warehouse> whs = service.findAll();
        for(Warehouse wh : whs){
            if(wh.getWh_code().equals(warehouse.getWh_code())){
                duplicate=true;
            }
        }
        
        if(duplicate){
            return ResponseEntity.ok(501);
        }else{
            Warehouse wh = service.save(warehouse);
        }
         
        return ResponseEntity.ok(200);
    } 
}
