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
import vn.aptech.warehouse.service.LocService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/allocated")
public class AllocateController {
    @Autowired
    private GoodsMasterService goodsMasterService;
    
    @Autowired
    private LocService locService;
    
    
    @GetMapping("/browse")
    public String index(Model model){
        model.addAttribute("unallocates", goodsMasterService.findUnallocated(false,""));
        model.addAttribute("locs", locService.findByWhCode("WH001"));
        return "allocate/index";
    }
    
}
