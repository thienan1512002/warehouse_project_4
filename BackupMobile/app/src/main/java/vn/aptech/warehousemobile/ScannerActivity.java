package vn.aptech.warehousemobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.bumptech.glide.Glide;
import com.google.zxing.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.GoodsService;
import vn.aptech.warehousemobile.entity.GoodData;

public class ScannerActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private GoodsService service;
    private String goods_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        if (ContextCompat.checkSelfPermission(ScannerActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(ScannerActivity.this, new String[] {Manifest.permission.CAMERA}, 0);
        }
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(ScannerActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ScannerActivity.this, GoodsDetailActivity.class);
                        intent.putExtra("goods_no",result.getText());
                        service = ApiUtil.getGoodsDataService();
                        service.findByNo(result.getText()).enqueue(new Callback<GoodData>() {
                            @Override
                            public void onResponse(Call<GoodData> call, Response<GoodData> response) {
                                goods_name = response.body().getGoods_name();
//                                Toast.makeText(ScannerActivity.this, response.body().getGoods_name(), Toast.LENGTH_SHORT).show();
                                intent.putExtra("goods_name",goods_name);
                                Log.i("good",goods_name);
                            }
                            @Override
                            public void onFailure(Call<GoodData> call, Throwable t) {

                            }
                        });
//                        intent.putExtra("goods_name",goods_name);
//                        Log.i("goods_name",goods_name);
                        startActivity(intent);
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}