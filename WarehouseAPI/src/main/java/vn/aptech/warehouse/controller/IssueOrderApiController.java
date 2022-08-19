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
import vn.aptech.warehouse.entity.SaleOrderDet;
import vn.aptech.warehouse.entity.Transactions;
import vn.aptech.warehouse.entity.vm.JsObj;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.IssueOrderService;
import vn.aptech.warehouse.service.LocService;
import vn.aptech.warehouse.service.SaleOrderDetService;
import vn.aptech.warehouse.service.TransactionsService;

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
    
     @Autowired
    private TransactionsService transService;
    @Autowired
    private SaleOrderDetService detService;
    
    
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
        
         SaleOrderDet det = detService.findBySoId(order.getSo_id(), gm.getGood_data().getGoods_no());
        
        det.setPicked(det.getPicked()+jsObj.getQty());
        
        SaleOrderDet addDet = detService.save(det);
        
        // add transaction report
        Transactions trans = new Transactions();
        trans.setType("out");
        trans.setFrom_loc(loc.getLoc_desc());
        trans.setTo_loc("Export for Sale Order");
        trans.setGoods_name(gm.getGood_data().getGoods_name());
        trans.setQuantity(jsObj.getQty());
        
        Transactions addTrans = transService.save(trans);
        
        return order;
       
    }
    
}
