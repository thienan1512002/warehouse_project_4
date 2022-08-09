package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.UserService;
import vn.aptech.warehousemobile.entity.User;

public class MainActivity extends AppCompatActivity {

    private TextView edName, edPass;
    private Button btnLogin;
    private UserService service;
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = findViewById(R.id.edUsername);
        edPass = findViewById(R.id.edPass);
        btnLogin = findViewById(R.id.btnLogin);

        service = ApiUtil.getUserService();


        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        if(!username.equalsIgnoreCase(""))
        {
            Toast.makeText(MainActivity.this, "Hello "+username, Toast.LENGTH_SHORT).show();
            Intent it = new Intent(MainActivity.this, ViewActivity.class );
            startActivity(it);

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(edName.getText().toString());
                user.setPassword(edPass.getText().toString());

                checkLogin(user);

                SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");
                if(!username.equalsIgnoreCase(""))
                {
                    Toast.makeText(MainActivity.this, "Hello "+username, Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(MainActivity.this, ViewActivity.class );
                    startActivity(it);

                }else{
                    Toast.makeText(MainActivity.this, "Wrong username or password ", Toast.LENGTH_SHORT).show();
                    edName.setText("");
                    edPass.setText("");
                    edName.setFocusable(true);
                }


            }
        });


    }

    public void checkLogin(User user){

      service.login(user).enqueue(new Callback<User>() {

          @Override
          public void onResponse(Call<User> call, Response<User> response) {

              if(response.isSuccessful()) {
                  SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putString("username",response.body().getUsername());
                  editor.apply();
                  Log.i( "login","post submitted to API." + response.body().getUsername());

              }
          }

          @Override
          public void onFailure(Call<User> call, Throwable t) {
              Log.e("login failed", "Unable to submit post to API."+t.toString());
          }

      });

    }
}