/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jack
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="goods_data")
public class GoodData implements Serializable {
    @Id
    private String goods_no;
    private String image;
    private String goods_name;
    private int price;
    private boolean active;
    private String goods_package;
    private String qty_per_package;
    @Transient
    public String getPhotosImagePath() {
        if (image == null || goods_no == null) return null;
         
        return "/goods-photos/" + goods_no + "/" + image;
    }
}
