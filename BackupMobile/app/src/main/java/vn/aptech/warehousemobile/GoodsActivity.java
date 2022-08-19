package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.adapter.GoodsDataAdapter;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.GoodsService;
import vn.aptech.warehousemobile.entity.GoodData;

public class GoodsActivity extends AppCompatActivity {
    private RecyclerView rvGoods;
    private GoodsService service;
    private GoodsDataAdapter adapter;
    private SearchView searchGood;
    private ImageView imvBar;
    private List<GoodData> list = new ArrayList<>();
    private List<GoodData> listcpy = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        initUi();
        fillData();

        searchGood.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        imvBar.setOnClickListener(v->{
            Intent it = new Intent(GoodsActivity.this, ScannerActivity.class);
            startActivity(it);
        });
    }

    private void filter(String newText) {
        list.clear();
        if(newText.isEmpty()){
            list.addAll(listcpy);
        } else{
            newText = newText.toLowerCase();
            for(GoodData item: listcpy){
                if(item.getGoods_name().toLowerCase().contains(newText) || item.getGoods_no().toLowerCase().contains(newText)){
                    list.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }


    private void fillData() {
        service = ApiUtil.getGoodsDataService();
        service.findAll().enqueue(new Callback<List<GoodData>>() {
            @Override
            public void onResponse(Call<List<GoodData>> call, Response<List<GoodData>> response) {
                List<GoodData> results = response.body();
                list.addAll(results);
                listcpy.addAll(results);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<GoodData>> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });
    }

    private void initUi() {
        searchGood = findViewById(R.id.searchGood);
        searchGood.setQueryHint("Input to search goods");
        rvGoods=findViewById(R.id.rvGoods);
        imvBar = findViewById(R.id.imvBarSearch);

        adapter = new GoodsDataAdapter(list, this);
        rvGoods.setAdapter(adapter);
        rvGoods.setLayoutManager(new LinearLayoutManager(this));
        //decorate
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvGoods.addItemDecoration(itemDecoration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
    }
}