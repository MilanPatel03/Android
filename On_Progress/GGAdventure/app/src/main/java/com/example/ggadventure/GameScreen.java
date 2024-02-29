package com.example.ggadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
    }

    public void goEasyScreen(View view){

        Intent easyLevel = new Intent(this,easyLevelScreen.class);
        startActivity(easyLevel);
    }

    public void goMediumScreen(View view){

        Intent mediumLevel = new Intent(this,mediumLevelScreen.class);
        startActivity(mediumLevel);
    }
}