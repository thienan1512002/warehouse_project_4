/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;


import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    User getUserById(int id);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User saveUserNoPass(User user);
//    boolean checkPassword(String oldPass, String newPass);
}
