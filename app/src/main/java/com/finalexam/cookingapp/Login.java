package com.finalexam.cookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextView register;
    Button login;
    private EditText email, password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.tv_register);
        email = findViewById(R.id.etemailL);
        password = findViewById(R.id.etpassL);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        login = findViewById(R.id.btn_loginL);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn(view);
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
    public void loginBtn(View view){
        if(!validateEmail() | !validatePassword()){
            return;
        }else{
            Intent switchActivityIntent = new Intent(this, HomePage.class);
            startActivity(switchActivityIntent);
        }
    }
}
