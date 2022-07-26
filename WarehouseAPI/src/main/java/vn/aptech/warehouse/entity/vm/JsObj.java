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
}
