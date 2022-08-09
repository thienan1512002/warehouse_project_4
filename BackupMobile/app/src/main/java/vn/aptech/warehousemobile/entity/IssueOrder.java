package vn.aptech.warehousemobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IssueOrder {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("locations")
    @Expose
    private String location;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("goods_name")
    @Expose
    private String goods_name;
    @SerializedName("goods_master")
    @Expose
    private GoodsMaster goods_master;
    @SerializedName("closed")
    @Expose
    private boolean closed;
    @SerializedName("si_code")
    @Expose
    private String si_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public GoodsMaster getGoods_master() {
        return goods_master;
    }

    public void setGoods_master(GoodsMaster goods_master) {
        this.goods_master = goods_master;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getSi_code() {
        return si_code;
    }

    public void setSi_code(String si_code) {
        this.si_code = si_code;
    }
}
