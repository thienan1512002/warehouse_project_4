///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package vn.aptech.warehouse.entity;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
///**
// *
// * @author Jack
// */
//public class WarehouseUserDetails implements UserDetails{
//    private User user;
//
//    public WarehouseUserDetails(User user) {
//        this.user=user;
//    }
//    
//    
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<Role> roles = user.getRoles();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for(Role role:roles){
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//}
