package com.example.shubhu.collegetime.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ranosys Technologies
 */

public class UserLoginRequestModel {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("devicetype")
    private String deviceType;

    public UserLoginRequestModel(String email, String password, String deviceId) {
        this.email = email;
        this.password = password;
        this.deviceId = deviceId;
        this.deviceType = "Android";
    }


}
