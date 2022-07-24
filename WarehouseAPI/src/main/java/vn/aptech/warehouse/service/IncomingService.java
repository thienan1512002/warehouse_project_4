/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.Incoming;

/**
 *
 * @author nhta1
 */
public interface IncomingService {
    public List<Incoming> findByClosed(boolean closed);
    
    public Incoming findByIcId(int id);
    
    public Incoming save(Incoming incoming);
}
