package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {
    EditText editTextUserName;
    EditText editTextUserLastName;
    EditText editTextPhone;
    Button formAddUserBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserLastName = findViewById(R.id.editTextUserLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        formAddUserBtn = findViewById(R.id.formAddUserBtn);


        formAddUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUserName(editTextUserName.getText().toString());
                user.setUserLastName(editTextUserLastName.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                Users users = new Users(AddUserActivity.this);
                users.addUser(user);
                onBackPressed(); // искусственное нажатие "назад"
            }
        });

    }
}
