/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.service.GoodsMasterService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/inventory")
public class GoodsMasterController {
    @Autowired
    private GoodsMasterService service;
    
    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("inventories", service.findAll("WH001"));
        return "inventory/index";
    }
}
