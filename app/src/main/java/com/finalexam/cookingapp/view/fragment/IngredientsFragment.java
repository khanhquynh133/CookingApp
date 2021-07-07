package com.finalexam.cookingapp.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.finalexam.cookingapp.R;
import com.finalexam.cookingapp.database.DatabaseHandler;
import com.finalexam.cookingapp.model.DetailIngredient;
import com.finalexam.cookingapp.model.Ingredient;
import com.finalexam.cookingapp.view.AddRecipeActivity;
import com.finalexam.cookingapp.viewmodel.NetworkProvider;

import java.util.List;

public class IngredientsFragment extends Fragment {

//    private Spinner sListSelectIngredients;
//    private EditText etQuantity;
    private Button btnAddIngredient;
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


//        etQuantity = view.findViewById(R.id.et_quantity);
//
        NetworkProvider.self().getAllIngredients(view.getContext());
        databaseHandler = new DatabaseHandler(view.getContext());
        List<Ingredient> ingredients = databaseHandler.getAllIngredients();
//
//        List<Ingredient> ingredients = databaseHandler.getAllIngredients();
//        ArrayAdapter<Ingredient> adapter = new ArrayAdapter(view.getContext(), R.layout.item_ingredient, ingredients);
//
//        adapter.setDropDownViewResource(R.layout.item_ingredient);
//        sListSelectIngredients.setAdapter(adapter);
//
//        sListSelectIngredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        btnAddIngredient = view.findViewById(R.id.btn_add_ingredient);
        btnAddIngredient.setOnClickListener(v -> {
            Dialog dialog = new Dialog(view.getContext());
            dialog.setTitle("ABC");
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_add_item_ingredient);

            Spinner spIngredients = dialog.findViewById(R.id.sp_ingredients_dialog);
            ArrayAdapter<Ingredient> adapter = new ArrayAdapter(dialog.getContext(), R.layout.item_ingredient, ingredients);

            adapter.setDropDownViewResource(R.layout.item_ingredient);
            spIngredients.setAdapter(adapter);

            Button btnOk = dialog.findViewById(R.id.btn_ok_dialog);
            EditText etQuantity = dialog.findViewById(R.id.et_quantity_dialog);
            btnOk.setOnClickListener(v1 -> {
                int position = spIngredients.getSelectedItemPosition();
                String quantity = etQuantity.getText().toString();

                Ingredient ingredient = ingredients.get(position);
                AddRecipeActivity.addDetailIngredient(new DetailIngredient(ingredient, quantity));
                System.out.println(AddRecipeActivity.getDetailIngredients().size());
                dialog.cancel();
            });
            dialog.show();
        });
        return view;
    }
}