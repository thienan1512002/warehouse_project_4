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
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.excelhelper.SupplierEHelper;
import vn.aptech.warehouse.message.ResponseMessage;
import vn.aptech.warehouse.service.ExcelService;
import vn.aptech.warehouse.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author thien
 */
@RestController
@RequestMapping("/api/suppliers/")
public class SuppliersController {

    @Autowired
    private SupplierService service;

    @GetMapping(value = "/")
    public List<Supplier> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/details/{id}")
    public Supplier findBySupCode(@PathVariable("id") String sup_code) {
        return service.findBySupCode(sup_code);
    }

    @PostMapping(value = "")
    public ResponseEntity addSupplier(@RequestBody Supplier supplier) {
        Supplier sl = service.save(supplier);
        return ResponseEntity.ok(sl);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateSupplier(@PathVariable("id") String sup_code, @RequestBody Supplier supplier) {
        service.save(supplier);
        return ResponseEntity.ok(200);
    }

//    @Autowired
//    ExcelService fileService;
//
//    @RequestMapping(value = "/import", method = RequestMethod.POST)
//    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
//        String message = "";
//        if (SupplierEHelper.hasExcelFormat(file)) {
//            try {
//                fileService.save(file);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                ra.addFlashAttribute("message", message);
//                return "redirect:/suppliers";
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                ra.addFlashAttribute("message", message);
//                return "redirect:/suppliers";
//            }
//        }
//        message = "Please upload an excel file!";
//        ra.addFlashAttribute("message", message);
//        return "redirect:/suppliers";
//    }
    
    @GetMapping(value = "/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=suppliers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Supplier> suppliers = service.findAll();
         
        SupplierEHelper excelExporter = new SupplierEHelper(suppliers);
         
        excelExporter.export(response);    
    }
}
