package com.example.shubhu.collegetime.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.android.databinding.library.baseAdapters.BR;
import com.example.shubhu.collegetime.api.ApiClient;
import com.example.shubhu.collegetime.api.ApiServices;
import com.example.shubhu.collegetime.model.request.UserLoginRequestModel;
import com.example.shubhu.collegetime.model.response.ErrorResponseModel;
import com.example.shubhu.collegetime.model.response.UserLoginResponseModel;
import com.example.shubhu.practicedemo.R;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginFragmentViewModel extends BaseAndroidViewModel {

    @NonNull
    private String userEmail = "";

    @NonNull
    private String userPassword = "";

    private String userEmailErrorMessage;

    private String userPasswordErrorMessage;

    private boolean isEmailErrorEnabled;

    private boolean isPasswordErrorEnabled;

    private boolean isProgressBarEnabled = false;

    private MutableLiveData<UserLoginResponseModel> userLoginResponseModelLiveData =
            new MutableLiveData<>();
    private MutableLiveData<Throwable> apiErrorLiveData = new MutableLiveData<>();

    private MutableLiveData<String> httpExceptionInretrofit = new MutableLiveData<>();

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<Throwable> getApiErrorLiveData() {
        return apiErrorLiveData;
    }

    @Bindable
    public String getUserPassword() {
        return userPassword;
    }

    @Bindable
    public String getUserEmail() {
        return userEmail;
    }

    @Bindable
    public String getUserEmailErrorMessage() {
        return userEmailErrorMessage;
    }

    @Bindable
    public boolean isEmailErrorEnabled() {
        return isEmailErrorEnabled;
    }

    @Bindable
    public boolean isPasswordErrorEnabled() {
        return isPasswordErrorEnabled;
    }

    @Bindable
    public String getUserPasswordErrorMessage() {
        return userPasswordErrorMessage;
    }

    @Bindable
    public boolean isProgressBarEnabled() {
        return isProgressBarEnabled;
    }

    public void setProgressBarEnabled(boolean progressBarEnabled) {
        isProgressBarEnabled = progressBarEnabled;
        notifyPropertyChanged(BR.progressBarEnabled);
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        notifyPropertyChanged(BR.userEmail);
        validateEmail(userEmail);
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
        validatePassword(userPassword);
        //setPasswordErrorEnabled(false);
    }

    public void setUserEmailErrorMessage(String userEmailErrorMessage) {
        this.userEmailErrorMessage = userEmailErrorMessage;
        notifyPropertyChanged(BR.userEmailErrorMessage);
    }

    public void setEmailErrorEnabled(boolean emailErrorEnabled) {
        isEmailErrorEnabled = emailErrorEnabled;
        notifyPropertyChanged(BR.emailErrorEnabled);
    }

    public void setPasswordErrorEnabled(boolean passwordErrorEnabled) {
        isPasswordErrorEnabled = passwordErrorEnabled;
        notifyPropertyChanged(BR.passwordErrorEnabled);
    }

    public void setUserPasswordErrorMessage(String userPasswordErrorMessage) {
        this.userPasswordErrorMessage = userPasswordErrorMessage;
        notifyPropertyChanged(BR.userPasswordErrorMessage);
    }

    public boolean performValidation(String userEmail, String userPassword) {
        boolean isAllFieldValid = true;
        if (!validateEmail(userEmail)) {
            isAllFieldValid = false;
        }
        if (!validatePassword(userPassword)) {
            isAllFieldValid = false;
        }
        return isAllFieldValid;
    }

    private boolean validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            setUserEmailErrorMessage(getApplication().getString(R.string.email_field_require_message));
            setEmailErrorEnabled(true);
            return false;
        } else if (!isValidEmail(email)) {
            setUserEmailErrorMessage(getApplication().getString(R.string.email_address_error));
            setEmailErrorEnabled(true);
            return false;
        }
        setEmailErrorEnabled(false);
        return true;
    }

    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password.trim())) {
            setUserPasswordErrorMessage(getApplication().getString(R.string.password_field_required_message));
            setPasswordErrorEnabled(true);
            return false;
        } else if (password.trim().length() < 6) {
            setUserPasswordErrorMessage(getApplication().getString(R.string.user_password_error));
            setPasswordErrorEnabled(true);
            return false;
        }
        setPasswordErrorEnabled(false);
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public MutableLiveData<UserLoginResponseModel> getUserLoginResponseModelLiveData() {
        return userLoginResponseModelLiveData;
    }

    public MutableLiveData<String> getHttpExceptionInretrofit() {
        return httpExceptionInretrofit;
    }

    public void callLoginService() {
        ApiServices apiServices = ApiClient.getClient();
        apiServices.getLogin(new UserLoginRequestModel(getUserEmail(), getUserPassword()
                , "")).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserLoginResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserLoginResponseModel userLoginResponseModel) {
                        setProgressBarEnabled(false);
                        userLoginResponseModelLiveData.setValue(userLoginResponseModel);

                    }

                    @Override
                    public void onError(Throwable e) {
                        setProgressBarEnabled(false);
                        apiErrorLiveData.setValue(e);

                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).
                                    response().errorBody();
                            String error = "";
                            try {
                                if (responseBody != null) {
                                    error = responseBody.string();
                                    Gson gson = new Gson();
                                    if (!TextUtils.isEmpty(error)) {
                                        ErrorResponseModel errorResponseModel =
                                                gson.fromJson(error, ErrorResponseModel.class);

                                        httpExceptionInretrofit.setValue(errorResponseModel.getMessage());
                                    }
                                }

                            } catch (IOException | JsonSyntaxException jsonExption) {
                                e.printStackTrace();
                            }

                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void clickOnLogin() {
        boolean isValidate = performValidation(getUserEmail(), getUserPassword());
        if (isValidate) {
            setProgressBarEnabled(true);
            callLoginService();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
