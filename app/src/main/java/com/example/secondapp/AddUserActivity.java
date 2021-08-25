package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;



    Button buttonAdd;
    Button buttonDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDel = findViewById(R.id.buttonDel);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddUserActivity.this, "Скоро всё будет...", Toast.LENGTH_SHORT).show();

            }
        });


        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddUserActivity.this, "Скоро всё будет...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}