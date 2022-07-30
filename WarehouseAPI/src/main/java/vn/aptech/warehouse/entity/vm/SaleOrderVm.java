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
public class SaleOrderVm {

    private String so_id;
    private String cust_code;
    private LocalDate order_date;
    private LocalDate req_date;
    private String ship;
    private String currency;
    private String address;
}
