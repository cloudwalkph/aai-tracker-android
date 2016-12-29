package com.cloudwalkph.aaitrackerandroid.login.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tricksunga on 11/27/16.
 */
public class LoginResponse {

    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("expires_in")
    public int expiresIn;
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("refresh_token")
    public String refreshToken;

}
