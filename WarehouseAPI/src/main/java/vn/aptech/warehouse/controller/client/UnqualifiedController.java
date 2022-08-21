/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.GoodData;
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.entity.Incoming;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.entity.Transactions;
import vn.aptech.warehouse.entity.Unqualified;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.entity.vm.JsObj;
import vn.aptech.warehouse.service.GoodDataService;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.IncomingService;
import vn.aptech.warehouse.service.SupplierService;
import vn.aptech.warehouse.service.TransactionsService;
import vn.aptech.warehouse.service.UnqualifiedService;
import vn.aptech.warehouse.service.WarehouseService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/unqualified")
public class UnqualifiedController {
    
    @Autowired
    private UnqualifiedService service;
    
    @Autowired
    private IncomingService icService;
    
    @Autowired
    private GoodDataService gdService;
    
    @Autowired
    private GoodsMasterService gmService;
    
    @Autowired
    private SupplierService supService;
    
    @Autowired
    private WarehouseService whService;
    
    @Autowired
    private TransactionsService transService;
    
    @GetMapping(value="")
    public String index(Model model , HttpServletRequest request){
        model.addAttribute("unqualifieds", service.findAll((String)request.getSession().getAttribute("workspace")));
        return "unqualified/index";
    }
    
    @PostMapping(value="/handle")
    public ResponseEntity handleItem(@RequestBody JsObj jsObj,HttpServletRequest request){
        int statusCode = 200;
        Unqualified unqualified = service.findById(jsObj.getId());
        unqualified.setStatus(jsObj.getStatus());
       
        String type = jsObj.getStatus()==1? "Recycle":"Disposed";
        unqualified.setAction_qty(unqualified.getAction_qty()+jsObj.getQty());
        unqualified.setNote(unqualified.getNote()+", "+type+": "+jsObj.getNote()+"("+jsObj.getQty()+")");
        String to =  jsObj.getStatus()==1? "Ready for allocated":"Disposed";
        Unqualified editUn = service.save(unqualified);
        Transactions trans = new Transactions();
        trans.setType(type);
        trans.setFrom_loc("Unqualified");
        trans.setTo_loc(to);
        trans.setGoods_name(unqualified.getGoods_name());
        trans.setQuantity(jsObj.getQty());
        
        Transactions addTrans = transService.save(trans);
        if(jsObj.getStatus()==1){
            Supplier sup = supService.findBySupCode("sup-00");
            GoodData goodsData = gdService.findByGoodName(unqualified.getGoods_name());
            Warehouse warehouse = whService.findWHByWhCode((String)request.getSession().getAttribute("workspace"));
            
            Incoming incoming = new Incoming();
            incoming.setClosed(false);
            incoming.setDelivery_date(LocalDate.now());
            incoming.setDriver("");
            incoming.setSupplier(sup);
            incoming.setVehicle("");
            Incoming addIc = icService.save(incoming);
            
            
            GoodsMaster gm = new GoodsMaster();
            gm.setGood_data(goodsData);
            gm.setSupplier(sup);
            gm.setPt_qty(jsObj.getQty());
            gm.setLoc_code("");
            gm.setPassed(false);
            gm.setWarehouse(warehouse);
            gm.setAccepted_qty(0);
            gm.setPt_hold(0);
            gm.setQc("");
            gm.setIc_id(incoming.getIc_id());
            gm.setPatch_no("RECYCLE-"+LocalDate.now().toString());
            gm.setPt_date_in(LocalDate.now());
            GoodsMaster addGm = gmService.save(gm);
        }
        return ResponseEntity.ok(statusCode);
    }
}
