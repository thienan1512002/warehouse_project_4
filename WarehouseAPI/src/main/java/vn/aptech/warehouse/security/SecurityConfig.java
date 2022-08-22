/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.warehouse.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


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
//        http.authorizeRequests().antMatchers("/home/login","/api/token/refresh/**","/home/test-layout","/**","/warehouse/*" ,"/api/warehouses/**","/locs/**","/api/locs/**","/inventory/*").permitAll();
//        http.authorizeRequests().antMatchers(GET,"/api/users/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(POST,"/api/users/user/save/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests();
        http.authorizeHttpRequests()
                //admin, manager, movement
                .antMatchers("/warehouse/","/customer","/goods","/Incoming","/locs",
                        "/suppliers","/user","/qc","/allocated","/sale","/unqualified","/")
                .hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN","ROLE_MOVEMENT")
                //update profile
                .antMatchers("/user/update-profile/**").authenticated()
                //update role
                .antMatchers("/user/update/**","/user/update-role/**").hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN","ROLE_APP")
                //app manager
//                .antMatchers("/warehouse/","/customer","/goods","/locs", "/suppliers","/user","/user/**",
//                        "/warehouse/**","/customer/**","/goods/**","/Incoming/**","/locs/**", "/suppliers/**"
//                ).hasAnyAuthority("ROLE_APP")
//                //receive incoming
//                .antMatchers("/warehouse/","/Incoming","/Incoming/**").hasAnyAuthority("ROLE_RECEIVE_INCOM")
//                //qc
//                .antMatchers("/warehouse/","/Incoming","/Incoming/**","/qc","/qc/**").hasAnyAuthority("ROLE_RECEIVE_QC")
//                //create sale
//                .antMatchers("/warehouse/","/sale","/sale/**").hasAnyAuthority("ROLE_CREATE_SO")
//                //approve sale
//                .antMatchers("/warehouse/","/sale","/sale/**").hasAnyAuthority("ROLE_APPROVE_SO")
                .anyRequest().permitAll();
        http.authorizeHttpRequests()
                .and()//login
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .and()//logout
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable().cors();
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
