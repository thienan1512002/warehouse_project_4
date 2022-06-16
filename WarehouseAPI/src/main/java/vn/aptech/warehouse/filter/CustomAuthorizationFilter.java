/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Jack
 */
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/api/token/refresh")){
            filterChain.doFilter(request, response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authoritys = new ArrayList<>();
                    stream(roles).forEach(role->{
                       authoritys.add(new SimpleGrantedAuthority(role)); 
                    });
                    UsernamePasswordAuthenticationToken authenticationToken=
                            new UsernamePasswordAuthenticationToken(username, null, authoritys);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }catch(Exception e){
                    log.error("Error loggin in: {}", e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    //response.sendError(FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", e.getMessage());
                    //String error = e.getMessage();
                    response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
                
            }else{
                filterChain.doFilter(request, response);
            }
        }
    }
    
}
