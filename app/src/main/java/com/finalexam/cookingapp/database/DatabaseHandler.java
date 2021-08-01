package com.finalexam.cookingapp.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.finalexam.cookingapp.model.entity.Category;
import com.finalexam.cookingapp.model.entity.Food;
import com.finalexam.cookingapp.model.entity.Ingredient;
import com.finalexam.cookingapp.model.entity.User;
import com.finalexam.cookingapp.view.activity.HomeActivity;
import com.finalexam.cookingapp.view.activity.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static List<TableInfo> tables;
    private static final String DATABASE_NAME = "mobile";
    private static final int DATABASE_VERSION = 3;

    private static final String INTEGER_TYPE = "INTEGER";
    private static final String TEXT_TYPE = "TEXT";


    private static final String USER_TABLE_NAME = "user";
    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_FULL_NAME = "full_name";
    private static final String USER_COLUMN_EMAIL = "email";
    private static final String USER_COLUMN_CURRENT_ACCOUNT = "current_account";

    private static final String CATEGORY_TABLE_NAME = "category";
    private static final String CATEGORY_COLUMN_ID = "id";
    private static final String CATEGORY_COLUMN_IMAGE_ID = "image_id";
    private static final String CATEGORY_COLUMN_NAME = "name";

    private static final String INGREDIENT_TABLE_NAME = "ingredient";
    private static final String INGREDIENT_COLUMN_ID = "id";
    private static final String INGREDIENT_COLUMN_NAME = "name";

    private static final String FOOD_TABLE_NAME = "food";
    private static final String FOOD_COLUMN_ID = "id";
    private static final String FOOD_COLUMN_NAME = "name";
    private static final String FOOD_COLUMN_PREPARE = "prepare";
    private static final String FOOD_COLUMN_COVER_IMAGE_ID = "cover_image_id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private void initTables() {
        tables = new ArrayList<>();

        Map<String, String> userColumns = new LinkedHashMap<>();
        userColumns.put(USER_COLUMN_ID, INTEGER_TYPE);
        userColumns.put(USER_COLUMN_FULL_NAME, TEXT_TYPE);
        userColumns.put(USER_COLUMN_EMAIL, TEXT_TYPE);
        userColumns.put(USER_COLUMN_CURRENT_ACCOUNT, INTEGER_TYPE);
        tables.add(new TableInfo(USER_TABLE_NAME, userColumns));

        Map<String, String> categoryColumns = new LinkedHashMap<>();
        categoryColumns.put(CATEGORY_COLUMN_ID, INTEGER_TYPE);
        categoryColumns.put(CATEGORY_COLUMN_IMAGE_ID, TEXT_TYPE);
        categoryColumns.put(CATEGORY_COLUMN_NAME, TEXT_TYPE);
        tables.add(new TableInfo(CATEGORY_TABLE_NAME, categoryColumns));

        Map<String, String> ingredientColumns = new LinkedHashMap<>();
        ingredientColumns.put(INGREDIENT_COLUMN_ID, INTEGER_TYPE);
        ingredientColumns.put(INGREDIENT_COLUMN_NAME, TEXT_TYPE);
        tables.add(new TableInfo(
                INGREDIENT_TABLE_NAME,
                ingredientColumns
        ));

        Map<String, String> foodColumns = new LinkedHashMap<>();
        foodColumns.put(FOOD_COLUMN_ID, INTEGER_TYPE);
        foodColumns.put(FOOD_COLUMN_NAME, TEXT_TYPE);
        foodColumns.put(FOOD_COLUMN_PREPARE, TEXT_TYPE);
        foodColumns.put(FOOD_COLUMN_COVER_IMAGE_ID, INTEGER_TYPE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initTables();
        for (TableInfo tableInfo : tables) {
            String createTableCommand = "CREATE TABLE " + tableInfo.getTableName() + "(";

            Map<String, String> columns = tableInfo.getColumns();
            for (Map.Entry<String, String> entry : columns.entrySet()) {
                String columnName = entry.getKey();
                String columnType = entry.getValue();

                createTableCommand += columnName + " " + columnType;
                if (columnName == "id")
                    createTableCommand += " PRIMARY KEY";
                createTableCommand += ", ";
            }
            createTableCommand = createTableCommand.substring(
                    0,
                    createTableCommand.length() - 2
            ) + ")";
            db.execSQL(createTableCommand);
        }
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {
        for (TableInfo tableInfo : tables) {
            String dropTableCommand = "DROP TABLE IF EXISTS " + tableInfo.getTableName();
            db.execSQL(dropTableCommand);
        }

        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_ID, user.getId());
        values.put(USER_COLUMN_FULL_NAME, user.getFullName());
        values.put(USER_COLUMN_EMAIL, user.getEmail());
        values.put(USER_COLUMN_CURRENT_ACCOUNT, user.getCurrentAccount());

        db.insert(USER_TABLE_NAME, null, values);
    }

    public User getCurrentAccount() {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query(
                    USER_TABLE_NAME,
                    null,
                    USER_COLUMN_CURRENT_ACCOUNT + " = ?",
                    new String[]{String.valueOf(1)},
                    null,
                    null,
                    null
            );

            if (cursor != null)
                cursor.moveToFirst();

            User user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            return user;
        } catch (CursorIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void logout() {
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_CURRENT_ACCOUNT + "=1;";
        db.execSQL(deleteQuery);
    }

    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORY_COLUMN_ID, category.getId());
        values.put(CATEGORY_COLUMN_IMAGE_ID, category.getImageID());
        values.put(CATEGORY_COLUMN_NAME, category.getCategoryName());

        db.insert(CATEGORY_TABLE_NAME, null, values);
    }

    public List<Category> getAllCategories() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Category> categories = new ArrayList<>();
        try {
            String getAllCategoriesCommand = "SELECT * FROM " + CATEGORY_TABLE_NAME;
            Cursor cursor = db.rawQuery(getAllCategoriesCommand, null);


            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Category category = new Category(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)
                    );
                    categories.add(category);

                    cursor.moveToNext();
                }
            }

            return categories;

        } catch (CursorIndexOutOfBoundsException ex) {
        }
        return categories;
    }

    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query(
                    CATEGORY_TABLE_NAME,
                    null,
                    CATEGORY_COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null
            );

            if (cursor != null)
                cursor.moveToFirst();

            Category category = new Category(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            return category;
        } catch (CursorIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INGREDIENT_COLUMN_ID, ingredient.getId());
        values.put(INGREDIENT_COLUMN_NAME, ingredient.getIngredientName());

        db.insert(INGREDIENT_TABLE_NAME, null, values);
    }

    public List<Ingredient> getAllIngredients() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Ingredient> ingredients = new ArrayList<>();
        try {
            String getAllIngredientsCommand = "SELECT * FROM " + INGREDIENT_TABLE_NAME;
            Cursor cursor = db.rawQuery(getAllIngredientsCommand, null);


            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Ingredient ingredient = new Ingredient(
                            cursor.getInt(0),
                            cursor.getString(1)
                    );
                    ingredients.add(ingredient);

                    cursor.moveToNext();
                }
            }

            return ingredients;

        } catch (CursorIndexOutOfBoundsException ex) {
        }
        return ingredients;
    }

    public Ingredient getIngredient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query(
                    INGREDIENT_TABLE_NAME,
                    null,
                    INGREDIENT_COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null
            );

            if (cursor != null)
                cursor.moveToFirst();

            Ingredient ingredient = new Ingredient(
                    cursor.getInt(0),
                    cursor.getString(1)
            );
            return ingredient;
        } catch (CursorIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FOOD_COLUMN_ID, food.getId());
        values.put(FOOD_COLUMN_NAME, food.getName());
        values.put(FOOD_COLUMN_PREPARE, food.getPrepare());
        values.put(FOOD_COLUMN_COVER_IMAGE_ID, food.getImageID());

        db.insert(FOOD_TABLE_NAME, null, values);
    }

    public Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query(
                    FOOD_TABLE_NAME,
                    null,
                    FOOD_COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null
            );

            if (cursor != null)
                cursor.moveToFirst();

            Food food = new Food(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(2)
            );
            return food;
        } catch (CursorIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public List<Food> getAllFoods() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Food> foods = new ArrayList<>();
        try {
            String getAllFoodsCommand = "SELECT * FROM " + FOOD_TABLE_NAME;
            Cursor cursor = db.rawQuery(getAllFoodsCommand, null);


            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Food food = new Food(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getInt(2)
                    );
                    foods.add(food);

                    cursor.moveToNext();
                }
            }

            return foods;

        } catch (CursorIndexOutOfBoundsException ex) {
        }
        return foods;
    }
}
