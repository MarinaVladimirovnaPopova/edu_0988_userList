package com.example.secondapp;
import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {
    private Users users;
    private Context context;

    public ViewPagerAdapter(Context context, Users users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.getUserList().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView firstNameTextView;
        TextView phoneTextView;
        Button editUserDataBtn; //создали кнопки редактирования
        Button deleteUserBtn; //и удаления


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_user_info, container,
                false);

        firstNameTextView = itemView.findViewById(R.id.userNameTextView);
        phoneTextView = itemView.findViewById(R.id.phoneTextView);
        editUserDataBtn = itemView.findViewById(R.id.editUserDataBtn);//нашли на активности
        deleteUserBtn = itemView.findViewById(R.id.deleteUserBtn);//нашли на активности

        User user = users.getUserList().get(position);

        firstNameTextView.setText(user.getUserName()+"\n"+user.getUserLastName());

        phoneTextView.setText(user.getPhone());

        editUserDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditUserActivity.class);//открываем активность редактирования
                intent.putExtra("user", user);
                startActivity(context,intent,null);
            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.getUuid();
                Users users = new Users(context);
                users.deleteUser(user.getUuid());
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(context,intent,null);
            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}