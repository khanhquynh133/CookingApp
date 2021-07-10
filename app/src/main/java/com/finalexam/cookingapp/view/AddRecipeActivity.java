package com.finalexam.cookingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.finalexam.cookingapp.R;
import com.finalexam.cookingapp.model.DetailIngredient;
import com.finalexam.cookingapp.viewmodel.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {
    ImageButton back;
    TextView tvCategoryTitle;
    TabLayout tlAddRecipe;
    ViewPager vpAddRecipe;

    static List<DetailIngredient> detailIngredients;
    static List<String> detailIngredientStrs;

    public static List<DetailIngredient> getDetailIngredients() {
        return detailIngredients;
    }
    public static List<String> getDetailIngredientStrs() {return detailIngredientStrs;}

    public static void addDetailIngredient(DetailIngredient detailIngredient) {
        detailIngredients.add(detailIngredient);
        detailIngredientStrs.add(detailIngredient.toString());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String categoryName = getIntent().getStringExtra("categoryName");
        if (detailIngredients == null) {
            detailIngredients = new ArrayList<>();
            detailIngredientStrs = new ArrayList<>();
        }

        setContentView(R.layout.activity_addrecipe);

        tlAddRecipe = findViewById(R.id.tl_add_recipe);
        vpAddRecipe = findViewById(R.id.vp_add_recipe);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAddRecipe.setAdapter(viewPagerAdapter);

        tlAddRecipe.setupWithViewPager(vpAddRecipe);

        tvCategoryTitle = findViewById(R.id.tv_categories7);
        tvCategoryTitle.setText(categoryName);
        back = findViewById(R.id.btn_backaddcake);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddRecipeActivity.this, Added.class));
            }
        });
    }
}
