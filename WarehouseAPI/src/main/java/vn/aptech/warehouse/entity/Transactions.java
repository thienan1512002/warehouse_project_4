package vn.aptech.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transactions")
public class Transactions {
    @Id
    private int id;
    private String type;
    private String goods_name;
    private String fromLoc;
    private String toLoc;
    private int quantity;
}