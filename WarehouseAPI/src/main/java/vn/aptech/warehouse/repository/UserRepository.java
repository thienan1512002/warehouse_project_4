/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.aptech.warehouse.entity.User;

/**
 *
 * @author Jack
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("SELECT o FROM User o WHERE o.username=:username and o.password=:password")
    User findByUsernameAndPassword(@Param("username")String username ,@Param("password") String password);
}
