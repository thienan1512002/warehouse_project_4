package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ViewActivity extends AppCompatActivity {
    private ImageView imgGoods, imgAllocate, imgMoment, imgIssue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //imgGoods.setOnClickListener();
    }
}