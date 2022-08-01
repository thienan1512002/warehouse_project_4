/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.aptech.warehouse.entity.Role;
import vn.aptech.warehouse.entity.User;
import vn.aptech.warehouse.entity.vm.UserVm;
import vn.aptech.warehouse.repository.RoleRepository;
import vn.aptech.warehouse.repository.UserRepository;
import vn.aptech.warehouse.service.UserService;

/**
 *
 * @author Jack
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{
    @Autowired
    private  UserRepository userRepo;
    @Autowired
    private  RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user== null){
            log.error("User not found in DB");
            throw new UsernameNotFoundException("User not found in DB");
        }else if(user!=null && user.getActive()==true){
            log.info("User found in DB: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role-> 
        {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);
    }
    @Override
    public User saveUser(User user) {
        log.info("Save new user {} to database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Save new role {} to database", role.getName());
        return roleRepo.save(role); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName, username );
        User user = userRepo.findByUsername(username); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
        userRepo.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).get(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User saveUserNoPass(User user) {
        log.info("Update user {} without password", user.getUsername());
        return userRepo.save(user); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserVm getUserAndroid(UserVm user) {
        User currentUser = userRepo.findByUsername(user.getUsername());
        if(currentUser==null){
            return null;
        }
        return user;
    }



    
    
}
