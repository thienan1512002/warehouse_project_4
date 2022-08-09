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
    @SerializedName("loc_code")
    @Expose
    private String loc_code;
    @SerializedName("pt_id")
    @Expose
    private int pt_id;

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
}
