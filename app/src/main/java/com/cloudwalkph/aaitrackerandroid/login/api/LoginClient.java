package com.cloudwalkph.aaitrackerandroid.login.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by patsoo on 08/01/16.
 */
public interface LoginClient {

    @POST("/oauth/token")
    Call<LoginResponse> getAccessToken(@Body LoginBody loginBody);

    @GET("/api/v1/user")
    Call<UserInfoResponse> getUserInfo();

}
