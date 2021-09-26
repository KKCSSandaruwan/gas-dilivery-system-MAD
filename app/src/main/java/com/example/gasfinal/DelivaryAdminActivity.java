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

public class DelivaryAdminActivity extends AppCompatActivity {
    EditText id ,name,address,phone ,email ,product_id;
    Button insert, update, delete, view;
    DBHelperGihan DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivary_admin);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        product_id = findViewById(R.id.product_id);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelperGihan(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String addressTXT = address.getText().toString();
                String phoneTXT = phone.getText().toString();
                String emailTXT = email.getText().toString();
                String product_idTXT = product_id.getText().toString();

                Boolean checkinsertdata = DB.insertDelivaryData(idTXT,nameTXT,addressTXT ,phoneTXT,emailTXT,product_idTXT);
                if(checkinsertdata==true)
                    Toast.makeText(DelivaryAdminActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DelivaryAdminActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String addressTXT = address.getText().toString();
                String phoneTXT = phone.getText().toString();
                String emailTXT = email.getText().toString();
                String product_idTXT = product_id.getText().toString();

                Boolean checkupdatedata = DB.updateDelivaryData(idTXT,nameTXT,addressTXT ,phoneTXT,emailTXT,product_idTXT);
                if(checkupdatedata==true)
                    Toast.makeText(DelivaryAdminActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DelivaryAdminActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                Boolean checkudeletedata = DB.deletedata(idTXT);
                if(checkudeletedata==true)
                    Toast.makeText(DelivaryAdminActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DelivaryAdminActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(DelivaryAdminActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Address :"+res.getString(2)+"\n");
                    buffer.append("Phone :"+res.getString(3)+"\n");
                    buffer.append("Email :"+res.getString(4)+"\n");
                    buffer.append("Product_id :"+res.getString(5)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(DelivaryAdminActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delivary Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            }        });
    }}