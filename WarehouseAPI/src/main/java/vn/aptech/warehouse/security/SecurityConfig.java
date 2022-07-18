/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import vn.aptech.warehouse.filter.CustomAuthenticationFilter;
import vn.aptech.warehouse.filter.CustomAuthorizationFilter;

/**
 *
 * @author Jack
 */
@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder); 
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        //customAuthenticationFilter.setFilterProcessesUrl("api/login");
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        //truy cap cong khai them duong dan vao antMatchers("")
//        http.authorizeRequests().antMatchers("/home/login","/api/token/refresh/**","/home/test-layout","/**","/warehouse/*" ,"/api/warehouses/**","/locs/**","/api/locs/**").permitAll();
//        http.authorizeRequests().antMatchers(GET,"/api/users/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(POST,"/api/users/user/save/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

          http.authorizeHttpRequests().antMatchers("/home/login","/").permitAll();
        //http.authorizeHttpRequests().anyRequest().authenticated();
        http.authorizeHttpRequests().antMatchers("/create","/save").hasRole("ROLE_  ADMIN");
        http.authorizeHttpRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/home/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/warehouse")
                .failureUrl("/login?error=true")
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/home/login")
                .and().exceptionHandling().accessDeniedPage("/403");
        http.authorizeHttpRequests().and().rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24*60*60);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    private PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl(); 
    }
}
