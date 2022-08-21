/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="unqualified")
public class Unqualified {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String goods_name;
    private int quantity;
    private String so_id;
    private int status;
    private String si_code;
    private String note;
}
