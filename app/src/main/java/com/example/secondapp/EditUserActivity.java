package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUserActivity extends AppCompatActivity {

    EditText editTextEditUserName;
    EditText editTextEditUserLastName;
    EditText editTextEditPhone;
    Button saveBtn;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);


        editTextEditUserName = findViewById(R.id.editTextEditUserName);
        editTextEditUserLastName = findViewById(R.id.editTextEditUserLastName);
        editTextEditPhone = findViewById(R.id.editTextEditPhone);
        saveBtn = findViewById(R.id.saveBtn);


        user = (User) getIntent().getSerializableExtra("user");
        editTextEditUserName.setText(user.getUserName());
        editTextEditUserLastName.setText(user.getUserLastName());
        editTextEditPhone.setText(user.getPhone());


        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                user.setUserName(editTextEditUserName.getText().toString());
                user.setUserLastName(editTextEditUserLastName.getText().toString());
                user.setPhone(editTextEditPhone.getText().toString());
                Users users = new Users(EditUserActivity.this);
                users.updateUser(user);
                Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}