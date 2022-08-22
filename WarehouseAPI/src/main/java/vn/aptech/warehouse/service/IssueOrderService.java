/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.warehouse.service;

import java.util.List;
import vn.aptech.warehouse.entity.IssueOrder;

/**
 *
 * @author nhta1
 */
public interface IssueOrderService {
    List<IssueOrder> findByConfirm(boolean closed , String si_code);
    
    IssueOrder findById (int id);
    
    IssueOrder save (IssueOrder issueOrder);
    
    List<IssueOrder> findInAMonth(int startMonth,int endMonth,String si_code);
}
