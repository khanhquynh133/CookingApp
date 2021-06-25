package com.finalexam.cookingapp.viewmodel;

import com.finalexam.cookingapp.model.LoginResponse;
import com.finalexam.cookingapp.model.SignUpRequest;
import com.finalexam.cookingapp.model.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @POST("/mobile_api/users/sign-up")
    Call<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST("/mobile_api/users/log-in")
    Call<LoginResponse> login(@Query("email") String email, @Query("password") String password);
}
