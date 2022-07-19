/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thien
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="supplier")
public class Supplier {
    @Id
    private String sup_code;
    private String sup_name;
    private String sup_address;
    private String sup_email;
    private String city;
    private String tax_code;
    private Boolean active;
}
