/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.GoodsMaster;
import vn.aptech.warehouse.repository.GoodsMasterRepository;
import vn.aptech.warehouse.service.GoodsMasterService;

/**
 *
 * @author nhta1
 */
@Service
public class GoodsMasterServiceImpl implements GoodsMasterService {

    @Autowired
    private GoodsMasterRepository service;
    
    @Override
    public List<GoodsMaster> findAll(String wh) {
       return service.findAll();
    }

    @Override
    public List<GoodsMaster> findByIcId(int ic_id) {
       return service.findByIcId(ic_id);
    }

    @Override
    public GoodsMaster save(GoodsMaster goodsMaster) {
        return service.save(goodsMaster);
    }

    @Override
    public GoodsMaster findByPtId(int pt_id) {
        return service.findByPtId(pt_id);
    }

    @Override
    public List<GoodsMaster> findUnallocated(boolean closed, String loc_code) {
        return service.findUnallocated(closed, loc_code);
    }

    @Override
    public List<GoodsMaster> findByInventory(String id) {
        return service.findGoodsLists(id, "");
    }
    
}
