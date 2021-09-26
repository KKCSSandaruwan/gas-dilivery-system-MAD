package com.example.gasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DelivaryActivity extends AppCompatActivity {

    EditText id ,name,address,phone ,email ,product_id;
    Button insert,makePay;
    DBHelperGihan DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivary);




            id = findViewById(R.id.id);
            name = findViewById(R.id.name);
            address = findViewById(R.id.address);
            phone = findViewById(R.id.phone);
            email = findViewById(R.id.email);
            product_id = findViewById(R.id.product_id);

            insert = findViewById(R.id.btnInsert);
            makePay = findViewById(R.id.makePay);

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
                    if (idTXT.equals("") || nameTXT.equals("") || addressTXT.equals("") || phoneTXT.equals("") || emailTXT.equals("") || product_idTXT.equals("")) {
                        Toast.makeText(DelivaryActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean checkinsertdata = DB.insertDelivaryData(idTXT, nameTXT, addressTXT, phoneTXT, emailTXT, product_idTXT);
                        if (checkinsertdata == true)
                            Toast.makeText(DelivaryActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DelivaryActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                });

        makePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(intent);


            }
        });
    }
}