package com.example.gasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PaymentActivity extends AppCompatActivity {

    EditText id ,name,email,card_number,expiry_date,cvv;
    Button insert;
    DBHelperChamindu DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        card_number = findViewById(R.id.card_number);
        expiry_date = findViewById(R.id.expiry_date);
        cvv = findViewById(R.id.cvv);

        insert = findViewById(R.id.btnInsert);


        DB = new DBHelperChamindu(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String card_numberTXT = card_number.getText().toString();
                String expiry_dateTXT = expiry_date.getText().toString();
                String cvvTXT = cvv.getText().toString();

                if (idTXT.equals("") || nameTXT.equals("") || emailTXT.equals("") || card_numberTXT.equals("") || expiry_dateTXT.equals("") || cvvTXT.equals("")) {
                    Toast.makeText(PaymentActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkinsertdata = DB.insertpaymentdata(idTXT, nameTXT, emailTXT, card_numberTXT, expiry_dateTXT, cvvTXT);
                    if (checkinsertdata == true) {
                        Toast.makeText(PaymentActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
                        startActivity(intent);


                    } else
                        Toast.makeText(PaymentActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();

                }
            }

        });







    }
}