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
import vn.aptech.warehouse.entity.GoodData;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.service.GoodDataService;
import vn.aptech.warehouse.service.LocService;

/**
 *
 * @author Jack
 */
@RestController
@RequestMapping(value="/api/goods")
public class GoodsApiController {
    @Autowired
    private GoodDataService service;
    
    @GetMapping(value="/")
    public List<GoodData> findAll(){
        return service.findAll();
    }
    
//    @GetMapping(value="/{id}")
//    public GoodData findById(@PathVariable("id") String goods_no){
//        return service.findByNo(goods_no);
//    }
    @GetMapping(value="/{name}")
    public GoodData findByName(@PathVariable("name") String goods_name){
        return service.findByNo(goods_name);
    }

}
