package com.cloudwalkph.aaitrackerandroid.login;

import com.cloudwalkph.aaitrackerandroid.lib.model.users.AccessToken;
import com.cloudwalkph.aaitrackerandroid.lib.model.users.TokenOwner;

/**
 * Created by patsoo on 09/01/16.
 */
public interface OnLoginWorkerListener {
    void onLoginSuccess(AccessToken accessToken);

    void onLoginFail(String message);

    void onFetchOwnerSuccess(TokenOwner tokenOwner);

    void onFetchOwnerFail(String message);
}
