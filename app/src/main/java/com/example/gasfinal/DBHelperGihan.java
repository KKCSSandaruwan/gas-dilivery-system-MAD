package com.example.gasfinal;





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperGihan extends SQLiteOpenHelper {
    public DBHelperGihan(Context context) {
        super(context, "DelivaryData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table DelivaryDetails(id Text primary key, name TEXT, address TEXT, phone TEXT,email TEXT,product_id TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists DelivaryDetails");

    }

    public Boolean insertDelivaryData(String id, String name, String address, String phone, String email, String product_id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name",name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("product_id", product_id);
        long result=DB.insert("DelivaryDetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateDelivaryData(String id, String name, String address, String phone, String email, String product_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name",name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("product_id", product_id);
        Cursor cursor = DB.rawQuery("Select * from DelivaryDetails where id = ?", new String []{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("DelivaryDetails", contentValues, "id=?", new String []{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletedata (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DelivaryDetails where id = ?", new String []{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("DelivaryDetails", "id=?", new String []{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DelivaryDetails", null);
        return cursor;

    }
}