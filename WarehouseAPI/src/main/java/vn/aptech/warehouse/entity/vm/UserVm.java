/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.entity.vm;

import java.util.Collection;
import lombok.Data;
import vn.aptech.warehouse.entity.Role;

/**
 *
 * @author nhta1
 */
@Data
public class UserVm {
    private String username;
    private String password;
    private String email;
    private Collection<Role> role;
}
