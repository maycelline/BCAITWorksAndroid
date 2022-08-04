package com.example.aplikasi2bca;

import com.google.gson.annotations.SerializedName;

public class OAuthResponse {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private String expiresIn;

    @SerializedName("scope")
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }
}
