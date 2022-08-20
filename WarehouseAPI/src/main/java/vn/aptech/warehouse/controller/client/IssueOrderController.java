/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
//import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.entity.IssueOrder;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.entity.SaleOrderDet;
import vn.aptech.warehouse.entity.Transactions;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.entity.vm.JsObj;
//import vn.aptech.warehouse.service.EmailSenderService;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.IssueOrderService;
import vn.aptech.warehouse.service.LocService;
import vn.aptech.warehouse.service.SaleOrderDetService;
import vn.aptech.warehouse.service.TransactionsService;
import vn.aptech.warehouse.service.WarehouseService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/Issue")
public class IssueOrderController {
    @Autowired
    private IssueOrderService service;
    @Autowired
    private WarehouseService whService;
    
     @Autowired
    private LocService locService;
    
    @Autowired
    private GoodsMasterService gmService;
    
    @Autowired
    private TransactionsService transService;
    @Autowired
    private SaleOrderDetService detService;
    
    
//    @Autowired
//    private EmailSenderService mailService;
 
    @GetMapping(value="")
    public String index(Model model,HttpServletRequest request){
        Warehouse wh = whService.findWHByWhCode("WH001");
        model.addAttribute("issues", service.findByConfirm(false, (String)request.getSession().getAttribute("workspace")));
        return "issue/index";
    }
   
//    @PostMapping(value="/send-email")
//    public ResponseEntity sendEmail(@RequestBody JsObj jsObj) throws MessagingException, UnsupportedEncodingException{
//        mailService.sendImportGoodsRequest("nhta151202@gmail.com", jsObj.getNote());
//        return ResponseEntity.ok(200);
//    }

    
    
    @PostMapping(value="/confirm")
    public ResponseEntity confirmOrder(@RequestBody JsObj jsObj){
        
        Location location = locService.findByLocCode(jsObj.getLoc_code());
        
        
        location.setLoc_remain(location.getLoc_remain()+jsObj.getQty());
        Location loc = locService.save(location);
        
        GoodsMaster gm = gmService.findByPtId(jsObj.getPt_id());
        
        gm.setPt_qty(gm.getPt_qty()-jsObj.getQty());
        gm.setPt_hold(gm.getPt_hold()-jsObj.getQty());
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
        return ResponseEntity.ok(200);
       
    }
    
    @GetMapping(value="/{id}")
    public String details(@PathVariable("id") int id,Model model){
        IssueOrder issue = service.findById(id);
        model.addAttribute("issue",issue);
        return "issue/detail";
    }
    
    @PostMapping(value="/create-issue")
    public ResponseEntity createIssue(@RequestBody List<JsObj> jsArr,HttpServletRequest request){
         int code = 200;
        jsArr.forEach(jsObj->{
            GoodsMaster gm = gmService.findByPtId(jsObj.getPt_id());
            IssueOrder issueOrder = new IssueOrder();
            issueOrder.setGoods_master(gm);
            issueOrder.setGoods_name(jsObj.getGoods_name());
            issueOrder.setLocations(jsObj.getLoc_desc());
            issueOrder.setMovemen_date(jsObj.getDate());
            issueOrder.setClosed(false);
            issueOrder.setSi_code((String)request.getSession().getAttribute("workspace"));
            issueOrder.setQuantity(jsObj.getQty());
            issueOrder.setSo_id(jsObj.getSo_id());
            IssueOrder createIs = service.save(issueOrder);
            
            gm.setPt_hold(gm.getPt_hold()+jsObj.getQty());
            
            GoodsMaster createGM = gmService.save(gm);
            
           
        });
        return ResponseEntity.ok(code);
    }
}
