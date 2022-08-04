package com.example.aplikasi2bca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText in_username, in_password;
    private Button btn_login;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in_username = findViewById(R.id.in_username);
        in_password = findViewById(R.id.in_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            String username = in_username.getText().toString();
            String password = in_password.getText().toString();
            doLogin(username, password);
        }
    }

    public void doLogin(String username, String password) {
        if (apiInterface == null) {
            apiInterface = new RetrofitClient().getService();
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "client_credentials");

            String baseAuthorization = "Basic MjI5MmYwNzctMjQ4Ny00ODRmLTg1MTMtYjg4ZThlNTJkZTAxOmYyMDg2Y2U5LTZlYmEtNDIxMy04Zjc4LWFjZDdlYjQ5OTU4Zg==";

            Call<OAuthResponse> call = apiInterface.doCallOauth(baseAuthorization, params);
            call.enqueue(new Callback<OAuthResponse>() {
                @Override
                public void onResponse(Call<OAuthResponse> call, Response<OAuthResponse> response) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("access_token", response.body().getAccessToken());
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    Intent moveIntent = new Intent(MainActivity.this, home.class);
                    moveIntent.putExtra(home.EXTRA_USERNAME, username);
                    startActivity(moveIntent);
                    String responseA = response.body().toString();
                    Log.d("GLG", "responseA: " + responseA);

                }

                @Override
                public void onFailure(Call<OAuthResponse> call, Throwable t) {
                    System.out.println(t);
                    Toast.makeText(getApplicationContext(), "Cannot hit API", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }
}

