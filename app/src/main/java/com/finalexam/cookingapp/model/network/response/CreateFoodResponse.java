package com.finalexam.cookingapp.model.network.response;

import com.google.gson.annotations.SerializedName;

public class CreateFoodResponse {
    @SerializedName("msg")
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
