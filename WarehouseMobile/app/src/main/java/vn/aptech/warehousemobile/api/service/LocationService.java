package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.aptech.warehousemobile.entity.Location;

public interface LocationService {

    @GET("locs/{wh_code}")
    Call<List<Location>> findAll(@Path("wh_code") String wh_code);
}
