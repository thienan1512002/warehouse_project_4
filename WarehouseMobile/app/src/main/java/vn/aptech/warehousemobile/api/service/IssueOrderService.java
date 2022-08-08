package vn.aptech.warehousemobile.api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.aptech.warehousemobile.entity.IssueOrder;

public interface IssueOrderService {
    @GET("issue")
    Call<List<IssueOrder>> findByClosed(boolean closed , String si_code);

    @GET("issue/{id}")
    Call<IssueOrder> findById(@Path("id") int id);
}
