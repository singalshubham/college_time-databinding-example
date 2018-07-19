package com.example.shubhu.collegetime.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ranosys Technologies
 */

public class UserLoginResponseModel {
    @SerializedName("data")
    public LoginResponseData loginResponseData;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LoginResponseData getLoginResponseData() {
        return loginResponseData;
    }


    public class LoginResponseData {
        @SerializedName("name")
        private String name;
        @SerializedName("contactNumber")
        private String contactNumber;
        @SerializedName("RoleId")
        private int roleId;
        @SerializedName("userId")
        private String userId;
        @SerializedName("isFirsttime")
        private boolean isFirstTime;
        @SerializedName("AuthenticationToken")
        private String accessToken;
        @SerializedName("IsBuildingManager")
        private boolean isBuildingManger;
        @SerializedName("propertyId")
        private String[] propertyId;

        public String[] getPropertyId() {
            return propertyId;
        }

        public boolean isBuildingManger() {
            return isBuildingManger;
        }

        public String getName() {
            return name;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public int getRoleId() {
            return roleId;
        }

        public String getUserId() {
            return userId;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public boolean isFirstTime() {
            return isFirstTime;
        }

    }
}
