package com.example.discount.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.discount.Mall.MallItem;
import com.example.discount.Util.Util;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - Structured Query Language
        String CREATE_SUBS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_IMAGE + " TEXT" + ")";

        db.execSQL(CREATE_SUBS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    //CRUD
    //Create, Read, Update, Delete

    public void addSub(MallItem mallItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, mallItem.getName());
        contentValues.put(Util.KEY_IMAGE, mallItem.getImageResourse());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public MallItem getSub(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME,
                        Util.KEY_IMAGE}, Util.KEY_ID + "=?", new String[] {String.valueOf(id)},
                null, null,
                null, null);
        MallItem mallItem = new MallItem();
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                mallItem = new MallItem(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), false);
            } finally {
                cursor.close();
            }

        }

        return mallItem;
    }

    public ArrayList<MallItem> getAllSubs() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<MallItem> SubsList = new ArrayList<>();

        String selectAllSubs = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllSubs, null);
        if (cursor.moveToFirst()) {
            try {
                do {
                    MallItem mallItem = new MallItem();
                    mallItem.setId(Integer.parseInt(cursor.getString(0)));
                    mallItem.setName(cursor.getString(1));
                    mallItem.setImageResourse(cursor.getString(2));

                    SubsList.add(mallItem);
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }

        }

        return SubsList;
    }

    public int updateSub(MallItem mallItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, mallItem.getName());
        contentValues.put(Util.KEY_IMAGE, mallItem.getImageResourse());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[] {String.valueOf(mallItem.getId())});
    }

    public void deleteSub(MallItem mallItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[] {String.valueOf(mallItem.getId())});

        db.close();
    }

    public int getSubsCount() {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = -1;
        if (cursor != null) {
            try {
                count = cursor.getCount();
            } finally {
                cursor.close();
            }

        }
        return count;

    }

    public void Delete() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + Util.TABLE_NAME);
    }
}
