/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import com.google.firebase.messaging.FirebaseMessagingException;
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
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.entity.Location;
import vn.aptech.warehouse.entity.Note;
import vn.aptech.warehouse.entity.Transactions;
import vn.aptech.warehouse.entity.Warehouse;
import vn.aptech.warehouse.entity.vm.JsObj;
import vn.aptech.warehouse.service.AllocateRequestService;
import vn.aptech.warehouse.service.FirebaseMessagingService;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.LocService;
import vn.aptech.warehouse.service.TransactionsService;
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
    
    @Autowired
    private TransactionsService transService;
    
    @Autowired
    private FirebaseMessagingService serviceMessage;

    @GetMapping("/browse")
    public String index(Model model) {
        model.addAttribute("unallocates", goodsMasterService.findUnallocated(true, ""));
        model.addAttribute("locs", locService.findByWhCode("WH001"));
        return "allocate/index";
    }

    @GetMapping("")
    public String allocated(Model model,HttpServletRequest request) {
        Warehouse warehouse = whService.findWHByWhCode((String)request.getSession().getAttribute("workspace"));
        model.addAttribute("allocates", aloService.findbyConfirm(false, warehouse));
        return "allocate/request";
    }

    @PostMapping("/decline-order")
    public ResponseEntity declineOrder(@RequestBody JsObj jsObj) {
        AllocateRequest ar = aloService.findById(jsObj.getId());
        GoodsMaster gm = goodsMasterService.findByPtId(ar.getGoods_masters().getPt_id());
        gm.setPt_hold(gm.getPt_hold() - ar.getAlc_moved_qty());

        GoodsMaster gm1 = goodsMasterService.save(gm);
        Location loc = locService.findByLocCode(ar.getLocation().getLoc_code());
        loc.setLoc_holding(loc.getLoc_holding() - ar.getAlc_moved_qty());
        Location editLoc = locService.save(loc);
        aloService.delete(ar);
        return ResponseEntity.ok(200);
    }

    @PostMapping("/confirm-order")
    public ResponseEntity confirmOrder(@RequestBody JsObj jsObj) {
        AllocateRequest ar = aloService.findById(jsObj.getId());
        ar.setConfirm(true);
        AllocateRequest edit = aloService.save(ar);
        Location loc = locService.findByLocCode(ar.getLocation().getLoc_code());
        loc.setLoc_remain(loc.getLoc_remain()- ar.getAlc_moved_qty());
        loc.setLoc_holding(loc.getLoc_holding() - ar.getAlc_moved_qty());
        Location editLoc = locService.save(loc);
        GoodsMaster gm = goodsMasterService.findByPtId(ar.getGoods_masters().getPt_id());
        gm.setLoc_code(loc.getLoc_code());
        gm.setPt_hold(gm.getPt_hold() - ar.getAlc_moved_qty());

        GoodsMaster gm1 = goodsMasterService.save(gm);
        
        // add transaction report
        Transactions trans = new Transactions();
        trans.setType("in");
        trans.setFrom_loc("UnAllocated");
        trans.setTo_loc(loc.getLoc_desc());
        trans.setGoods_name(gm.getGood_data().getGoods_name());
        trans.setQuantity(ar.getAlc_moved_qty());
        
        Transactions addTrans = transService.save(trans);

        return ResponseEntity.ok(200);
    }

    @PostMapping("/pick-list")
    public ResponseEntity pickList(@RequestBody JsObj jsObj) throws FirebaseMessagingException {
        int responseCode;

        GoodsMaster goods = goodsMasterService.findByPtId(jsObj.getPt_id());

        Location loc = locService.findByLocCode(jsObj.getLoc_code());
        loc.setLoc_holding(loc.getLoc_holding() + jsObj.getQty());
        Location edit = locService.save(loc);
        Warehouse wh = whService.findWHByWhCode(loc.getWh_code());

        AllocateRequest ar = new AllocateRequest();

        ar.setGoods_masters(goods);
        ar.setConfirm(false);
        ar.setAlc_moved_qty(jsObj.getQty());
        ar.setLocation(loc);
        ar.setWarehouse(wh);
        ar.setMovement_time(jsObj.getDate());

        AllocateRequest newAr = aloService.save(ar);

        goods.setPt_hold((goods.getPt_hold()) + jsObj.getQty());

        GoodsMaster editGoods = goodsMasterService.save(goods);

        responseCode = 400;

        if (newAr != null) {
            responseCode = 200;
        }
        Note notiMess = new Note();
        notiMess.setSubject("New Allocate Request");
        notiMess.setContent("An new allocate request wait for your approval");

        serviceMessage.sendNotification(notiMess, "sale");
        return ResponseEntity.ok(responseCode);
    }

}
