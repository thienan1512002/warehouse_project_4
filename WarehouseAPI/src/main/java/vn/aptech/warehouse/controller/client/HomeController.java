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
        Warehouse warehouse = whService.findWHByWhCode((String)request.getSession().getAttribute("workspace"));
        
        model.addAttribute("locations", locService.findByWhCode((String)request.getSession().getAttribute("workspace")));
        List<AllocateRequest> requests8 = aloService.findInAMonth(warehouse,8,8);
        List<AllocateRequest> requests7 = aloService.findInAMonth(warehouse,7,7);
        List<AllocateRequest> requests6 = aloService.findInAMonth(warehouse,6,6);
        List<AllocateRequest> requests5 = aloService.findInAMonth(warehouse,5,5);
        List<AllocateRequest> requests4 = aloService.findInAMonth(warehouse,4,3);
        List<AllocateRequest> requests3 = aloService.findInAMonth(warehouse,3,2);
        List<AllocateRequest> requests2 = aloService.findInAMonth(warehouse,2,1);
        List<AllocateRequest> requests1 = aloService.findInAMonth(warehouse,1,1);
        List<AllocateRequest> requests9 = aloService.findInAMonth(warehouse,9,9);
        List<AllocateRequest> requests10 = aloService.findInAMonth(warehouse,10,10);
        List<AllocateRequest> requests11 = aloService.findInAMonth(warehouse,11,11);
        List<AllocateRequest> requests12 = aloService.findInAMonth(warehouse,12,12);
        
       
        model.addAttribute("jan",requests1.size());
        model.addAttribute("feb",requests2.size());
        model.addAttribute("thi",requests3.size());
        model.addAttribute("apr",requests4.size());
        model.addAttribute("may",requests5.size());
        model.addAttribute("jun",requests6.size());
        model.addAttribute("july",requests7.size());
        model.addAttribute("aug",requests8.size());
        model.addAttribute("sep",requests9.size());
        model.addAttribute("oct",requests10.size());
        model.addAttribute("nov",requests11.size());
        model.addAttribute("dec",requests12.size());
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
