package com.finalexam.cookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Account extends AppCompatActivity {
    ImageButton people1,back;
    EditText pass;
    TextView acc,regis;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        back = findViewById(R.id.btn_backA);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account.this,RememberLogin.class));
                overridePendingTransition(R.anim.slideinleft,R.anim.slideoutleft);
            }
        });
        regis = findViewById(R.id.tv_registerr);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account.this,Register.class));
            }
        });
        acc = findViewById(R.id.tv_another);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account.this,Login.class));
            }
        });
        people1 = findViewById(R.id.people1);
        Animation animFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        people1.startAnimation(animFade);
        pass = findViewById(R.id.etpassA);
        Animation animFade1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        pass.startAnimation(animFade);
    }
}
