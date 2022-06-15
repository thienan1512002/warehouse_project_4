/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.aptech.warehouse.entity.Role;
import vn.aptech.warehouse.entity.RoleToUserForm;
import vn.aptech.warehouse.entity.User;
import vn.aptech.warehouse.service.UserService;

/**
 *
 * @author Jack
 */
@RestController
@RequestMapping(value="/api/users")
public class UsersController {
    @Autowired
    private UserService userService;
    
    @GetMapping(value="/")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    
    @PostMapping(value="/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping(value="/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role ){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping(value="/role/addtouser")
    public ResponseEntity<?> addRole(@RequestBody RoleToUserForm form ){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    
    
}
