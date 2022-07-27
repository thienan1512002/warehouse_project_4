/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.AllocateRequest;
import vn.aptech.warehouse.entity.Warehouse;

/**
 *
 * @author nhta1
 */
public interface AllocateRequestService {
    
    public List<AllocateRequest> findbyConfirm(boolean confirm , Warehouse warehouse);
    
    public AllocateRequest save(AllocateRequest allocateRequest);
    
    public AllocateRequest findById(int id);
    
    public void delete(AllocateRequest allocateRequest);
}
