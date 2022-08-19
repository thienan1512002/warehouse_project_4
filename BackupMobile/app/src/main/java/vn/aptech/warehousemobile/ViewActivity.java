package vn.aptech.warehousemobile;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    private RelativeLayout rltGood, rltAllocate, rltMovement, rltIssue;
    private ImageView imgGoods, imgAllocate, imgMoment, imgIssue;
    private Button btnLogout, btnSubcribe;
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
        btnSubcribe.setOnClickListener(v->{
            Task<Void> weather = FirebaseMessaging.getInstance().subscribeToTopic("sale")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Subscribed";
                            if (!task.isSuccessful()) {
                                msg = "Subscribe failed";
                            }
                            Log.d(TAG, msg);
                            Toast.makeText(ViewActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void setUi() {
        btnLogout = findViewById(R.id.btnLogout);
        btnSubcribe = findViewById(R.id.btnSubcribe);

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