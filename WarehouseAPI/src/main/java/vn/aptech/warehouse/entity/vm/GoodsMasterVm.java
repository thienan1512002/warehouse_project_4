
package vn.aptech.warehouse.entity.vm;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsMasterVm {
    private int pt_id;
    private float pt_qty;
    private LocalDate pt_date_in;
    private String patch_no;
    private int ic_id;
    private String goods_no;    
    private String sup_code;   
    private String wh_code;
}
