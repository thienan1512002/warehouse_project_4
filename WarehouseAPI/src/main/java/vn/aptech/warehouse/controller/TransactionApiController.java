/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.Transactions;
import vn.aptech.warehouse.service.GoodDataService;
import vn.aptech.warehouse.service.TransactionsService;

/**
 *
 * @author Jack
 */
@RestController
@RequestMapping(value="/api/transaction")
public class TransactionApiController {
    @Autowired
    private TransactionsService service;
    
    @GetMapping(value="/")
    public List<Transactions> findAll(){
        return service.findAll();
    }
    
    @GetMapping(value="/{name}")
    public List<Transactions> findByName(@PathVariable("name") String goods_name){
        return service.findByName(goods_name);
    }
}
