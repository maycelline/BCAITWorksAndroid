package com.example.aplikasi2bca;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/api/oauth/token")

    Call<OAuthResponse> doCallOauth(@Header("Authorization") String authorization, @FieldMap Map<String, String> params);

    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("/utilities/signature/")
    Call<String> getSignature(
            @Header("URI") String url,
            @Header("Timestamp") String timeStamp,
            @Header("APISecret") String apiSecret,
            @Header("HTTPMethod") String httpMethod,
            @FieldMap Map<String, String> param,
            @Header("AccessToken") String accessToken
    );


    @GET("/banking/v3/corporates/BCAAPI2016/accounts/0201245680")
    Call<AccountBalanceResponse> getAccountBalance(
            @Header("Authorization") String authorization,
            @Header("X-BCA-Key") String Bcakey,
            @Header("X-BCA-Signature") String BcaSignature,
            @Header("X-BCA-Timestamp") String BcaTimestamp
    );

}
