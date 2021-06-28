package com.finalexam.cookingapp.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.finalexam.cookingapp.database.DatabaseHandler;
import com.finalexam.cookingapp.model.User;
import com.finalexam.cookingapp.view.HomePage;
import com.finalexam.cookingapp.model.LoginResponse;
import com.finalexam.cookingapp.model.SignUpRequest;
import com.finalexam.cookingapp.model.SignUpResponse;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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

    private String getErrorMessage(ResponseBody errorBody) {
        StringBuilder error = new StringBuilder();
        try {
            BufferedReader bufferedReader = null;
            if (errorBody != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        errorBody.byteStream()));

                String eLine = null;
                while ((eLine = bufferedReader.readLine()) != null) {
                    error.append(eLine);
                }
                bufferedReader.close();
            }

        } catch (Exception e) {
            error.append(e.getMessage());
        }
        return error.toString();
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

    public void login(String email, String password, Activity loginActivity) {
        retrofit.create(APIService.class).login(email, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(loginActivity.getApplicationContext());

                    User user = new User(response.body());
                    user.setCurrentAccount(1);
                    databaseHandler.addUser(user);

//                    loginActivity.startActivity(new Intent(loginActivity, HomePage.class));
                } else {
                    String errorMessage = getErrorMessage(response.errorBody());
                    System.out.println(errorMessage);
                    Toast.makeText(loginActivity.getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
