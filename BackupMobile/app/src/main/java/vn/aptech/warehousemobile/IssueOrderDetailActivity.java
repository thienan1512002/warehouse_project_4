package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.IssueOrderService;
import vn.aptech.warehousemobile.entity.IssueOrder;

public class IssueOrderDetailActivity extends AppCompatActivity {
    private IssueOrderService service;
    private TextView tvIssueDetailName , tvIssueDetailLocation , tvIssueDetailRef , tvIssueDetailQuantity;
    private ImageView imgDetailIssue;
    private final String IMG_URL = "http://10.0.0.18:8080/goods-photos/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_order_detail);

        service = ApiUtil.getIssueOrderService();

        tvIssueDetailName = findViewById(R.id.tv_issue_detail_GoodsName);
        tvIssueDetailLocation = findViewById(R.id.tv_issue_detail_location);
        tvIssueDetailRef = findViewById(R.id.tv_detail_ref);
        tvIssueDetailQuantity = findViewById(R.id.tv_issue_detail_quantity);

        imgDetailIssue = findViewById(R.id.img_ava_issue_detail);

        int id = getIntent().getIntExtra("issue_id",0);

        service.findById(id).enqueue(new Callback<IssueOrder>() {
            @Override
            public void onResponse(Call<IssueOrder> call, Response<IssueOrder> response) {
                tvIssueDetailName.setText(tvIssueDetailName.getText().toString()+" "+response.body().getGoods_name());
                tvIssueDetailLocation.setText(response.body().getLocation());
                tvIssueDetailRef.setText(response.body().getGoods_master().getPatch_no());
                tvIssueDetailQuantity.setText(Integer.toString(response.body().getQuantity()));
                Glide.with(IssueOrderDetailActivity.this).load(IMG_URL+response.body().getGoods_master().getGood_data().getGoods_no()+"/"+response.body().getGoods_master().getGood_data().getImage()).into(imgDetailIssue);
            }

            @Override
            public void onFailure(Call<IssueOrder> call, Throwable t) {
                Log.e("get value fail", "Unable to submit post to API."+t.toString());
            }
        });
    }
}