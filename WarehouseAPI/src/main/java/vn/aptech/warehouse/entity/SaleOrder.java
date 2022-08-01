/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "sale_order")
public class SaleOrder {

    @Id
    private String so_id;
    @ManyToOne
    @JoinColumn(name = "cust_code", nullable = false)
    private Customer customer;
    private LocalDate order_date;
    private LocalDate req_date;
    private String ship;
    private boolean confirm;
    private boolean closed;
    private String currency;
    private String address;
    private boolean complete;
   
}
