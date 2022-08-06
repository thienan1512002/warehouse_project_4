package vn.aptech.warehousemobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodsMaster {
    @SerializedName("good_data")
    @Expose
    private GoodData good_data;

    public GoodData getGood_data() {
        return good_data;
    }

    public void setGood_data(GoodData good_data) {
        this.good_data = good_data;
    }
}
