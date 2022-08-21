package vn.aptech.warehousemobile;

import static vn.aptech.warehousemobile.api.ApiUtil.IMG_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.adapter.GoodsDataAdapter;
import vn.aptech.warehousemobile.adapter.TransactionAdapter;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.GoodsService;
import vn.aptech.warehousemobile.api.service.TransactionService;
import vn.aptech.warehousemobile.entity.GoodData;
import vn.aptech.warehousemobile.entity.Transaction;

public class GoodsDetailActivity extends AppCompatActivity {
    private TextView tvName, tvPrice, tvNo, tvPackage;
    private ImageView imgGood;
    private String imgUrl = IMG_URL;
    private RecyclerView rvTransact;
    private TransactionAdapter adapter;
    private GoodsService service;
    private TransactionService transactionService;
    private Spinner spinnerFilter;
    private String type;
    private String[] transType = new String[]{"Allocated","UnAllocated","Unqualified","Recycle","Disposed"};
    private List<Transaction> list = new ArrayList<>();
    private List<Transaction> listcpy = new ArrayList<>();
    private String goods_no, goods_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initUi();
        goods_no = getIntent().getStringExtra("goods_no");
        goods_name = getIntent().getStringExtra("goods_name");
        getGoodsDetail();
        fillTransact();
        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = spinnerFilter.getSelectedItem().toString();
                switch (spinnerFilter.getSelectedItem().toString()){
                    case "Allocated":{
                        type="in";
                        break;
                    }
                    case "UnAllocated":{
                        type="out";
                        break;
                    }
                    case "Unqualified":{
                        type="unqualified";
                        break;
                    }
                    case "Recycle":{
                        type="Recycle";
                        break;
                    }
                    case "Disposed":{
                        type="Disposed";
                        break;
                    }
                }
                filter(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    private void filter(String type) {
        list.clear();
        if(type.isEmpty()){
            list.addAll(listcpy);
        } else{
            for(Transaction item: listcpy){
                if(item.getType().equals(type)){
                    list.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void fillTransact() {
        transactionService = ApiUtil.getTransactionService();
        transactionService.findByName(goods_name).enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                List<Transaction> results = response.body();
                list.addAll(results);
                listcpy.addAll(results);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {

            }
        });
    }

    private void getGoodsDetail() {
        service = ApiUtil.getGoodsDataService();
        service.findByNo(goods_no).enqueue(new Callback<GoodData>() {
            @Override
            public void onResponse(Call<GoodData> call, Response<GoodData> response) {
                tvName.setText(response.body().getGoods_name());
                tvPrice.setText(String.valueOf(response.body().getPrice())+"$");
                tvNo.setText(response.body().getGoods_no());
                tvPackage.setText(response.body().getGoods_package());
                Glide.with(GoodsDetailActivity.this).load(imgUrl+response.body().getGoods_no()+"/"+response.body().getImage()).into(imgGood);
            }
            @Override
            public void onFailure(Call<GoodData> call, Throwable t) {

            }
        });
    }

    private void initUi() {
        tvName = findViewById(R.id.tvGoodNameDetail);
        tvPrice=findViewById(R.id.tvGoodPriceDetail);
        tvNo = findViewById(R.id.tvGoodNoDetail);
        tvPackage = findViewById(R.id.tvGoodPackDetail);
        rvTransact = findViewById(R.id.rvTransact);
        imgGood = findViewById(R.id.imvGoodDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new TransactionAdapter(list, this);
        rvTransact.setAdapter(adapter);
        rvTransact.setLayoutManager(new LinearLayoutManager(this));
        //decorate
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvTransact.addItemDecoration(itemDecoration);
        spinnerFilter = findViewById(R.id.spinnerType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, transType);
        spinnerFilter.setAdapter(adapter);
    }
}