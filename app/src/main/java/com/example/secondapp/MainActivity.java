package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Button addUserBtn;
    ArrayList<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //R = ресурс


        addUserBtn = findViewById(R.id.addUserBtn);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }
    private void recyclerViewInit(){ // запускаем на момент вызова метода onResume, т.е. при выполнении onResume запустится потом и recyclerViewInit
        Users users = new Users(MainActivity.this); // создание объекта users перетащили
        userList = users.getUserList(); //создание списка
        userAdapter = new UserAdapter(userList);//адаптер
        recyclerView.setAdapter(userAdapter);// установка адаптера
    }

    @Override
    public void onResume(){ //заново прочтет пользователей из базы данных, если они добавились.
        super.onResume();
        recyclerViewInit();
    }

    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTextView;
        User user;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }
        public void bind(String userName, User user){
            this.user = user;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<User> users;

        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            userHolder.bind(userString, user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}