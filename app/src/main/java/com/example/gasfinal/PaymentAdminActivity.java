package com.example.gasfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentAdminActivity extends AppCompatActivity {

    EditText id ,name,email,card_number,expiry_date ,cvv;
    Button insert, update, delete, view;
    DBHelperChamindu DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_admin);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        card_number = findViewById(R.id.card_number);
        expiry_date = findViewById(R.id.expiry_date);
        cvv = findViewById(R.id.cvv);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
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

                Boolean checkinsertdata = DB.insertpaymentdata(idTXT,nameTXT,emailTXT ,card_numberTXT,expiry_dateTXT,cvvTXT);
                if(checkinsertdata==true)
                    Toast.makeText(PaymentAdminActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PaymentAdminActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String card_numberTXT = card_number.getText().toString();
                String expiry_dateTXT = expiry_date.getText().toString();
                String cvvTXT = cvv.getText().toString();

                Boolean checkupdatedata = DB.updatepaymentdata(idTXT,nameTXT,emailTXT ,card_numberTXT,expiry_dateTXT,cvvTXT);
                if(checkupdatedata==true)
                    Toast.makeText(PaymentAdminActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PaymentAdminActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                Boolean checkudeletedata = DB.deletepaymentdata(idTXT);
                if(checkudeletedata==true)
                    Toast.makeText(PaymentAdminActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PaymentAdminActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getpaymentdata();
                if(res.getCount()==0){
                    Toast.makeText(PaymentAdminActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Email :"+res.getString(2)+"\n");
                    buffer.append("Card_number :"+res.getString(3)+"\n");
                    buffer.append("Expiry_date :"+res.getString(4)+"\n");
                    buffer.append("cvv :"+res.getString(5)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentAdminActivity.this);
                builder.setCancelable(true);
                builder.setTitle("payment Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            }        });
    }
}