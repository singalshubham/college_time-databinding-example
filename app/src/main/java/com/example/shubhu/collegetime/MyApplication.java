package com.example.shubhu.collegetime;

import android.app.Application;

/**
 * @author Ranosys Technologies
 */
public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

    public static MyApplication getAppInstance() {
        return application;
    }
}
