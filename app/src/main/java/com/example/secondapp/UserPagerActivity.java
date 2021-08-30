package com.example.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class UserPagerActivity extends AppCompatActivity {
    private ViewPager viewPager; //создали view pager
    Users users; // создали переменную users


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pager);
        viewPager = findViewById(R.id.userViewPager); //нашли на активности вью пэйджер
//
        users = new Users(UserPagerActivity.this);

        PagerAdapter pagerAdapter = new ViewPagerAdapter(UserPagerActivity.this, users);

        viewPager.setAdapter(pagerAdapter);
    }
}
