/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
@RequestMapping("/allocated")
public class AllocateController {
    @Autowired
    private GoodsMasterService goodsMasterService;
    
    @Autowired
    private LocService locService;
    
    @Autowired
    private WarehouseService whService;
    
    @Autowired
    private AllocateRequestService aloService;
    
    @GetMapping("/browse")
    public String index(Model model){
        model.addAttribute("unallocates", goodsMasterService.findUnallocated(false,""));
        model.addAttribute("locs", locService.findByWhCode("WH001"));
        return "allocate/index";
    }
    
    @PostMapping("/pick-list")
    public ResponseEntity pickList(@RequestBody JsObj jsObj){
        int responseCode;
        
        GoodsMaster goods = goodsMasterService.findByPtId(jsObj.getPt_id());
        
        Location loc = locService.findByLocCode(jsObj.getLoc_code());
        
        Warehouse wh = whService.findWHByWhCode(loc.getWh_code());
        
        AllocateRequest ar = new AllocateRequest();
        
        ar.setGoods_masters(goods);
        ar.setConfirm(false);
        ar.setAlc_moved_qty(jsObj.getQty());
        ar.setLocation(loc);
        ar.setWarehouse(wh);
        ar.setMovement_time(jsObj.getDate());
        
        AllocateRequest newAr = aloService.save(ar);
        
        goods.setPt_hold((goods.getPt_hold())+jsObj.getQty());
        
        GoodsMaster editGoods = goodsMasterService.save(goods);
        
        responseCode = 400;
        
        if(newAr != null){
            responseCode = 200;
        }
        
        return ResponseEntity.ok(goods);
    }
    
}
