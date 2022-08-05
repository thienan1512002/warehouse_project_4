package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    private ImageView imgGoods, imgAllocate, imgMoment, imgIssue;
    private Button btnLogout;
    private TextView txtUser;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String welcome = "Welcome "+ username.toString();
        //txtUser.setText(welcome);
        //imgGoods.setOnClickListener();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}