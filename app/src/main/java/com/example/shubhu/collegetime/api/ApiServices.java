package com.example.shubhu.collegetime.api;

import com.example.shubhu.collegetime.model.request.UserLoginRequestModel;
import com.example.shubhu.collegetime.model.response.UserLoginResponseModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("api/ComplaintsManager/Login")
    Observable<UserLoginResponseModel> getLogin(@Body UserLoginRequestModel userLoginRequestModel);
}
