package com.example.ggadventure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class easyLevelScreen extends AppCompatActivity {

    //private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level_screen);

        viewPager = findViewById(R.id.easyPager);


        EasyAdapter easyAdapter = new EasyAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        easyAdapter.addFragment(new fragmentEasyOne());
        easyAdapter.addFragment(new fragmentEasyTwo());

        viewPager.setAdapter(easyAdapter);
    }
}