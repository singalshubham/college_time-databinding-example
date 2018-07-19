package com.example.shubhu.collegetime.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ranosys Technologies
 */

public class ErrorResponseModel {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
