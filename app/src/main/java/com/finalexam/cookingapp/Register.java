package com.finalexam.cookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    TextView login;
    private EditText email, username, password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.etemailR);
        username = findViewById(R.id.etnameR);
        password = findViewById(R.id.etpassR);

        login = findViewById(R.id.tv_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }
    public Boolean validateEmail(){
        String valEmail = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+$";
        if(valEmail.isEmpty()){
            email.setError("Email cannot be empty");
            return false;
        }else if(!valEmail.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }
    public Boolean validateUsername(){
        String valUsername = username.getText().toString().trim();
        String whiteSpace = "\\A\\w{4,20}\\z";
        if(valUsername.isEmpty()) {
            username.setError("Username cannot be empty");
            return false;
        }else if(!valUsername.matches(whiteSpace)) {
            username.setError("White Space in username is not allowed");
            return false;
        }else{
            username.setError(null);
            return true;
        }
    }
    public  Boolean validatePassword(){
        String valPassword = password.getText().toString().trim();
        if(valPassword.isEmpty()){
            password.setError("Password cannot be empty");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }
    public void registerBtn(View view){
        if(!validateEmail() | !validateUsername() | !validatePassword()){
            return;
        }
    }
}
