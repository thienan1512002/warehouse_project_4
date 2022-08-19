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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.aptech.warehouse.entity.Customer;
import vn.aptech.warehouse.entity.GoodData;
import vn.aptech.warehouse.entity.SaleOrder;
import vn.aptech.warehouse.entity.SaleOrderDet;
import vn.aptech.warehouse.entity.vm.JsObj;
import vn.aptech.warehouse.entity.vm.SaleOrderDetVm;
import vn.aptech.warehouse.entity.vm.SaleOrderVm;
import vn.aptech.warehouse.service.CustomerService;
import vn.aptech.warehouse.service.GoodDataService;
import vn.aptech.warehouse.service.GoodsMasterService;
import vn.aptech.warehouse.service.SaleOrderDetService;
import vn.aptech.warehouse.service.SaleOrderService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/sale")
public class SaleOrderController  {
    @Autowired
    private SaleOrderService soService;
    
    @Autowired
    private CustomerService custService;
    
    @Autowired
    private SaleOrderDetService detService;
    
    @Autowired
    private GoodDataService goodService;
    
    @Autowired
    private GoodsMasterService gmService;
    
    @GetMapping(value="/browse")
    public String index(Model model){
        model.addAttribute("sales", soService.findByComplete());
        model.addAttribute("customers", custService.findAll());
        return "sale/index";
    }
    
    @GetMapping(value="/details/{id}")
    public String detail(@PathVariable("id") String so_id,Model model){
        model.addAttribute("sale", soService.findBySoId(so_id));
        model.addAttribute("details", detService.findBySoId(so_id));
        model.addAttribute("goodsData", goodService.findAll());
        return "sale/detail";
    }
    
    @GetMapping(value="/pick-list/{id}/{so_id}")
    public String pickList(@PathVariable("id") String id,@PathVariable("so_id") String so_id,Model model){
        model.addAttribute("goods",gmService.findByInventory(id));
        model.addAttribute("so_id",so_id);
        model.addAttribute("goods_no",id);
        return "sale/pick-item";
    }
    
    
    @PostMapping(value="/close")
    public ResponseEntity close(@RequestBody JsObj jsObj){
        SaleOrder so = soService.findBySoId(jsObj.getSo_id());
        
        so.setClosed(true);
        
        SaleOrder editSo = soService.save(so);
        return ResponseEntity.ok(200);
    }
    
    @PostMapping(value="/add-item")
    public ResponseEntity addItem(@RequestBody SaleOrderDetVm det ){
        
        int responseCode = 500;
        
        GoodData goodData = goodService.findByNo(det.getGoods_no());
        SaleOrderDet soDet = new SaleOrderDet();
        
        soDet.setSo_id(det.getSo_id());
        soDet.setGood_data(goodData);
        soDet.setQuantity(det.getQuantity());
        soDet.setDiscount(det.getDiscount());
        
        SaleOrderDet newSoDet = detService.save(soDet);
        
        if(newSoDet!=null){
            responseCode =200;
        }
        
        return ResponseEntity.ok(responseCode);
    }
    
    @PostMapping(value="/save")
    public ResponseEntity addSaleOrder(@RequestBody SaleOrderVm saleOrder){
        int responseCode=500;
        Customer cust = custService.findByCustomerCode(saleOrder.getCust_code());
        SaleOrder so = new SaleOrder(saleOrder.getSo_id(), cust, saleOrder.getOrder_date(), saleOrder.getReq_date(), saleOrder.getShip(), false, false, saleOrder.getCurrency(), saleOrder.getAddress(), false);
        SaleOrder newSo =  soService.save(so);
        if(newSo!=null){
            responseCode=200;
        }
        return ResponseEntity.ok(responseCode);
    }
}
