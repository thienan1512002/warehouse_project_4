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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    private RelativeLayout rltGood, rltAllocate, rltMovement, rltIssue;
    private ImageView imgGoods, imgAllocate, imgMoment, imgIssue;
    private Button btnLogout;
    private TextView txtUser;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        setUi();

        rltIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this , IssueOrderActivity.class);
                startActivity(it);
            }
        });
        rltGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this , GoodsActivity.class);
                startActivity(it);
            }
        });
        rltAllocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this, AllocateActivity.class );
                startActivity(it);
            }
        });
        rltMovement.setOnClickListener(v->{
            Intent it = new Intent(ViewActivity.this, MovementActivity.class );
            startActivity(it);
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

    private void setUi() {
        btnLogout = findViewById(R.id.btnLogout);
        txtUser = findViewById(R.id.txtUser);
        rltAllocate = findViewById(R.id.rltAllocate);
        rltGood = findViewById(R.id.rltGoods);
        rltMovement = findViewById(R.id.rltMovement);
        rltIssue = findViewById(R.id.rltIssue);

        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String welcome = "Welcome "+ username.toString();
        txtUser.setText(welcome);
    }
}