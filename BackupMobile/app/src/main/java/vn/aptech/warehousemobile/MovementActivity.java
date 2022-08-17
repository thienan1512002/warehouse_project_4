package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MovementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement);
        initUi();

    }

    private void initUi() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}