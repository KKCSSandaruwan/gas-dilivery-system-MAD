package com.example.gasfinal;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    EditText name,address,phone ,email ;
    Button insert, update, delete, view;
    DBHelperDeshan DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelperDeshan(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameTXT = name.getText().toString();
                String addressTXT = address.getText().toString();
                String phoneTXT = phone.getText().toString();
                String emailTXT = email.getText().toString();

                if (nameTXT.equals("") || addressTXT.equals("") || phoneTXT.equals("") || emailTXT.equals("")) {
                    Toast.makeText(ProfileActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean checkinsertdata = DB.insertProfileData(nameTXT, addressTXT, phoneTXT, emailTXT);
                    if (checkinsertdata == true)
                        Toast.makeText(ProfileActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ProfileActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();


                }
            }
            });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameTXT = name.getText().toString();
                String addressTXT = address.getText().toString();
                String phoneTXT = phone.getText().toString();
                String emailTXT = email.getText().toString();


                Boolean checkupdatedata = DB.updateProfileData(nameTXT,addressTXT ,phoneTXT,emailTXT);
                if(checkupdatedata==true)
                    Toast.makeText(ProfileActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProfileActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deleteprofiledata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(ProfileActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProfileActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getprofiledata();
                if(res.getCount()==0){
                    Toast.makeText(ProfileActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){

                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Address :"+res.getString(1)+"\n");
                    buffer.append("Phone :"+res.getString(2)+"\n");
                    buffer.append("Email :"+res.getString(3)+"\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Profile Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            }        });
    }}