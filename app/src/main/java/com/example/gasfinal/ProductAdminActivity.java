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

public class ProductAdminActivity extends AppCompatActivity {
    EditText id ,name,weight,price;
    Button insert, update, delete, view;
    DBHelperBuddhishan DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_admin);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        price = findViewById(R.id.price);


        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelperBuddhishan(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String weightTXT = weight.getText().toString();
                String priceTXT = price.getText().toString();

                if (idTXT.equals("") || nameTXT.equals("") || weightTXT.equals("") || priceTXT.equals("")) {
                    Toast.makeText(ProductAdminActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkinsertdata = DB.insertproductdata(idTXT, nameTXT, weightTXT, priceTXT);
                    if (checkinsertdata == true)
                        Toast.makeText(ProductAdminActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ProductAdminActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();

                }
            }

            });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String weightTXT = weight.getText().toString();
                String priceTXT = price.getText().toString();


                Boolean checkupdatedata = DB.updateproductdata(idTXT,nameTXT,weightTXT ,priceTXT);
                if(checkupdatedata==true)
                    Toast.makeText(ProductAdminActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProductAdminActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                Boolean checkudeletedata = DB.deleteproductdata(idTXT);
                if(checkudeletedata==true)
                    Toast.makeText(ProductAdminActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProductAdminActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getproductdata();
                if(res.getCount()==0){
                    Toast.makeText(ProductAdminActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("weight :"+res.getString(2)+"\n");
                    buffer.append("Price:"+res.getString(3)+"\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductAdminActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Product Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            }        });
    }}