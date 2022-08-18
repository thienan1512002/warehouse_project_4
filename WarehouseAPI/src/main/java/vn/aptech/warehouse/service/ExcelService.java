package vn.aptech.warehouse.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.warehouse.entity.Customer;
import vn.aptech.warehouse.entity.Supplier;
import vn.aptech.warehouse.excelhelper.CustomerEHelper;
import vn.aptech.warehouse.excelhelper.SupplierEHelper;
import vn.aptech.warehouse.repository.CustomerRepository;
import vn.aptech.warehouse.repository.SupplierRepository;

@Service
public class ExcelService {

    @Autowired
    SupplierRepository repository;
    
    @Autowired
    CustomerRepository cust_rep;
    
    public void save(MultipartFile file) {
        try {
            List<Supplier> suppliers = SupplierEHelper.excelToSuppliers(file.getInputStream());
            repository.saveAll(suppliers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void saveCust(MultipartFile file) {
        try {
            List<Customer> customers = CustomerEHelper.excelToCustomers(file.getInputStream());
            cust_rep.saveAll(customers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
