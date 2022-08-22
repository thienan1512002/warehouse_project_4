/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller.client;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger log;
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
    @GetMapping(value="/update-role/{id}")
    public String showUpdateRole(Model model, @PathVariable("id")int id) {
        User user = service.getUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = serviceRole.getRoles();
        model.addAttribute("listRoles", roles);
        return "user/role";
    }
    @GetMapping(value="/profile")
    public String showProfile(Model model) {
        UserDetails loggedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = service.getUser(loggedUser.getUsername());
        model.addAttribute("user", user);
        List<Role> roles = serviceRole.getRoles();
        model.addAttribute("roles", roles);
        return "user/profile";
    }
    
    
    @PostMapping(value="/save3")
    public String updateRole(User user, RedirectAttributes ra){
        User saveUser = service.getUserById(user.getId());
        saveUser.setRoles(user.getRoles());
        service.saveUserNoPass(saveUser);
        ra.addFlashAttribute("message", "User has been updated.");
        return "redirect:/user";
    }
    @PostMapping(value="/save")
    public String save(User user, RedirectAttributes ra){
        User saveUser = new User();
        saveUser.setUsername(user.getUsername());
        saveUser.setEmail(user.getEmail());
        saveUser.setPassword(user.getPassword());
        saveUser.setActive(true);
//        Collection<Role> userRole = new ArrayList<Role>();
//        Role role = 
//        userRole.add("ROLE_USER");
//        saveUser.setRoles(roles);
        service.saveUser(saveUser);
//        service.addRoleToUser(saveUser.getUsername(), "ROLE_USER");
        ra.addFlashAttribute("message", "User has been created.");
        return "redirect:/user";
    }
    @PostMapping(value="/save2")
    public String saveUpdate(User user, RedirectAttributes ra){
        User updateUser = service.getUser(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setActive(user.getActive());
        String password = user.getPassword();
        if(!password.isBlank() || !password.isEmpty()){
            updateUser.setPassword(password);
            service.saveUser(updateUser);
            ra.addFlashAttribute("message", "User has been updated.");
            return "redirect:/user";         
        }else{
            service.saveUserNoPass(updateUser);
            ra.addFlashAttribute("message", "User has been updated.");
            return "redirect:/user";
        }
    } 
    @PostMapping(value="/save-profile")
    public String saveUpdateProfile(User user, RedirectAttributes ra){
        User updateUser = service.getUser(user.getUsername());
        updateUser.setEmail(user.getEmail());
        String password = user.getPassword();
        if(!password.isBlank() || !password.isEmpty()){
            updateUser.setPassword(password);
            service.saveUser(updateUser);
            ra.addFlashAttribute("message", "Your account profile has been updated.");
            return "redirect:/user/profile";         
        }else{
            service.saveUserNoPass(updateUser);
            ra.addFlashAttribute("message", "Your account profile has been updated.");
            return "redirect:/user/profile";
        }
    } 

}
