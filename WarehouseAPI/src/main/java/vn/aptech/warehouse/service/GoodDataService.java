/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.warehouse.entity.GoodData;


/**
 *
 * @author Jack
 */

public interface GoodDataService {
    List<GoodData> findAll();
    GoodData findByNo(String no);
    List<GoodData> findByName(String name);
    GoodData save(GoodData goodData);
}
