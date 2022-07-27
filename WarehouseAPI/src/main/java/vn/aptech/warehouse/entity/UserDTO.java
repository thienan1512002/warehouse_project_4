/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Jack
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UserDTO implements Serializable {
    @Id
    private int id;
    
    private String username;
    
    private String email;
//    private String password;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
