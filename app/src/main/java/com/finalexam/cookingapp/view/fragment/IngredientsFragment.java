package com.finalexam.cookingapp.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.finalexam.cookingapp.R;
import com.finalexam.cookingapp.database.DatabaseHandler;
import com.finalexam.cookingapp.model.Ingredient;
import com.finalexam.cookingapp.viewmodel.NetworkProvider;

import java.util.List;

public class IngredientsFragment extends Fragment {

    private Spinner sListSelectIngredients;
    private EditText etQuantity;
    private DatabaseHandler databaseHandler;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        sListSelectIngredients = view.findViewById(R.id.s_list_select_ingredients);
        etQuantity = view.findViewById(R.id.et_quantity);

        NetworkProvider.self().getAllIngredients(view.getContext());
        databaseHandler = new DatabaseHandler(view.getContext());

        List<Ingredient> ingredients = databaseHandler.getAllIngredients();
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter(view.getContext(), R.layout.item_ingredient, ingredients);

        adapter.setDropDownViewResource(R.layout.item_ingredient);
        sListSelectIngredients.setAdapter(adapter);

        sListSelectIngredients.setOnItemSelectedListener();
        return view;
    }
}