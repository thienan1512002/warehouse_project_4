package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.aptech.warehousemobile.entity.IssueOrder;
import vn.aptech.warehousemobile.entity.vm.JsObj;

public interface IssueOrderService {

    @GET("issue")
    Call<List<IssueOrder>> findByClosed();

    @GET("issue/{id}")
    Call<IssueOrder> findById(@Path("id") int id);

    @POST("issue/confirm")
    Call<IssueOrder> confirmOrder(@Body JsObj jsObj);
}
