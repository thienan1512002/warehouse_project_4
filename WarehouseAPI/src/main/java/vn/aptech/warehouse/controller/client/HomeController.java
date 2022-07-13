package vn.aptech.warehouse.controller.client;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @GetMapping(value="/test-layout")
    public String index(Model model){
        return "home/index";
    }
    @GetMapping(value="/login")
    public String login(Model model){
        return "login";
    }
}
