package com.example.shubhu.collegetime.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shubhu.collegetime.model.response.UserLoginResponseModel;
import com.example.shubhu.collegetime.utility.Constants;
import com.example.shubhu.collegetime.utility.DialogUtils;
import com.example.shubhu.collegetime.utility.SavedPreferences;
import com.example.shubhu.collegetime.view.activity.LoginActivity;
import com.example.shubhu.collegetime.viewmodel.LoginFragmentViewModel;
import com.example.shubhu.practicedemo.R;
import com.example.shubhu.practicedemo.databinding.FragmentLoginBinding;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    LoginFragmentViewModel loginFragmentViewModel;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentLoginBinding fragmentLoginBinding = DataBindingUtil.inflate(getActivity().
                getLayoutInflater(), R.layout.fragment_login, container, false);
        fragmentLoginBinding.setLoginFragment(this);
        loginFragmentViewModel =
                ViewModelProviders.of(getActivity()).get(LoginFragmentViewModel.class);
        fragmentLoginBinding.setLoginFragment(this);
        fragmentLoginBinding.setLoginViewModel(loginFragmentViewModel);
        fragmentLoginBinding.executePendingBindings();
        loginFragmentViewModel.getApiErrorLiveData().observe(this,
                new Observer<Throwable>() {
                    @Override
                    public void onChanged(@Nullable Throwable throwable) {
                        if (throwable instanceof HttpException) {
                            loginFragmentViewModel.getHttpExceptionInretrofit().observe(
                                    LoginFragment.this,
                                    new Observer<String>() {
                                        @Override
                                        public void onChanged(@Nullable String s) {
                                            DialogUtils.getConfirmationDialog(getActivity(),
                                                    getString(R.string.text_error),
                                                    s);

                                        }
                                    });
                        } else if (throwable instanceof IOException) {
                            Toast.makeText(getActivity(), "internet error ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        loginFragmentViewModel.getUserLoginResponseModelLiveData().observe(this, new Observer<UserLoginResponseModel>() {
            @Override
            public void onChanged(@Nullable UserLoginResponseModel userLoginResponseModel) {
                assert userLoginResponseModel != null;
                Toast.makeText(getActivity(), "" + userLoginResponseModel.
                        loginResponseData.getUserId(), Toast.LENGTH_SHORT).show();
                SavedPreferences.getInstance().storeUserId(userLoginResponseModel.
                        loginResponseData.getUserId());
                //  startActivity(new Intent(getActivity(), DashboardActivity.class));
            }
        });
        return fragmentLoginBinding.getRoot();

    }

    public void openRegisterFragment() {
        ((LoginActivity) getActivity()).addFragment(R.id.container_for_fragment, RegisterFrament.newInstance()
                , Constants.REGISTER_FRAG_TAG, true);
    }

    public void openForgotPasswordFragment() {
        ((LoginActivity) getActivity()).addFragment(R.id.container_for_fragment, ForgotPasswordFragment.newInstance()
                , Constants.FORGOT_PASSWORD_FRAG, true);
    }

}
