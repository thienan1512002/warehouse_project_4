package vn.aptech.warehouse.controller.client;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.vm.JsObj;

@Controller
public class HomeController {

    @GetMapping(value = "/test-layout")
    public String index(Model model) {
        return "home/index";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/error")
    public String error(Model model) {
        return "error";
    }

    @GetMapping(value = "/check-workspace")
    public ResponseEntity checkWorkspace(HttpServletRequest request) {
        int code = 200;
        if (request.getSession().getAttribute("workspace") == null) {
            code = 400;
        }
        return ResponseEntity.ok(code);
    }

    @PostMapping(value = "/choose-workspace")
    public ResponseEntity chooseWorkspace(HttpServletRequest request, @RequestBody JsObj jsObj) {
        int code = 200;
        if (request.getSession().getAttribute("workspace") != null) {
            request.getSession().removeAttribute("workspace");
            request.getSession().setAttribute("workspace", jsObj.getWh_code());
        }else{
            request.getSession().setAttribute("workspace", jsObj.getWh_code());
        }
        return ResponseEntity.ok(code);
    }
}
