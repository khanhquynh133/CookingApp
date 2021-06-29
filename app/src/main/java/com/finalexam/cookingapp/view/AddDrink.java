package com.finalexam.cookingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.finalexam.cookingapp.R;

public class AddDrink extends AppCompatActivity {
    ImageButton back;
    Button save;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddrink);
        back = findViewById(R.id.btn_backaddcake);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddDrink.this,Add.class));
            }
        });
        save = findViewById(R.id.btn_savecake);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddDrink.this,Drink.class));
            }
        });
    }
}