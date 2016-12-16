package com.cloudwalkph.aaitrackerandroid.login;

import com.cloudwalkph.aaitrackerandroid.lib.api.Conf;
import com.cloudwalkph.aaitrackerandroid.lib.api.ServiceGenerator;
import com.cloudwalkph.aaitrackerandroid.lib.model.users.AccessToken;
import com.cloudwalkph.aaitrackerandroid.lib.model.users.TokenOwner;
import com.cloudwalkph.aaitrackerandroid.login.api.LoginBody;
import com.cloudwalkph.aaitrackerandroid.login.api.LoginClient;
import com.cloudwalkph.aaitrackerandroid.login.api.LoginResponse;
import com.cloudwalkph.aaitrackerandroid.login.api.UserInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by patsoo on 09/01/16.
 */
public class LoginWorkerImpl implements LoginWorker {
    private OnLoginWorkerListener listener;

    public LoginWorkerImpl(OnLoginWorkerListener listener) {
        this.listener = listener;
    }

    @Override
    public void processLogin(final String loginName, String password) {
        String clientId = Conf.API_CLIENT_ID;
        String clientSecret = Conf.API_CLIENT_SECRET;
        String grantType = Conf.API_GRANT_TYPE;

        LoginClient client = ServiceGenerator.createService(LoginClient.class, loginName, password);
        LoginBody loginBody = new LoginBody();
        loginBody.clientId = clientId;
        loginBody.clientSecret = clientSecret;
        loginBody.grantType = grantType;
        loginBody.username = loginName;
        loginBody.password = password;

        Call<LoginResponse> call = client.getAccessToken(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    AccessToken accessToken = new AccessToken();
                    accessToken.setAccessToken(loginResponse.accessToken);
                    accessToken.setTokenType(loginResponse.tokenType);
                    listener.onLoginSuccess(accessToken);
                } else {
                    listener.onLoginFail("Username or Password is incorrect");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // something went completely south (like no internet connection)
                //TODO snackbar network error
                listener.onLoginFail("Make sure you are connected to internet");
            }
        });
    }

    @Override
    public void fetchOwner(final AccessToken token) {
        LoginClient client = ServiceGenerator.createService(LoginClient.class, token);
        Call<UserInfoResponse> call = client.getUserInfo();
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    UserInfoResponse userInfoResponse = response.body();
                    TokenOwner tokenOwner = new TokenOwner();
                    tokenOwner.setId(userInfoResponse.id);
                    tokenOwner.setFirstName(userInfoResponse.firstName);
                    tokenOwner.setLastName(userInfoResponse.lastName);
                    tokenOwner.setEmail(userInfoResponse.email);
                    tokenOwner.setCompany(userInfoResponse.company);

                    listener.onFetchOwnerSuccess(tokenOwner);
                } else {
                    listener.onFetchOwnerFail("Username or Password is incorrect");
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                // something went completely south (like no internet connection)
                //TODO snackbar network error
                listener.onFetchOwnerFail("Make sure you are connected to internet");
            }
        });
    }
}
