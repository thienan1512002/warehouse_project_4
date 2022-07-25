/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.ast.Test;
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
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.excelhelper.SupplierEHelper;
import vn.aptech.warehouse.message.ResponseMessage;
import vn.aptech.warehouse.service.ExcelService;
import vn.aptech.warehouse.service.SupplierService;

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
    public Optional<Supplier> findBySupCode(@PathVariable("id") String sup_code) {
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

    @Autowired
    ExcelService fileService;

    @RequestMapping(value="/import", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (SupplierEHelper.hasExcelFormat(file)) {
//            try {
                fileService.save(file);
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
