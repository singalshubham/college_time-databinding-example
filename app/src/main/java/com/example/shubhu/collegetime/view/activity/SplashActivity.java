package com.example.shubhu.collegetime.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.shubhu.practicedemo.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*if(TextUtils.isEmpty(SavedPreferences.getInstance().getUserId()))*/
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
               /* else
                startActivity(new Intent(SplashActivity.this,DashboardActivity.class));*/
                finish();
            }
        },1000);
    }
}
