/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.service.UnqualifiedService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/unqualified")
public class UnqualifiedController {
    
    @Autowired
    private UnqualifiedService service;
    
    @GetMapping(value="")
    public String index(Model model , HttpServletRequest request){
        model.addAttribute("unqualifieds", service.findAll((String)request.getSession().getAttribute("workspace")));
        return "unqualified/index";
    }
}
