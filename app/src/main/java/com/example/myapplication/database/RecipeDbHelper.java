package com.example.myapplication.database;

import static com.example.myapplication.database.DbConstants.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_RECIPE + " TEXT, " +
                    COLUMN_INGREDIENTS + " TEXT, " +
                    COLUMN_RATING + " TEXT);";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public RecipeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

}