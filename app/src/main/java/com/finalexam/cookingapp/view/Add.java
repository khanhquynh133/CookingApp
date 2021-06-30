package com.finalexam.cookingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalexam.cookingapp.R;
import com.finalexam.cookingapp.database.DatabaseHandler;
import com.finalexam.cookingapp.model.Category;
import com.finalexam.cookingapp.model.User;
import com.finalexam.cookingapp.view.menu.Nav;
import com.finalexam.cookingapp.viewmodel.CategoryAdapter;
import com.finalexam.cookingapp.viewmodel.NetworkProvider;

import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {
    private TextView tvHello2;
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    DatabaseHandler databaseHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        databaseHandler = new DatabaseHandler(getApplicationContext());

        User user = databaseHandler.getCurrentAccount();

        if (user != null) {
            tvHello2 = findViewById(R.id.tv_hello2);
            tvHello2.setText("Hello " + user.getFullName());
        }

        rvCategory = findViewById(R.id.rv_category);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCategory.setLayoutManager(linearLayoutManager);

        categoryAdapter = new CategoryAdapter(databaseHandler.getAllCategories());
        rvCategory.setAdapter(categoryAdapter);

        Nav nav = new Nav(Add.this);
    }
}