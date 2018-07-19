package com.example.shubhu.collegetime.view.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shubhu.collegetime.viewmodel.RegisterViewModel;
import com.example.shubhu.practicedemo.R;
import com.example.shubhu.practicedemo.databinding.FragmentRegisterBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFrament extends Fragment {

    public static RegisterFrament newInstance() {

        Bundle args = new Bundle();

        RegisterFrament fragment = new RegisterFrament();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentRegisterBinding fragmentRegisterBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_register,
                        container, false);
        RegisterViewModel registerViewModel =
                ViewModelProviders.of(getActivity()).get(RegisterViewModel.class);

        fragmentRegisterBinding.setRegisterViewModel(registerViewModel);

        return fragmentRegisterBinding.getRoot();
    }


    public void clickOnRegister() {

      /*  myRef.push().setValue(new UserRegisterModel(userMail,userPassword));
        Toast.makeText(MyApplication.getAppInstance(), "User register successfuly" + userMail,
                Toast.LENGTH_SHORT).show();*/
    }

}
