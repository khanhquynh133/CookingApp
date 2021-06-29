package com.finalexam.cookingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.finalexam.cookingapp.model.User;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mobile";
    private static final int DATABASE_VERSION = 3;

    private static final String USER_TABLE_NAME = "user";
    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_FULL_NAME = "full_name";
    private static final String USER_COLUMN_EMAIL = "email";
    private static final String USER_COLUMN_CURRENT_ACCOUNT = "current_account";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableCommand = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER)", USER_TABLE_NAME, USER_COLUMN_ID, USER_COLUMN_FULL_NAME, USER_COLUMN_EMAIL, USER_COLUMN_CURRENT_ACCOUNT);
        db.execSQL(createUserTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", USER_TABLE_NAME);
        db.execSQL(drop_students_table);

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
            Cursor cursor = db.query(USER_TABLE_NAME, null, USER_COLUMN_CURRENT_ACCOUNT + " = ?", new String[] {String.valueOf(1)},null, null, null);

            if (cursor != null)
                cursor.moveToFirst();

            User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            return user;
        }
        catch (CursorIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void logout() {
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_CURRENT_ACCOUNT + "=1;";
        db.execSQL(deleteQuery);
    }
}
