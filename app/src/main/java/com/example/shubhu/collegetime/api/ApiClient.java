package com.example.shubhu.collegetime.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiServices apiServices;
    private static final String getURL = "http://www.snapnfix.com/";

    public static ApiServices getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.connectTimeout(60, TimeUnit.SECONDS);
        okClient.readTimeout(60, TimeUnit.SECONDS);
        okClient.addInterceptor(logging);
        okClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        });
        Retrofit client = new Retrofit.Builder()
                .baseUrl(getURL)
                .client(okClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiServices = client.create(ApiServices.class);
        return apiServices;
    }

}
