package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.aptech.warehousemobile.entity.User;

public interface UserService {
    @GET("user")
    Call<List<User>> findAll();

    @POST("user/android")
    Call<User> login(@Body User user);
}
