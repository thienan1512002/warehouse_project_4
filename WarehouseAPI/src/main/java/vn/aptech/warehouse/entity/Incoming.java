/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="incomings")
public class Incoming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ic_id;
    @ManyToOne
    @JoinColumn(name="sup_code",nullable = false)
    private Supplier supplier;
    private String vehicle;
    private String driver;
    private LocalDate delivery_date;
    private boolean closed;
    
    @OneToMany(mappedBy = "ic_id",cascade = CascadeType.ALL)
    private List<GoodsMaster> goods;
         
}
