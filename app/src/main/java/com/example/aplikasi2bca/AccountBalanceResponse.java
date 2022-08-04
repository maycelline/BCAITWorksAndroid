package com.example.aplikasi2bca;

import com.google.gson.annotations.SerializedName;

public class AccountBalanceResponse {

    @SerializedName("AccountDetailDataSuccess")
    private String AccountDetailDataSuccess;

    @SerializedName("AccountNumber")
    private String AccountNumber;

    @SerializedName("Currency")
    private String Currency;

    @SerializedName("Balance")
    private String Balance;

    @SerializedName("AvailableBalance")
    private String AvailableBalance;

    @SerializedName("FloatAmount")
    private String FloatAmount;

    @SerializedName("HoldAmount")
    private String HoldAmount;

    @SerializedName("Plafon")
    private String Plafon;

    @SerializedName("AccountDetailDataFailed")
    private String AccountDetailDataFailed;

    @SerializedName("English")
    private String English;

    @SerializedName("Indonesian")
    private String Indonesian;

    @SerializedName("Account Number")
    private String AccountNumberFailed;

    public String getBalance() {return Balance;}
}
