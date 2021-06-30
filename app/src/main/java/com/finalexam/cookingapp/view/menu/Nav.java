package com.finalexam.cookingapp.view.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.finalexam.cookingapp.R;

import com.finalexam.cookingapp.database.DatabaseHandler;
import com.finalexam.cookingapp.view.Add;
import com.finalexam.cookingapp.view.Fav;
import com.finalexam.cookingapp.view.HomePage;
import com.finalexam.cookingapp.view.Login;
import com.finalexam.cookingapp.view.SearchPage;
import com.finalexam.cookingapp.viewmodel.NetworkProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public final class Nav {
    BottomNavigationView nav;

    public Nav(Activity activity) {

        nav = activity.findViewById(R.id.bottomnav);
        nav.setSelectedItemId(R.id.home);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        activity.startActivity(new Intent(activity.getApplicationContext(), HomePage.class));
                        activity.overridePendingTransition(0, 0);
                        return true;
                    case R.id.add:
                        NetworkProvider.self().getAllCategories(activity);
                        return true;
                    case R.id.search:
                        activity.startActivity(new Intent(activity.getApplicationContext(), SearchPage.class));
                        activity.overridePendingTransition(0, 0);
                        return true;
                    case R.id.fav:
                        activity.startActivity(new Intent(activity.getApplicationContext(), Fav.class));
                        activity.overridePendingTransition(0, 0);
                        return true;
                    case R.id.out:
                        DatabaseHandler databaseHandler = new DatabaseHandler(activity.getApplicationContext());

                        databaseHandler.logout();
                        activity.startActivity(new Intent(activity.getApplicationContext(), Login.class));
                        activity.overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
    }
}
