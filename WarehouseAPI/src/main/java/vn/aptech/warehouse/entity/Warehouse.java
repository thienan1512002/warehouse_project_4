package vn.aptech.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    private String wh_code;
    private String wh_desc;
    private boolean wh_status;
    private String wh_cmt;

    @OneToMany(mappedBy = "wh_code",cascade = CascadeType.ALL)
    private List<Location> locations;
}
