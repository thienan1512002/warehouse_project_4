package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.adapter.IssueOrderAdapter;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.IssueOrderService;
import vn.aptech.warehousemobile.entity.IssueOrder;

public class IssueOrderActivity extends AppCompatActivity {

    private RecyclerView rvIssueOrder;
    private IssueOrderService service;
    private IssueOrderAdapter adapter;
    List<IssueOrder> orders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_order);
        rvIssueOrder = findViewById(R.id.rvIssueOrder);
        adapter = new IssueOrderAdapter(orders,this);
       service = ApiUtil.getIssueOrderService();
        rvIssueOrder.setAdapter(adapter);
        rvIssueOrder.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvIssueOrder.addItemDecoration(itemDecoration);

        service.findByClosed(false,"WH001").enqueue(new Callback<List<IssueOrder>>() {
            @Override
            public void onResponse(Call<List<IssueOrder>> call, Response<List<IssueOrder>> response) {
                List<IssueOrder> results = response.body();
                orders.addAll(results);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<IssueOrder>> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });
    }
}