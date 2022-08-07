package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.AllocateService;
import vn.aptech.warehousemobile.api.service.LocationService;
import vn.aptech.warehousemobile.entity.AllocateRequest;
import vn.aptech.warehousemobile.entity.Location;

public class AllocateDetailActivity extends AppCompatActivity {

    private TextView tvDetailGoods , tvDetailsId , tvDetailsName , tvDetailQuantity;
    private AllocateService service;
    private Spinner spinner;
    private LocationService locService;
    List<String> loc_name = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_detail);

        tvDetailGoods = findViewById(R.id.tv_detail_GoodsName);
        tvDetailsName = findViewById(R.id.tv_detail_good);
        tvDetailsId = findViewById(R.id.tv_detail_id);
        tvDetailQuantity = findViewById(R.id.tv_detail_quantity);
        spinner = findViewById(R.id.sp_location);


        service = ApiUtil.getAllocateService();
        locService = ApiUtil.getLocationService();


        Intent it = getIntent();

        int id = it.getIntExtra("alc_id",0);

        locService.findAll("WH001").enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                for(Location loc: response.body()){
                    loc_name.add(loc.getLoc_desc());
                    Log.i("data ",loc.getLoc_desc());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this , R.layout.activity_allocate_detail,loc_name);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);

        service.findById(id).enqueue(new Callback<AllocateRequest>() {
            @Override
            public void onResponse(Call<AllocateRequest> call, Response<AllocateRequest> response) {
              tvDetailGoods.setText(response.body().getGoods_masters().getGood_data().getGoods_name());
              tvDetailsName.setText(response.body().getGoods_masters().getGood_data().getGoods_name());
              tvDetailsId.setText(Integer.toString(response.body().getAlc_id()));
              tvDetailQuantity.setText(Integer.toString(response.body().getAlc_moved_qty()));
            }

            @Override
            public void onFailure(Call<AllocateRequest> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });
    }
}