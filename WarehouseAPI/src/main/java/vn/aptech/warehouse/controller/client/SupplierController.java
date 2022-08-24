/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import vn.aptech.warehouse.excelhelper.SupplierEHelper;
import vn.aptech.warehouse.service.ExcelService;
import vn.aptech.warehouse.service.SupplierService;

/**
 *
 * @author nhta1
 */
@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService service;
    
    @GetMapping(value="")
    public String index(Model model){
        model.addAttribute("suppliers", service.findAll());
        return "supplier/index";
    }
    
    @PostMapping(value="/save")
    public ResponseEntity save(@RequestBody Supplier supplier){
        
        
        boolean duplicate = false;
        List<Supplier> whs = service.findAll();
        for(Supplier wh : whs){
            if(wh.getSup_code().equals(supplier.getSup_code())){
                duplicate=true;
            }
        }
        
        if(duplicate){
            return ResponseEntity.ok(501);
        }else{
            Supplier wh = service.save(supplier);
        }
        
        return ResponseEntity.ok(200);
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
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                ra.addFlashAttribute("message", message);
                return "redirect:/suppliers";
            } catch (Exception e) {
                message1 = "Could not upload the file: " + file.getOriginalFilename() + "!";
                ra.addFlashAttribute("message", message1);
                return "redirect:/suppliers";
            }
        }
        message2 = "Please upload an excel file!";
        ra.addFlashAttribute("message", message2);
        return "redirect:/suppliers";
    }
}
