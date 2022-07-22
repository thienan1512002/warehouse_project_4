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
import vn.aptech.warehouse.service.IncomingService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/Incoming")
public class IncomingController {
    
    @Autowired
    private IncomingService service;
    
    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("incomings", service.findAll());
        return "incoming/index";
    }
}
