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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue_order")
public class IssueOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String locations;
    private int quantity;
    private String goods_name;
    @ManyToOne
    @JoinColumn(name = "pt_id", nullable = false)
    private GoodsMaster goods_master;
    
    private boolean closed;
    
    private String si_code;
    
    private LocalDate movemen_date;
}
