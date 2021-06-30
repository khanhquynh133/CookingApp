package com.finalexam.cookingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.finalexam.cookingapp.R;

public class AddRecipe extends AppCompatActivity {
    ImageButton back;
    TextView tvCategoryTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String categoryName = getIntent().getStringExtra("categoryName");

        setContentView(R.layout.activity_addrecipe);

        tvCategoryTitle = findViewById(R.id.tv_categories7);
        tvCategoryTitle.setText(categoryName);
        back = findViewById(R.id.btn_backaddcake);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddRecipe.this,Added.class));
            }
        });
    }
}
