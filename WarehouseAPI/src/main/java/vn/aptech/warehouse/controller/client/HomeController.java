package vn.aptech.warehouse.controller.client;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @GetMapping(value="/test-layout")
    public String index(Model model){
        return "home/index";
    }
    @GetMapping(value="/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping(value="/error")
    public String error(Model model){
        return "error";
    }
}
