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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.warehousemobile.api.ApiUtil;
import vn.aptech.warehousemobile.api.service.UserService;
import vn.aptech.warehousemobile.entity.Role;
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(edName.getText().toString());
                user.setPassword(edPass.getText().toString());
                checkLogin(user);
            }
        });
    }

    public void checkLogin(User user){

      service.login(user).enqueue(new Callback<User>() {

          @Override
          public void onResponse(Call<User> call, Response<User> response) {

              if(response.isSuccessful()) {
                  //check role
                  List<Role> filterRole;
                  Collection<Role> roles = response.body().getRole();
                  ArrayList<Role> listRole = new ArrayList<>(roles);
                  List<String> allowRole = Arrays.asList("ROLE_ADMIN","ROLE_MANAGER");
                  filterRole = listRole.stream().filter(role -> allowRole.contains(role.getName()))
                          .collect(Collectors.toList());
                  if(filterRole.size()>1){
                      Toast.makeText(MainActivity.this, "Admin or manager", Toast.LENGTH_SHORT).show();

                      SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
                      SharedPreferences.Editor editor = sharedPreferences.edit();
                      editor.putString("username",response.body().getUsername());
                      editor.apply();

                      Log.i( "login","post submitted to API." + response.body().getUsername());
                      Toast.makeText(MainActivity.this, "Hello "+response.body().getUsername(), Toast.LENGTH_SHORT).show();
                      Intent it = new Intent(MainActivity.this, ViewActivity.class );
                      startActivity(it);
                  }else{
                      edName.setText("");
                      edPass.setText("");
                      Toast.makeText(MainActivity.this, "You can not access this", Toast.LENGTH_SHORT).show();
                  }

              }else{
                  Toast.makeText(MainActivity.this, "Wrong username or password ", Toast.LENGTH_SHORT).show();
                  edName.setText("");
                  edPass.setText("");
                  edName.setFocusable(true);
              }
          }

          @Override
          public void onFailure(Call<User> call, Throwable t) {
              Log.e("login failed", "Unable to submit post to API."+t.toString());
          }

      });

    }
}