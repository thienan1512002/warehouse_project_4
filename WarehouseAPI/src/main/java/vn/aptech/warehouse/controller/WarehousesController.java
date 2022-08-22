package vn.aptech.warehouse.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.service.WarehouseService;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.aptech.warehouse.entity.AllocateRequest;
import vn.aptech.warehouse.entity.IssueOrder;
import vn.aptech.warehouse.entity.vm.NotyfVm;
import vn.aptech.warehouse.service.AllocateRequestService;
import vn.aptech.warehouse.service.IssueOrderService;

@RestController
@RequestMapping(value = "/api/warehouses")
public class WarehousesController {

    @Autowired
    private WarehouseService service;
    
     @Autowired
    private AllocateRequestService aloService;
    @Autowired
    private IssueOrderService issueService;
   

    @GetMapping(value = "")
    public List<Warehouse> findAll(){
        return service.findAll();
    }
    
     @GetMapping(value = "/notyf")
    public List<NotyfVm> findAllNotyf(HttpServletRequest request){
        List<NotyfVm> data = new ArrayList<>();
        
        List<IssueOrder> issues = issueService.findByConfirm(false,(String)request.getSession().getAttribute("workspace"));
        Warehouse wh = service.findWHByWhCode((String)request.getSession().getAttribute("workspace"));
        List<AllocateRequest> alos = aloService.findbyConfirm(false, wh);
        
        issues.forEach(x->{
            NotyfVm noti = new NotyfVm();
            noti.setId(Integer.toString(x.getId()));
            noti.setContent("There is an issue order about "+x.getGoods_name()+" wait for your requests");
            noti.setTime(x.getMovemen_date());
            noti.setUrl("http://localhost:8080/Issue/"+x.getId());
            
            data.add(noti);
        });
        
        alos.forEach(x->{
            NotyfVm noti = new NotyfVm();
            noti.setId(Integer.toString(x.getAlc_id()));
            noti.setContent("There is an allocate order about "+x.getGoods_masters().getGood_data().getGoods_name()+" wait for your requests");
            noti.setTime(x.getMovement_time());
            noti.setUrl("http://localhost:8080/allocated");           
            data.add(noti);
        });
        return data;       
    }
    
     @PostMapping(value = "")
    public ResponseEntity addWarehouse(@RequestBody Warehouse warehouse) throws URISyntaxException {
        Warehouse wh = service.save(warehouse);
        return ResponseEntity.ok(wh);
    }
    
    @GetMapping(value = "/{id}")
    public Optional<Warehouse> findByWhCode(@PathVariable("id") String wh_code){
        return service.findByWhCode(wh_code);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity updateWarehouse(@PathVariable("id") String wh_code , @RequestBody Warehouse warehouse){
        service.save(warehouse);
        return ResponseEntity.ok(200);
    }
}
