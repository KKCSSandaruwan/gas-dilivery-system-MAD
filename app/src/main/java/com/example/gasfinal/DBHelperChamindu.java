package com.example.gasfinal;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperChamindu extends SQLiteOpenHelper {
    public DBHelperChamindu(Context context) {
        super(context, "Paymentdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Paymentdetails(id TEXT primary key, name TEXT, email TEXT, card_number TEXT, expiry_date TEXT, cvv TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Paymentdetails");

    }

    public Boolean insertpaymentdata(String id, String name, String email,String card_number,String expiry_date,String cvv)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("card_number", card_number);
        contentValues.put("expiry_date", expiry_date);
        contentValues.put("cvv", cvv);
        long result=DB.insert("Paymentdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updatepaymentdata(String id, String name, String email,String card_number,String expiry_date,String cvv) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("card_number", card_number);
        contentValues.put("expiry_date", expiry_date);
        contentValues.put("cvv", cvv);
        Cursor cursor = DB.rawQuery("Select * from Paymentdetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("Paymentdetails", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletepaymentdata (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Paymentdetails where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Paymentdetails", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getpaymentdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Paymentdetails", null);
        return cursor;

    }
}