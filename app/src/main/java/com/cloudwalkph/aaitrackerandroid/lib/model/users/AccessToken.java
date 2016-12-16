package com.cloudwalkph.aaitrackerandroid.lib.model.users;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by patsoo on 08/01/16.
 */

public class AccessToken extends RealmObject {

    @PrimaryKey
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private Long expiresIn;

    @SerializedName("refresh_token")
    private String refreshToken;

    private static AccessToken mInstance = null;

    public static AccessToken getInstance(){
        if(mInstance == null)
        {
            mInstance = new AccessToken();
        }
        return mInstance;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String value) {
        accessToken = value;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String value) { tokenType = value; }

    public Long getExpiresIn() {
        return this.expiresIn;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
