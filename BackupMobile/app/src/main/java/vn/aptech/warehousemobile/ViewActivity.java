package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    private ImageView imgGoods, imgAllocate, imgMoment, imgIssue;
    private Button btnLogout;
    private TextView txtUser;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        btnLogout = findViewById(R.id.btnLogout);
        txtUser = findViewById(R.id.txtUser);
        imgGoods = findViewById(R.id.goodsImage);
        imgAllocate = findViewById(R.id.imgAllocate);
        imgIssue = findViewById(R.id.imgIssue);
        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String welcome = "Welcome "+ username.toString();
        txtUser.setText(welcome);
        //imgGoods.setOnClickListener();
        imgIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this , IssueOrderActivity.class);
                startActivity(it);
            }
        });
        imgGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ViewActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        imgAllocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this, AllocateActivity.class );
                startActivity(it);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent it = new Intent(ViewActivity.this, MainActivity.class );
                startActivity(it);
            }
        });
    }
}