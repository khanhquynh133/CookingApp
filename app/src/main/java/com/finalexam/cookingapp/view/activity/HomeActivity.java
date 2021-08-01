package com.finalexam.cookingapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finalexam.cookingapp.R;
import com.finalexam.cookingapp.database.DatabaseHandler;
import com.finalexam.cookingapp.model.entity.Food;
import com.finalexam.cookingapp.model.entity.User;
import com.finalexam.cookingapp.view.Cake;
import com.finalexam.cookingapp.view.Drink;
import com.finalexam.cookingapp.view.FoodActivity;
import com.finalexam.cookingapp.view.Infor;
import com.finalexam.cookingapp.view.menu.Nav;
import com.finalexam.cookingapp.viewmodel.FoodAdapter;
import com.finalexam.cookingapp.viewmodel.NetworkProvider;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ImageButton cake,food,drink,cakeFOC,drinkPT,ava;
    TextView tvHello;
    DatabaseHandler databaseHandler;
    RecyclerView rvListFood;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHandler = new DatabaseHandler(getApplicationContext());
        setContentView(R.layout.activity_homepage);

        User user = databaseHandler.getCurrentAccount();

        tvHello = findViewById(R.id.tv_hello);
        tvHello.setText("Hello " + user.getFullName());

        ava = findViewById(R.id.ava);
        ava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Infor.class));
            }
        });
        cake = findViewById(R.id.btn_cake);
        cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Cake.class));
            }
        });
        drink = findViewById(R.id.btn_drink);
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Drink.class));
            }
        });
        food = findViewById(R.id.btn_food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FoodActivity.class));
            }
        });

        Nav nav = new Nav(HomeActivity.this);

        DatabaseHandler databaseHandler = new DatabaseHandler(HomeActivity.this);
        List<Food> foods = databaseHandler.getAllFoods();

        rvListFood = findViewById(R.id.rv_list_food);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        rvListFood.setLayoutManager(gridLayoutManager);

        FoodAdapter foodAdapter = new FoodAdapter(foods);
        rvListFood.setAdapter(foodAdapter);
    }
}
