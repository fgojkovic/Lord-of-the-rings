package com.example.lordoftheringsapp.apiCalls;

import android.media.session.MediaSession;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCall {


    public static final String TOKEN = "Bearer iXxlqVvWt_bMh_tE0jzn";
    private static Retrofit retrofit = null;

    public static Retrofit getApiCall() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //Autentification with okhttpclient
       /* OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization", "OXGUgP6u2niuAxyhobUN");
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();*/

        retrofit = new Retrofit.Builder()
                .baseUrl("https://the-one-api.dev/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
