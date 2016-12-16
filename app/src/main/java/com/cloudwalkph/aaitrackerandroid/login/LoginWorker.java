package com.cloudwalkph.aaitrackerandroid.login;

import com.cloudwalkph.aaitrackerandroid.lib.model.users.AccessToken;

import java.io.IOException;

/**
 * Created by patsoo on 09/01/16.
 */
public interface LoginWorker {
    void processLogin(String loginName, String password) throws IOException;
    void fetchOwner(AccessToken token) throws IOException;
}
