package com.example.aplikasi2bca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_USERNAME = "extra_uname";
    private TextView tv_showBalance;
    private ImageView btn_view;
    private APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvUsernameReceived = findViewById(R.id.tv_username);
        btn_view = findViewById(R.id.view_button);


        String username = getIntent().getStringExtra(EXTRA_USERNAME);
        tvUsernameReceived.setText("Pagi, " + username);

        btn_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.view_button) {
            doGetSignature();
        }
    }

    private void doGetSignature() {
        if (apiInterface == null) {
            String url = "/banking/v3/corporates/BCAAPI2016/accounts/0201245680";
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String time = new SimpleDateFormat("hh:mm").format(new Date());
            String timestamp = date + "T" + time + ":00.000+07.00";
            String apiSecret = "a48bbf60-3d5d-408c-b569-2b911bf8fde6";
            String HTTPmethod = "GET";
            String request = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            String accessToken = sharedPreferences.getString("access_token", "");
            Map<String, String> params = new HashMap<>();
            params.put("RequestPayload", request);

            System.out.println("Access Token: " + accessToken);
            System.out.println("Timestamp before: " + timestamp);

            apiInterface = new RetrofitClientScalar().getClient().create(APIInterface.class);
            Call<String> call = apiInterface.getSignature(url, timestamp, apiSecret, HTTPmethod, params, accessToken);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String rawResponse = response.body();
                    if (response.code() == 200 && rawResponse != null) {
                        String[] splitResponse = rawResponse.split(",[\\r\\n]");
                        String rawSignature = splitResponse[splitResponse.length - 1].trim();
                        String signature = rawSignature.substring(rawSignature.indexOf(" "));

                        doGetBalance(accessToken, signature, timestamp);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    System.out.println(t);
                }
            });
        }
    }

    private void doGetBalance(String accessToken, String signature, String timestamp) {
        String authorization = "Bearer " + accessToken;
        String BCAKey = "a7be80dc-cf31-4b71-b18f-fd261bc416db";
        String CorporateID = "BCAAPI2016";
        String AccountNumber = "0201245680";
        System.out.println("Authorization: " + authorization);
        System.out.println("Signature:" + signature);

        apiInterface = new RetrofitClient().getService();
        Call<AccountBalanceResponse> call = apiInterface.getAccountBalance(authorization, BCAKey, signature, timestamp);

        call.enqueue(new Callback<AccountBalanceResponse>() {
            @Override
            public void onResponse(Call<AccountBalanceResponse> call, Response<AccountBalanceResponse> response) {
                System.out.println("Balance: " + response.code());
                System.out.println("Message: " + response.message());
                System.out.println("Error: " + response.errorBody().toString());
                tv_showBalance = findViewById(R.id.tv_balance);
                tv_showBalance.setText("Rp. " + response.body().getBalance());
                System.out.println("Bisa");
            }

            @Override
            public void onFailure(Call<AccountBalanceResponse> call, Throwable t) {
                System.out.println("Gabisa");
            }
        });
    }


}