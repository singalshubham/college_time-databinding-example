package com.example.shubhu.collegetime.viewmodel;

import android.databinding.Bindable;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.example.shubhu.collegetime.MyApplication;
import com.example.shubhu.collegetime.model.UserRegisterModel;
import com.example.shubhu.collegetime.utility.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Ranosys Technologies
 */
public class RegisterViewModel extends BaseViewModel {

    // Write a message to the database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child(Constants.REGISTER_NODE);


    private String userMail;
    private String userPassword;

    @Bindable
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
        notifyPropertyChanged(BR.userMail);
    }

    @Bindable
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }

}
