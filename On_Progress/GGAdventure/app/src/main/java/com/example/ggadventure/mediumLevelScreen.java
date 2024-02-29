package com.example.ggadventure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class mediumLevelScreen extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_level_screen);

        viewPager = findViewById(R.id.mediumPager);

        MediumAdapter mediumAdapter = new MediumAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mediumAdapter.addFragment(new fragmentMediumOne());
        mediumAdapter.addFragment(new fragmentMediumTwo());

        viewPager.setAdapter(mediumAdapter);
    }
}