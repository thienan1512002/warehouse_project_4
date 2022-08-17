package vn.aptech.warehouse.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.service.TransactionsService;

@Controller
@RequestMapping("/transaction")
public class TranactionsController {
    @Autowired
    private TransactionsService service;

    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("trans", service.findAll());
        return "transaction/index";
    }
}
