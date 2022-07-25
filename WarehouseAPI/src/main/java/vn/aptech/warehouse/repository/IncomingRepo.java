/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.warehouse.entity.Incoming;

/**
 *
 * @author nhta1
 */
public interface IncomingRepo extends JpaRepository<Incoming, Integer>{
    
    @Query("SELECT o FROM Incoming o WHERE o.ic_id =:id")
    public Incoming findIncoming(@Param("id") Integer  id);
    
    @Query("SELECT o FROM Incoming o WHERE o.closed=:closed")
    public List<Incoming> findByClosed(@Param("closed") boolean closed);
}
