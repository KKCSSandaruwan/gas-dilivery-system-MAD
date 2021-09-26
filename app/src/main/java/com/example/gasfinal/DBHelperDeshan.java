package com.example.gasfinal;







import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperDeshan extends SQLiteOpenHelper {
    public DBHelperDeshan(Context context) {
        super(context, "ProfileData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table ProfileDetails(name TEXT primary key , address TEXT, phone TEXT,email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists ProfileDetails");

    }

    public Boolean insertProfileData( String name, String address, String phone, String email)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);

        long result=DB.insert("ProfileDetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateProfileData( String name, String address, String phone, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);

        Cursor cursor = DB.rawQuery("Select * from ProfileDetails where name = ?", new String []{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("ProfileDetails", contentValues, "name=?", new String []{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deleteprofiledata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ProfileDetails where name = ?", new String []{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("ProfileDetails", "name=?", new String []{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getprofiledata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ProfileDetails", null);
        return cursor;

    }
}