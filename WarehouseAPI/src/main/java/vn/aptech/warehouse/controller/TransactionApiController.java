/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.warehouse.entity.Transactions;
import vn.aptech.warehouse.excelhelper.CustomerEHelper;
import vn.aptech.warehouse.excelhelper.TransactionEHelper;
import vn.aptech.warehouse.service.TransactionsService;

/**
 *
 * @author Jack
 */
@RestController
@RequestMapping(value="/api/transaction")
public class TransactionApiController {
    @Autowired
    private TransactionsService service;
    
    @GetMapping(value="")
    public List<Transactions> findAll(){
        return service.findAll();
    }
    
    @GetMapping(value="/{name}")
    public List<Transactions> findByName(@PathVariable("name") String goods_name){
        return service.findByName(goods_name);
    }
    
    @GetMapping(value = "/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transactions_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Transactions> transactions = findAll();
            
        TransactionEHelper excelExporter = new TransactionEHelper(transactions);
         
        excelExporter.export(response);    
    }
}
