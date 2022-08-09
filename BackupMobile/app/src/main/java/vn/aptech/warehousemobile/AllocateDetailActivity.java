package vn.aptech.warehousemobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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
import vn.aptech.warehousemobile.entity.vm.JsObj;

public class AllocateDetailActivity extends AppCompatActivity {

    private TextView tvDetailGoods , tvDetailsId , tvDetailsDate , tvDetailQuantity;
    private AllocateService service;
    private ImageView imgView;
    private LocationService locService;
    private Button btnSubmit , btnDecline;
    private final String URL ="http://10.0.0.18:8080/goods-photos/";
    JsObj jsObj = new JsObj();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_detail);

        tvDetailGoods = findViewById(R.id.tv_detail_GoodsName);
        tvDetailsDate = findViewById(R.id.tv_detail_date);
        tvDetailsId = findViewById(R.id.tv_detail_id);
        tvDetailQuantity = findViewById(R.id.tv_detail_quantity);
        imgView = findViewById(R.id.img_ava_detail);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnDecline = findViewById(R.id.btnDecline);

        service = ApiUtil.getAllocateService();
        locService = ApiUtil.getLocationService();


        Intent it = getIntent();

        int id = it.getIntExtra("alc_id",0);

        locService.findAll("WH001").enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                for(Location loc: response.body()){

                    Log.i("data ",loc.getLoc_desc());
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });



        service.findById(id).enqueue(new Callback<AllocateRequest>() {
            @Override
            public void onResponse(Call<AllocateRequest> call, Response<AllocateRequest> response) {
              tvDetailGoods.setText(tvDetailGoods.getText().toString()+" "+response.body().getGoods_masters().getGood_data().getGoods_name());
              tvDetailsDate.setText(response.body().getMovement_time());
              tvDetailsId.setText(response.body().getLocation().getLoc_desc());
              tvDetailQuantity.setText(Integer.toString(response.body().getAlc_moved_qty()));
              Glide.with(AllocateDetailActivity.this).load(URL+response.body().getGoods_masters().getGood_data().getGoods_no()+"/"+response.body().getGoods_masters().getGood_data().getImage()).into(imgView);

                jsObj.setLoc_desc(response.body().getLocation().getLoc_desc());
                jsObj.setId(response.body().getAlc_id());
                jsObj.setQty(response.body().getAlc_moved_qty());
            }

            @Override
            public void onFailure(Call<AllocateRequest> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AllocateDetailActivity.this);
                dialog.setMessage("Do you want to save your change ?").setTitle("Confirm Allocate request").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        service.confirmOrder(jsObj).enqueue(new Callback<AllocateRequest>() {
                            @Override
                            public void onResponse(Call<AllocateRequest> call, Response<AllocateRequest> response) {
                                Log.i("Response : ", response.body().toString());
                                if(response.body().getAlc_id() != 0){
                                    Toast.makeText(AllocateDetailActivity.this, "Confirm allocate request successfully", Toast.LENGTH_SHORT).show();
                                   Intent it = new Intent(AllocateDetailActivity.this,AllocateActivity.class);
                                   startActivity(it);
                                }
                            }

                            @Override
                            public void onFailure(Call<AllocateRequest> call, Throwable t) {
                                Toast.makeText(AllocateDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Cancle",null).create().show();
            }
        });

        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder declineDialog = new AlertDialog.Builder(AllocateDetailActivity.this);
                declineDialog.setMessage("Do you want to save your change ?").setTitle("Decline allocate request").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        service.declineOrder(jsObj).enqueue(new Callback<AllocateRequest>() {
                            @Override
                            public void onResponse(Call<AllocateRequest> call, Response<AllocateRequest> response) {
                                Log.i("Response : ", response.body().toString());
                                if(response.body().getAlc_id() != 0){
                                    Toast.makeText(AllocateDetailActivity.this, "Decline allocate request successfully", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(AllocateDetailActivity.this,AllocateActivity.class);
                                    startActivity(it);
                                }
                            }

                            @Override
                            public void onFailure(Call<AllocateRequest> call, Throwable t) {
                                Toast.makeText(AllocateDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Cancle",null).create().show();
            }
        });
    }
}