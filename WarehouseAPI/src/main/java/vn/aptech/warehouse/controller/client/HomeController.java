package vn.aptech.warehouse.controller.client;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value="/test-layout")
    public String index(Model model){
        return "layout/layout";
    }
}
