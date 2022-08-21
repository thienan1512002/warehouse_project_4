/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="so_detail")
public class SaleOrderDet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String so_id;
    @ManyToOne
    @JoinColumn(name = "goods_no", nullable = false)
    private GoodData good_data;
    private int quantity;
    private int discount;
    private int picked;
    private int booked;
}
