/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.service.LocService;
import vn.aptech.warehouse.service.WarehouseService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/locs")
public class LocController {
    @Autowired
    private LocService service;
    
     @Autowired
    private WarehouseService whService;
    
    
    @GetMapping(value="")
    public String index(Model model,HttpServletRequest request){
        model.addAttribute("locs", service.findByWhCode((String) request.getSession().getAttribute("workspace")));
        model.addAttribute("warehouses", whService.findAll());
        return "locs/index";
    }
    
    
    @PostMapping(value="/save")
    public ResponseEntity save(@RequestBody Location location,HttpServletRequest request){
        location.setWh_code((String) request.getSession().getAttribute("workspace"));
        Location loc = service.save(location);
        return ResponseEntity.ok(200);
    } 
}
