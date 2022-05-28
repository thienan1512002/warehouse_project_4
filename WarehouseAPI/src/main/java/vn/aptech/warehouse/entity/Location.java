package vn.aptech.warehouse.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @Id
    private String loc_code;
    private String loc_desc;
    private boolean loc_status;
    private double loc_cap;
    private double loc_holding;
    private double loc_outgo;
    private boolean flammable;
    private String wh_code;
    
    
    
}
