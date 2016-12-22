package com.cloudwalkph.aaitrackerandroid.login.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by patsoo on 14/01/2016.
 */
public class UserInfoResponse {

    public int id;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    public String company;
    @SerializedName("contact_number")
    public String contactNumber;
    public String email;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;

}
