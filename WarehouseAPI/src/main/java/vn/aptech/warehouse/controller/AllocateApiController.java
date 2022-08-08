/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.AllocateRequest;
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.entity.vm.JsObj;
import vn.aptech.warehouse.service.AllocateRequestService;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.LocService;
import vn.aptech.warehouse.service.WarehouseService;

/**
 *
 * @author nhta1
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin()
public class AllocateApiController {
    
    
    @Autowired
    private AllocateRequestService service;
    @Autowired
    private LocService serviceLoc;
    @Autowired
    private WarehouseService whService;
    
    @Autowired
    private GoodsMasterService serviceGM;
    
    
    @GetMapping(value="/allocate")
    public List<AllocateRequest> findAll(){
        Warehouse wh = whService.findWHByWhCode("WH001");
        return service.findbyConfirm(false,wh);
    }
    
    @GetMapping(value="/allocate/{id}")
    public AllocateRequest findById(@PathVariable("id") int id){
        return service.findById(id);
    }
    @PostMapping(value="/allocate/confirm")
    public AllocateRequest confirmOrder(@RequestBody JsObj jsObj) {
        AllocateRequest ar = service.findById(jsObj.getId());
        ar.setConfirm(true);
        AllocateRequest edit = service.save(ar);
        Location loc = serviceLoc.findByLocDesc(jsObj.getLoc_desc());
        loc.setLoc_cap(loc.getLoc_cap() + jsObj.getQty());
        loc.setLoc_holding(loc.getLoc_holding() - jsObj.getQty());
        Location editLoc = serviceLoc.save(loc);
        GoodsMaster gm = serviceGM.findByPtId(ar.getGoods_masters().getPt_id());
        gm.setLoc_code(loc.getLoc_code());
        gm.setPt_hold(gm.getPt_hold() - jsObj.getQty());

        GoodsMaster gm1 = serviceGM.save(gm);

        return ar;
    }
    
    @PostMapping(value="/allocate/decline")
    public AllocateRequest declineOrder(@RequestBody JsObj jsObj){
        AllocateRequest result = service.findById(jsObj.getId());
        AllocateRequest ar = service.findById(jsObj.getId());
        GoodsMaster gm = serviceGM.findByPtId(ar.getGoods_masters().getPt_id());
        gm.setPt_hold(gm.getPt_hold() - ar.getAlc_moved_qty());

        GoodsMaster gm1 = serviceGM.save(gm);
        Location loc = serviceLoc.findByLocCode(ar.getLocation().getLoc_code());
        loc.setLoc_holding(loc.getLoc_holding() - ar.getAlc_moved_qty());
        Location editLoc = serviceLoc.save(loc);
        service.delete(ar);
        return result;
    }
    
}
