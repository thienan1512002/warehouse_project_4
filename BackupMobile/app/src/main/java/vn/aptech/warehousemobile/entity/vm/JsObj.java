package vn.aptech.warehousemobile.entity.vm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class JsObj {
    @SerializedName("loc_code")
    @Expose
    private String loc_code;
    @SerializedName("pt_id")
    @Expose
    private int pt_id;
    @SerializedName("qty")
    @Expose
    private int qty;
    @SerializedName("date")
    @Expose
    private LocalDate date;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("so_id")
    @Expose
    private String so_id;
    @SerializedName("alc_id")
    @Expose
    private int alc_id;
    @SerializedName("loc_desc")
    @Expose
    private String loc_desc;

    public String getLoc_code() {
        return loc_code;
    }

    public void setLoc_code(String loc_code) {
        this.loc_code = loc_code;
    }

    public int getPt_id() {
        return pt_id;
    }

    public void setPt_id(int pt_id) {
        this.pt_id = pt_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSo_id() {
        return so_id;
    }

    public void setSo_id(String so_id) {
        this.so_id = so_id;
    }

    public int getAlc_id() {
        return alc_id;
    }

    public void setAlc_id(int alc_id) {
        this.alc_id = alc_id;
    }

    public String getLoc_desc() {
        return loc_desc;
    }

    public void setLoc_desc(String loc_desc) {
        this.loc_desc = loc_desc;
    }
}
