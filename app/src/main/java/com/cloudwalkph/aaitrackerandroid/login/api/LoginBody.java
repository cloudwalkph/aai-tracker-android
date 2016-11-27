package com.cloudwalkph.aaitrackerandroid.login.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tricksunga on 11/27/16.
 */
public class LoginBody {

    @SerializedName("client_id")
    public String clientId;
    @SerializedName("client_secret")
    public String clientSecret;
    @SerializedName("grant_type")
    public String grantType;
    public String username;
    public String password;

}
