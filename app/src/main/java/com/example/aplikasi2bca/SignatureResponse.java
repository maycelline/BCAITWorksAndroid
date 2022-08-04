package com.example.aplikasi2bca;

import com.google.gson.annotations.SerializedName;

public class SignatureResponse {
    @SerializedName("AccessToken")
    private String AccessToken;

    @SerializedName("HTTPMethod")
    private String Method;

    @SerializedName("APISecret")
    private String apisecret;

    @SerializedName("Timestamp")
    private String timestamp;

    @SerializedName("URI")
    private String url;

    @SerializedName("RequestPayload")
    private String reqpayload;

    @SerializedName("HashedPayload")
    private String hashedpayloda;

    @SerializedName("SortedURI")
    private String sortedurl;

    @SerializedName("CalculatedHMAC")
    private String hmac;

    public String getHMAC() {return hmac;}
}
