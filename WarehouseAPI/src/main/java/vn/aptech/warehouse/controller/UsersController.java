/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
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
@RequestMapping(value="/api")
public class UsersController {
    @Autowired
    private UserService userService;
    
    @GetMapping(value="/users")
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

    @GetMapping(value="/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
                try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                    
                }catch(Exception e){
                    response.setHeader("error", e.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    //response.sendError(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());

                    response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
                
            }else{
                throw new RuntimeException("Refres token is missing");
            }
    }

    
}
