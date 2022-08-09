package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.aptech.warehousemobile.entity.AllocateRequest;
import vn.aptech.warehousemobile.entity.vm.JsObj;

public interface AllocateService {

    @GET("allocate")
    Call<List<AllocateRequest>> findAll();
    @GET("allocate/{id}")
    Call<AllocateRequest> findById(@Path("id") int id);
    @POST("allocate/confirm")
    Call<AllocateRequest> confirmOrder(@Body JsObj jsObj);
    @POST("allocate/decline")
    Call<AllocateRequest> declineOrder(@Body JsObj jsObj);


}
