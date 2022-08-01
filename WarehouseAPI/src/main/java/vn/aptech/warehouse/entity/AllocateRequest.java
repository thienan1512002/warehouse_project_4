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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "allocate_request")
public class AllocateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alc_id;

    private int alc_moved_qty;

    private boolean confirm;

    private LocalDate movement_time;

    @ManyToOne
    @JoinColumn(name = "pt_id", nullable = false)
    private GoodsMaster goods_masters;

    @ManyToOne
    @JoinColumn(name = "loc_code", nullable = false)
    private Location location;
    
    @ManyToOne
    @JoinColumn(name="si_code", nullable = false)
    private Warehouse warehouse;
}
