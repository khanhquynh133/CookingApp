package com.finalexam.cookingapp.viewmodel;

import com.finalexam.cookingapp.model.Category;
import com.finalexam.cookingapp.model.Ingredient;
import com.finalexam.cookingapp.model.response.LoginResponse;
import com.finalexam.cookingapp.model.request.SignUpRequest;
import com.finalexam.cookingapp.model.response.SignUpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    String prefixUrl = "/mobile_api/";

    @POST(prefixUrl + "users/sign-up")
    Call<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);

    @POST(prefixUrl + "users/log-in")
    Call<LoginResponse> login(@Query("email") String email, @Query("password") String password);

    @GET(prefixUrl + "categories")
    Call<List<Category>> getAllCategories();

    @GET(prefixUrl + "ingredients")
    Call<List<Ingredient>> getAllIngredient();
}
