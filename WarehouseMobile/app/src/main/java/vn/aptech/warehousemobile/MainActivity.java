package vn.aptech.warehousemobile;

import androidx.appcompat.app.AppCompatActivity;

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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(edName.getText().toString());
                user.setPassword(edPass.getText().toString());
                User login = checkLogin(user);
                if(login!=null){
                    Toast.makeText(MainActivity.this, "Hello "+MainActivity.this.user.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public User checkLogin(User user){
        User logUser = new User();
      service.login(user).enqueue(new Callback<User>() {

          @Override
          public void onResponse(Call<User> call, Response<User> response) {

              if(response.isSuccessful()) {
                  MainActivity.this.user.setPassword(response.body().getPassword());
                  MainActivity.this.user.setUsername(response.body().getUsername());
                  Log.i( "login","post submitted to API." + response.body().getUsername());
              }
          }

          @Override
          public void onFailure(Call<User> call, Throwable t) {
              Log.e("login failed", "Unable to submit post to API."+t.toString());
          }
      });
      return logUser;
    }
}