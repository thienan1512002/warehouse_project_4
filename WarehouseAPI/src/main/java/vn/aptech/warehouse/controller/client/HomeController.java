package vn.aptech.warehouse.controller.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.AllocateRequest;
import vn.aptech.warehouse.entity.IssueOrder;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.entity.vm.JsObj;
import vn.aptech.warehouse.entity.vm.NotyfVm;
import vn.aptech.warehouse.service.AllocateRequestService;
import vn.aptech.warehouse.service.IssueOrderService;
import vn.aptech.warehouse.service.LocService;
import vn.aptech.warehouse.service.WarehouseService;

@Controller
public class HomeController {

    @Autowired
    private LocService locService;
    
    @Autowired
    private AllocateRequestService aloService;
    @Autowired
    private IssueOrderService issueService;
    @Autowired
    private WarehouseService whService;
    
    @GetMapping(value = "")
    public String index(Model model,HttpServletRequest request) {       
        model.addAttribute("locations", locService.findByWhCode((String)request.getSession().getAttribute("workspace")));
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
