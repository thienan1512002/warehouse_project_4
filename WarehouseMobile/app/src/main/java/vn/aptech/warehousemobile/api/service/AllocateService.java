package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.aptech.warehousemobile.entity.AllocateRequest;

public interface AllocateService {

    @GET("allocate")
    Call<List<AllocateRequest>> findAll();

}
