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
public class IncomingVm {
    private int ic_id;
    private String sup_code;
    private String vehicle;
    private String driver;
    private LocalDate delivery_date;
    private boolean closed;
}
