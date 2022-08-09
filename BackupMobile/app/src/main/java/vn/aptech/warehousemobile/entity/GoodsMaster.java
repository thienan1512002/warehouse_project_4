package vn.aptech.warehousemobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodsMaster {
    @SerializedName("good_data")
    @Expose
    private GoodData good_data;
    @SerializedName("patch_no")
    @Expose
    private String patch_no;

    public GoodData getGood_data() {
        return good_data;
    }

    public void setGood_data(GoodData good_data) {
        this.good_data = good_data;
    }

    public String getPatch_no() {
        return patch_no;
    }

    public void setPatch_no(String patch_no) {
        this.patch_no = patch_no;
    }
}
