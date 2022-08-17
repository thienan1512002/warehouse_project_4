package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLogined();

            }
        }, 500);
    }

    private void checkLogined() {
        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        if(!username.equalsIgnoreCase(""))
        {
            Toast.makeText(SplashActivity.this, "Hello "+username, Toast.LENGTH_SHORT).show();
            Intent it = new Intent(SplashActivity.this, ViewActivity.class );
            startActivity(it);

        }else{
            Intent it = new Intent(SplashActivity.this, MainActivity.class );
            startActivity(it);
        }
    }
}