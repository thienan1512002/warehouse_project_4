/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nhta1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="goods_master")
public class GoodsMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pt_id;
    
    private float pt_qty;
    
    private float accepted_qty;
    
    private float pt_hold;
    
    private LocalDate pt_date_in;
    
    private String patch_no;
    
    private String qc;
    
    private boolean passed;
    
    @ManyToOne
    @JoinColumn(name="goods_no",nullable = false)
    private GoodData good_data;
    @ManyToOne
    @JoinColumn(name="loc_code",nullable = false)
    private Location location;
    @ManyToOne
    @JoinColumn(name="sup_code",nullable = false)
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name="wh_code",nullable = false)
    private Warehouse warehouse;
}
