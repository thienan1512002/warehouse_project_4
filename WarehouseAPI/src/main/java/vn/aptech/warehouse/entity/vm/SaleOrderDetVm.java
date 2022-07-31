/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity.vm;

import lombok.Data;

/**
 *
 * @author nhta1
 */
@Data
public class SaleOrderDetVm {
    private int id;
    private String so_id;
    private String goods_no;
    private int quantity;
    private int discount;
}
