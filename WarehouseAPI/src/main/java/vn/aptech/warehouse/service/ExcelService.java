package vn.aptech.warehouse.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.excelhelper.SupplierEHelper;
import vn.aptech.warehouse.repository.SupplierRepository;

@Service
public class ExcelService {

    @Autowired
    SupplierRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Supplier> suppliers = SupplierEHelper.excelToSuppliers(file.getInputStream());
            repository.saveAll(suppliers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }
    
//    public ByteArrayInputStream load() {
//        List<Supplier> suppliers = repository.findAll();
//        ByteArrayInputStream in = SupplierEHelper.suppliersToExcel(suppliers);
//        return in;
//    }
}
