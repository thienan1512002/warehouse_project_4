package vn.aptech.warehousemobile;

import static vn.aptech.warehousemobile.api.ApiUtil.IMG_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.GoodsService;
import vn.aptech.warehousemobile.entity.GoodData;

public class GoodsDetailActivity extends AppCompatActivity {
    private TextView tvName, tvPrice, tvNo, tvPackage;
    private ImageView imgGood;
    private String imgUrl = IMG_URL;
    private RecyclerView rvReport;
    private GoodsService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initUi();
        String goods_no = getIntent().getStringExtra("goods_no");
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
        rvReport = findViewById(R.id.rvMoveReport);
        imgGood = findViewById(R.id.imvGoodDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}