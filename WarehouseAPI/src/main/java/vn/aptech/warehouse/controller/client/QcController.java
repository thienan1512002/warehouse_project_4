/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.entity.Incoming;
import vn.aptech.warehouse.entity.vm.GoodsMasterVm;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.IncomingService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/qc")
public class QcController {
    @Autowired
    private IncomingService incomingService;
    @Autowired
    private GoodsMasterService goodsMasterService;
    @GetMapping(value="")
    public String index(Model model){
        List<Incoming> qcs = new ArrayList<>(); //incomingService.findByClosed(true);
        incomingService.findByClosed(true).forEach(x->{
            if(!x.getGoods().isEmpty()){
                qcs.add(x);
            }
        });
        model.addAttribute("incomings",qcs);
        return "qc/index";
    }
    
    @PostMapping(value="/qc-check")
    public ResponseEntity qcCheck(@RequestBody GoodsMasterVm goodsMaster,Authentication authentication){
        GoodsMaster gm = goodsMasterService.findByPtId(goodsMaster.getPt_id());
        gm.setAccepted_qty(gm.getPt_qty());
        gm.setQc(authentication.getName());
        gm.setPassed(true);
        gm.setLoc_code("");
        GoodsMaster edit = goodsMasterService.save(gm);
        
        return ResponseEntity.ok(200);
    }
}
