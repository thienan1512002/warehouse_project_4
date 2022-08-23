/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.aptech.warehouse.entity.Customer;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.excelhelper.CustomerEHelper;
import vn.aptech.warehouse.excelhelper.SupplierEHelper;
import vn.aptech.warehouse.message.ResponseMessage;
import vn.aptech.warehouse.service.CustomerService;
import vn.aptech.warehouse.service.ExcelService;

@Controller
@RequestMapping("/customer")
public class CustomerClientController {
    @Autowired
    private CustomerService service;
    
    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("customers", service.findAll());
      
        return "customer/index";
    }
    
    @PostMapping(value="/save")
    public ResponseEntity save(@RequestBody Customer customer){
        
        int code =200;
        Customer cust = service.save(customer);
        if (cust.getCust_code().equals("")  ){
            code= 500;
        }
        return ResponseEntity.ok(code);
    }
    
    @Autowired
    ExcelService fileService;

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        String message = "";
        String message1 = "";
        String message2 = "";
        if (SupplierEHelper.hasExcelFormat(file)) {
            try {
                fileService.saveCust(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                ra.addFlashAttribute("message", message);
                return "redirect:/customer";
            } catch (Exception e) {
                message1 = "Could not upload the file: " + file.getOriginalFilename() + "!";
                ra.addFlashAttribute("message", message1);
                return "redirect:/customer";
            }
        }
        message2 = "Please upload an excel file!";
        ra.addFlashAttribute("message", message2);
        return "redirect:/customer";
    }
}
