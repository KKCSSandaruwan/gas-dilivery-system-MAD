package com.example.gasfinal;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperBuddhishan extends SQLiteOpenHelper {
    public DBHelperBuddhishan(Context context) {
        super(context, "Productdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Productdetails(id TEXT primary key,name TEXT, weight TEXT, price TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Productdetails");

    }

    public Boolean insertproductdata(String id, String name, String weight,String price)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("weight", weight);
        contentValues.put("price", price);
        long result=DB.insert("Productdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateproductdata(String id, String name, String weight,String price) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("weight", weight);
        contentValues.put("price", price);
        Cursor cursor = DB.rawQuery("Select * from Productetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("Productdetails", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deleteproductdata (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from productdetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Productdetails", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getproductdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Productdetails", null);
        return cursor;

    }
}