/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.service.LocService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/locs")
public class LocController {
    @Autowired
    private LocService service;
    
    
    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("locs", service.findByWhCode("WH001"));
        return "locs/index";
    }
}
