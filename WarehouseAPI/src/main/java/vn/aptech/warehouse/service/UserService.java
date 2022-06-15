/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;


import java.util.List;
import vn.aptech.warehouse.entity.Role;
import vn.aptech.warehouse.entity.User;

/**
 *
 * @author Jack
 */
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    //UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
