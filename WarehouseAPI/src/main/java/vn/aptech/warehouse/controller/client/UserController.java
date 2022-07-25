/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.aptech.warehouse.entity.Role;
import vn.aptech.warehouse.entity.User;
import vn.aptech.warehouse.service.RoleService;
import vn.aptech.warehouse.service.UserService;

/**
 *
 * @author Jack
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleService serviceRole;
    
    @GetMapping(value="")
    public String getUsers(Model model) {
        List<User> users = service.getUsers();
        model.addAttribute("users", users);
        List<Role> roles = serviceRole.getRoles();
        model.addAttribute("roles", roles);
        
        return "user/index2";
    }
    
    @GetMapping(value="/create")
    public String create(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = serviceRole.getRoles();
        model.addAttribute("roles", roles);
        
        return "user/create2";
    }
    @GetMapping(value="/update/{id}")
    public String update(Model model,@PathVariable("id")int id) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = serviceRole.getRoles();
        model.addAttribute("roles", roles);
        
        return "user/update";
    }
    
    @PostMapping(value="/save")
    public String save(User user,String role){
//        Warehouse wh = service.save(warehouse);
        service.saveUser(user);
        service.addRoleToUser(user.getUsername(), role);
//        return ResponseEntity.ok(200);
        return "redirect:/user";
    }
    @PostMapping(value="/save2")
    public String saveUpdate(User user, String password){
        UserDetails updateUser = service.loadUserByUsername(user.getUsername());
        System.out.println(updateUser.getPassword());
        if(password== null){
            user.setPassword(updateUser.getPassword());
        }else{
            user.setPassword(password);
        }
        
        service.saveUser(user);
//        service.addRoleToUser(user.getUsername(), role);
//        return ResponseEntity.ok(200);
        return "redirect:/user";
    } 
//    @RequestMapping(value="/username", method=RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(HttpServletRequest request){
//        Principal principal = request.getUserPrincipal();
//        return principal.getName();
//    }
}