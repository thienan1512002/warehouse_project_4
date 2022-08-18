package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.aptech.warehousemobile.entity.AllocateRequest;
import vn.aptech.warehousemobile.entity.GoodData;

public interface GoodsService {
    @GET("goods/")
    Call<List<GoodData>> findAll();
    @GET("goods/{id}")
    Call<GoodData> findByNo(@Path("id") String goods_no);
//    @GET("goods/{name}")
//    Call<List<GoodData>> findByName(@Path("name") String goods_name);

}
