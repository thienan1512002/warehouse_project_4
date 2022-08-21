/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity.vm;

import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author nhta1
 */
@Data
public class JsObj {
    private String loc_code;
    private int pt_id;
    private int qty;
    private LocalDate date;
    private int id;
    private String so_id;
    private int alc_id;
    private String loc_desc;
    private String note;
    private String goods_name; 
    private String goods_no;
    private String wh_code;
    private int status;
   
}
