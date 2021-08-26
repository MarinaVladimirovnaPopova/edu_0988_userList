package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    private TextView userNameTextView;
    private TextView phoneTextView;
    private User user;
    private Button editUserDataBtn; //создали кнопки редактирования
    private Button deleteUserBtn; //и удаления
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userNameTextView = findViewById(R.id.userNameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        user = (User) getIntent().getSerializableExtra("user");
        userNameTextView.setText(user.getUserName()+"\n"+user.getUserLastName());
        phoneTextView.setText(user.getPhone());
        editUserDataBtn = findViewById(R.id.editUserDataBtn);//нашли на активности
        deleteUserBtn = findViewById(R.id.deleteUserBtn);//нашли на активности


        editUserDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoActivity.this, EditUserActivity.class);//открываем активность редактирования
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.getUuid();
                Users users = new Users(UserInfoActivity.this);
                users.deleteUser(user.getUuid());
                onBackPressed();
            }
        });
    }
}