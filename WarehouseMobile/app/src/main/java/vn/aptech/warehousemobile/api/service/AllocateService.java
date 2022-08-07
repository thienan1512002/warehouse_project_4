package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.aptech.warehousemobile.entity.AllocateRequest;

public interface AllocateService {

    @GET("allocate")
    Call<List<AllocateRequest>> findAll();
    @GET("allocate/{id}")
    Call<AllocateRequest> findById(@Path("id") int id);

}
