package com.example.gasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button btnDelivaryAdmin,btnProductAdmin,btnPaymentAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnDelivaryAdmin = findViewById(R.id.btnDelivaryAdmin);
        btnProductAdmin = findViewById(R.id.btnProductAdmin);
        btnPaymentAdmin = findViewById(R.id.btnPaymentAdmin);

        btnDelivaryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DelivaryAdminActivity.class);
                startActivity(intent);


            }
        });

        btnProductAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProductAdminActivity.class);
                startActivity(intent);


            }
        });

        btnPaymentAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PaymentAdminActivity.class);
                startActivity(intent);


            }
        });


    }
}