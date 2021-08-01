package com.finalexam.cookingapp.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finalexam.cookingapp.R;
import com.finalexam.cookingapp.model.entity.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> foods;

    public FoodAdapter(
            List<Food> foods
    ) {
        this.foods = foods;
    }

    @NonNull @Override public FoodViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_food_home, parent, false);
        return new FoodViewHolder(view);
    }

    @Override public void onBindViewHolder(
            @NonNull FoodAdapter.FoodViewHolder holder,
            int position
    ) {
        Food food = foods.get(position);
        if (food == null) return;

        holder.tvFoodName.setText(food.getName());
        // TODO: Change this
        holder.tvTimeToCook.setText("120 mins");
        // TODO: Change this
        holder.tvReview.setText("4.9");

        Picasso.with(holder.itemView.getContext()).load(
                "https://localhost:8000/mobile_api/image/" + food.getImageID()).into(
                holder.ivFoodCover);
    }

    @Override public int getItemCount() {
        return foods.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoodCover;
        TextView tvFoodName, tvTimeToCook, tvReview;

        public FoodViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);

            ivFoodCover = itemView.findViewById(R.id.iv_food_cover);
            tvReview = itemView.findViewById(R.id.tv_review);
            tvTimeToCook = itemView.findViewById(R.id.tv_time_to_cook);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
        }
    }
}
