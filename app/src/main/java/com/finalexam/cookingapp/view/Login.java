package com.finalexam.cookingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.finalexam.cookingapp.R;
import com.finalexam.cookingapp.viewmodel.NetworkProvider;

public class Login extends AppCompatActivity {
    TextView register;
    Button login;
    EditText email, password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.tv_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
        login = findViewById(R.id.btn_loginL);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = findViewById(R.id.etemailL);
                password = findViewById(R.id.etpassL);

                NetworkProvider.self().login(email.getText().toString(), password.getText().toString(), Login.this);
//                startActivity(new Intent(Login.this,HomePage.class));
            }
        });
    }
}
