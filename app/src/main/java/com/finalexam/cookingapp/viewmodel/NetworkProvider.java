package com.finalexam.cookingapp.viewmodel;

import com.finalexam.cookingapp.model.APIError;
import com.finalexam.cookingapp.model.LoginResponse;
import com.finalexam.cookingapp.model.SignUpRequest;
import com.finalexam.cookingapp.model.SignUpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkProvider {
    private static volatile NetworkProvider mInstance = null;

    private Retrofit retrofit;

    private NetworkProvider() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5:8000")
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public static NetworkProvider self() {
        if (mInstance == null)
            mInstance = new NetworkProvider();
        return mInstance;
    }

    public void signUp(String full_name, String email, String password) {
        retrofit.create(APIService.class).signUp(new SignUpRequest(full_name, email, password)).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                System.out.println("Register successfully");
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void login(String email, String password) {
        retrofit.create(APIService.class).login(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body().getId());
                } else {
                    System.out.println(response.errorBody().charStream());
//                    APIError message = new Gson().fromJson(response.errorBody().charStream(), APIError.class);
//                    System.out.println(message.getMessage());
//                    Toast.makeText(MainActivity.this, "" + message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
