package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.adapter.AllocateAdapter;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.AllocateService;
import vn.aptech.warehousemobile.entity.AllocateRequest;

public class AllocateActivity extends AppCompatActivity {

    private AllocateAdapter adapter;
    private AllocateService service;
    private RecyclerView rvAllocate;
    private List<AllocateRequest> requests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate);

        rvAllocate = findViewById(R.id.rvAllocate);

        adapter = new AllocateAdapter(requests,this);

        rvAllocate.setAdapter(adapter);
        rvAllocate.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvAllocate.addItemDecoration(itemDecoration);

        service = ApiUtil.getAllocateService();

        service.findAll().enqueue(new Callback<List<AllocateRequest>>() {
            @Override
            public void onResponse(Call<List<AllocateRequest>> call, Response<List<AllocateRequest>> response) {
                List<AllocateRequest> results = response.body();

                requests.addAll(results);

                adapter.notifyDataSetChanged();

                for(AllocateRequest r : requests){
                    Log.e("get value ", "data."+r.getAlc_id());
                }

                Toast.makeText(AllocateActivity.this, "data "+adapter.getItemCount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<AllocateRequest>> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });


    }
}