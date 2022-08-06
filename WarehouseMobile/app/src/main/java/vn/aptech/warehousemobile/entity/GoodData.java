package vn.aptech.warehousemobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodData {
    @SerializedName("goods_no")
    @Expose
    private String goods_no;
    @SerializedName("goods_name")
    @Expose
    private String goods_name;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("goods_package")
    @Expose
    private String goods_package;
    @SerializedName("active")
    @Expose
    private boolean active;

    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGoods_package() {
        return goods_package;
    }

    public void setGoods_package(String goods_package) {
        this.goods_package = goods_package;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
