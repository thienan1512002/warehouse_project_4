package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.aptech.warehousemobile.entity.Transaction;

public interface TransactionService {
    @GET("transaction/{name}")
    Call<List<Transaction>> findByName(@Path("name") String goods_name);
}
