/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this licens
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.entity.IssueOrder;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.entity.vm.JsObj;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.IssueOrderService;
import vn.aptech.warehouse.service.LocService;

/**
 *
 * @author nhta1
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin()
public class IssueOrderApiController {
    
    @Autowired
    private IssueOrderService service;
    
    @Autowired
    private LocService locService;
    
    @Autowired
    private GoodsMasterService gmService;
    
    
    @GetMapping(value="/issue")
    public List<IssueOrder> findByClosed(boolean closed , String si_code){
        return service.findByConfirm(false, "WH001");
    }
    
    @GetMapping(value="/issue/{id}")
    public IssueOrder findById(@PathVariable("id") int id){
        return service.findById(id);
    }
    
    @PostMapping(value="/issue/confirm")
    public IssueOrder confirmOrder(@RequestBody JsObj jsObj){
        Location location = locService.findByLocCode(jsObj.getLoc_code());
        
        location.setLoc_holding(location.getLoc_holding()-jsObj.getQty());
        
        Location loc = locService.save(location);
        
        GoodsMaster gm = gmService.findByPtId(jsObj.getPt_id());
        
        gm.setPt_qty(gm.getPt_qty()-jsObj.getQty());
        gm.setAccepted_qty(gm.getAccepted_qty()-jsObj.getQty());
        
        GoodsMaster editGm = gmService.save(gm);
        
        IssueOrder order = service.findById(jsObj.getId());
        
        order.setClosed(true);
        
        IssueOrder editOrder = service.save(order);
        
        return order;
       
    }
    
}
