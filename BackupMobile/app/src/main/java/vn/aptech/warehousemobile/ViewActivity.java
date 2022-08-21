package vn.aptech.warehousemobile;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class ViewActivity extends AppCompatActivity {
    private CardView cardGoods, cardAllocate, cardMovement, cardIssue;
    private ImageView imgLogout;
    private TextView txtUser;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        setUi();
        subcribeReceiveNoti();
        cardIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this , IssueOrderActivity.class);
                startActivity(it);
            }
        });
        cardGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this , GoodsActivity.class);
                startActivity(it);
            }
        });
        cardAllocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ViewActivity.this, AllocateActivity.class );
                startActivity(it);
            }
        });
        cardMovement.setOnClickListener(v->{
            Intent it = new Intent(ViewActivity.this, MovementActivity.class );
            startActivity(it);
        });
        imgLogout.setOnClickListener(v->{
            logout();
        });

    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent it = new Intent(this, MainActivity.class);
        finishAffinity();
        startActivity(it);
    }

    private void subcribeReceiveNoti() {
        FirebaseMessaging.getInstance().subscribeToTopic("sale").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(ViewActivity.this, "Subcribe!", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"Subcribe");
            }
        });
    }

    private void setUi() {
        imgLogout = findViewById(R.id.imgLogout);
        txtUser = findViewById(R.id.txtUser);
        cardAllocate = findViewById(R.id.cardAllocate);
        cardGoods = findViewById(R.id.cardGoods);
        cardMovement = findViewById(R.id.cardMovement);
        cardIssue = findViewById(R.id.cardIssue);

        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String welcome = "Welcome "+ username.toString();
        txtUser.setText(welcome);
        getSupportActionBar().hide();
    }
}