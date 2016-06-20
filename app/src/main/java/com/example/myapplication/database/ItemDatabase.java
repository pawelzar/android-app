package com.example.myapplication.database;

import static com.example.myapplication.database.DbConstants.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Item;


public class ItemDatabase {

    private ItemDbHelper itemDbHelper;

    public ItemDatabase(Context context) {
        this.itemDbHelper = new ItemDbHelper(context);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = itemDbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, item.getName());
            values.put(COLUMN_DESCRIPTION, item.getDescription());
            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }

    public Item getItem(int position) {
        SQLiteDatabase db = itemDbHelper.getReadableDatabase();

        try {
            String[] projection = { COLUMN_NAME, COLUMN_DESCRIPTION };
            String sortOrder = COLUMN_NAME + " DESC";

            Cursor cursor = db.query(TABLE_NAME,  // The table to query
                    projection,                   // The columns to return
                    null,                         // The columns for the WHERE clause
                    null,                         // The values for the WHERE clause
                    null,                         // don't group the rows
                    null,                         // don't filter by row groups
                    sortOrder                     // The sort order
            );

            return getItemFromCursor(position, cursor);
        } finally {
            db.close();
        }
    }

    private Item getItemFromCursor(int position, Cursor cursor) {
        try {
            cursor.moveToPosition(position);
            String name = cursor.getString(0);
            String description = cursor.getString(1);
            cursor.close();

            return new Item(name, description);
        } finally {
            cursor.close();
        }
    }

    public int getItemsNumber() {
        SQLiteDatabase db = itemDbHelper.getReadableDatabase();

        try {
            return (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, null, null);
        } finally {
            db.close();
        }
    }

    public void deleteAll() {
        SQLiteDatabase db = itemDbHelper.getWritableDatabase();

        try {
            db.delete(TABLE_NAME, null, null);
        } finally {
            db.close();
        }
    }

    public void deleteItem(String name) {
        SQLiteDatabase db = itemDbHelper.getWritableDatabase();

        try {
            String whereClause = COLUMN_NAME + "=?";
            String[] whereArgs = new String[] { String.valueOf(name) };
            db.delete(TABLE_NAME, whereClause, whereArgs);
        } finally {
            db.close();
        }
    }

    public void updateItem(String name, Item item) {
        SQLiteDatabase db = itemDbHelper.getWritableDatabase();

        try {
            //ContentValues values = new ContentValues();
            //values.put(COLUMN_NAME, String.valueOf(item.getName()));
            //values.put(COLUMN_DESCRIPTION, String.valueOf(item.getDescription()));
            //db.update(TABLE_NAME, values, COLUMN_NAME + "=", new String[] {String.valueOf(name)});
            String whereClause = COLUMN_NAME + "=?";
            String[] whereArgs = new String[] { String.valueOf(name) };
            db.delete(TABLE_NAME, whereClause, whereArgs);

            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, item.getName());
            values.put(COLUMN_DESCRIPTION, item.getDescription());
            db.insert(TABLE_NAME, null, values);
        } finally {
            db.close();
        }
    }
}
