package com.cloudwalkph.aaitrackerandroid.login;

import com.cloudwalkph.aaitrackerandroid.lib.model.AccessToken;
import com.cloudwalkph.aaitrackerandroid.lib.model.TokenOwner;

import java.io.IOException;

/**
 * Created by patsoo on 09/01/16.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginWorkerListener {
    private LoginView view;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
    }

    @Override
    public void login() {
        view.setProgressDialogVisible(true);
        //TODO validation
        boolean hasError = false;
        if(view.getLoginNameValue().isEmpty()){
            view.setLoginNameError();
            hasError = true;
        }
        if(view.getPasswordValue().isEmpty()){
            view.setPasswordError();
            hasError = true;
        }
        if(!hasError) {
            LoginWorker loginWorker = new LoginWorkerImpl(this);
            try {
                loginWorker.processLogin(view.getLoginNameValue(), view.getPasswordValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fetchTokenOwner(AccessToken token) {
        LoginWorker loginWorker = new LoginWorkerImpl(this);
        try {
            loginWorker.fetchOwner(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoginSuccess(AccessToken accessToken) {
        //Save singleton
        AccessToken.getInstance().setAccessToken(accessToken.getAccessToken());
        AccessToken.getInstance().setTokenType(accessToken.getTokenType());

        fetchTokenOwner(accessToken);
    }

    @Override
    public void onLoginFail(String message) {
        view.setProgressDialogVisible(false);
        view.setLoginFail(message);
    }

    @Override
    public void onFetchOwnerSuccess(TokenOwner TokenOwner) {
        //Save singleton
        TokenOwner.getInstance().setId(TokenOwner.getId());
        TokenOwner.getInstance().setFirstName(TokenOwner.getFirstName());
        TokenOwner.getInstance().setLastName(TokenOwner.getLastName());
        TokenOwner.getInstance().setEmail(TokenOwner.getEmail());
        TokenOwner.getInstance().setCompany(TokenOwner.getCompany());

        view.setProgressDialogVisible(false);
        view.setLoginSuccessful();
    }

    @Override
    public void onFetchOwnerFail(String message) {
        view.setProgressDialogVisible(false);
        view.setLoginFail(message);
    }
}
