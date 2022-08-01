package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.aptech.warehousemobile.entity.User;

public interface UserService {
    @GET("user")
    Call<List<User>> findAll();
}
