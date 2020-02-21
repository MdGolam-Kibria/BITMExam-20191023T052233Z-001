package com.example.bitmexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity {
    EditText NameEditText, EmailEditText, AdressEditText, passwordEditText;
    Button saveBtn, showBtn;
    ImageView imageview;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);


        NameEditText = findViewById(R.id.NameEditText);
        EmailEditText = findViewById(R.id.EmailEditText);
        AdressEditText = findViewById(R.id.AdressEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        imageview = findViewById(R.id.imageview);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonDetails personDetails = new PersonDetails();
                personDetails.setName(NameEditText.getText().toString());
                personDetails.setEmail(EmailEditText.getText().toString());
                personDetails.setAdress(AdressEditText.getText().toString());

                personDetails.setPassword(passwordEditText.getText().toString());

                if (NameEditText.getText().toString().equals("") || EmailEditText.getText().toString().equals("") || AdressEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "data missing", Toast.LENGTH_SHORT).show();
                }

                dataBaseHelper = new DataBaseHelper(getApplicationContext());

                long id = dataBaseHelper.insertData(personDetails);

                if (id == -1) {
                    Toast.makeText(getApplicationContext(), "Insert error!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "data Insert!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        showBtn = findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserDetails.this, ListViewShow.class);
                startActivity(intent);

            }
        });
    }
}
