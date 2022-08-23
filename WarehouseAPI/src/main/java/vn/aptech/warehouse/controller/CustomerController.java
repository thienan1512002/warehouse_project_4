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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.warehouse.entity.Customer;
import vn.aptech.warehouse.excelhelper.CustomerEHelper;
import vn.aptech.warehouse.message.ResponseMessage;
import vn.aptech.warehouse.service.CustomerService;
import vn.aptech.warehouse.service.ExcelService;

/**
 *
 * @author nhta1
 */
@RestController
@RequestMapping(value="/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    
    @GetMapping(value = "")
    public List<Customer> findAll(){
        return service.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Customer findByCustomerCode(@PathVariable("id")String customerCode){
        return service.findByCustomerCode(customerCode);
    }
    
    @PostMapping(value="/")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        Customer cust = service.save(customer);
        return ResponseEntity.ok(cust);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") String customerCode , @RequestBody Customer customer){
        service.save(customer);
        return ResponseEntity.ok(200);
    }

    //Excel
//    @Autowired
//    ExcelService fileService;
//
//    @RequestMapping(value = "/import", method = RequestMethod.POST)
//    public ResponseEntity<ResponseMessage> uploadFile1(@RequestParam("file") MultipartFile file) {
//        String message = "";
//        if (CustomerEHelper.hasExcelFormat(file)) {
//            try {
//                fileService.saveCust(file);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
//        }
//        message = "Please upload an excel file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
//    }
    
    @GetMapping(value = "/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Customer> customers = service.findAll();
         
        CustomerEHelper excelExporter = new CustomerEHelper(customers);
         
        excelExporter.export(response);    
    }
}
