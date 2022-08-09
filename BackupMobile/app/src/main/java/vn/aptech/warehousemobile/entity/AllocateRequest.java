package vn.aptech.warehousemobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;

public class AllocateRequest  {
    @SerializedName("alc_id")
    @Expose
    private int alc_id;
    @SerializedName("alc_moved_qty")
    @Expose
    private int alc_moved_qty;
    @SerializedName("confirm")
    @Expose
    private boolean confirm;
    @SerializedName("movement_time")
    @Expose
    private String movement_time;
    @SerializedName("goods_masters")
    @Expose
    private  GoodsMaster goods_masters;

    @SerializedName("warehouse")
    @Expose
    private Warehouse warehouse;
    @SerializedName("location")
    @Expose
    private Location location;

    public int getAlc_id() {
        return alc_id;
    }

    public void setAlc_id(int alc_id) {
        this.alc_id = alc_id;
    }

    public int getAlc_moved_qty() {
        return alc_moved_qty;
    }

    public void setAlc_moved_qty(int alc_moved_qty) {
        this.alc_moved_qty = alc_moved_qty;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public String getMovement_time() {
        return movement_time;
    }

    public void setMovement_time(String movement_time) {
        this.movement_time = movement_time;
    }

    public GoodsMaster getGoods_masters() {
        return goods_masters;
    }

    public void setGoods_masters(GoodsMaster goods_masters) {
        this.goods_masters = goods_masters;
    }


    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "AllocateRequest{" +
                "alc_id=" + alc_id +
                ", alc_moved_qty=" + alc_moved_qty +
                ", confirm=" + confirm +
                ", movement_time='" + movement_time + '\'' +
                ", goods_masters=" + goods_masters +
                ", warehouse=" + warehouse +
                ", location=" + location +
                '}';
    }
}
