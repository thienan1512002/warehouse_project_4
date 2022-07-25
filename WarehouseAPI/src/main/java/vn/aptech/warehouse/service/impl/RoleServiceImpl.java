/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.warehouse.entity.Role;
import vn.aptech.warehouse.repository.RoleRepository;
import vn.aptech.warehouse.service.RoleService;

/**
 *
 * @author Jack
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository repo;

    @Override
    public List<Role> getRoles() {
        return repo.findAll(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
