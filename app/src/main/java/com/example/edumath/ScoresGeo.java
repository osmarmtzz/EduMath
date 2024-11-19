package com.example.edumath;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoresGeo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoresgeo);

        TextView highScore = findViewById(R.id.textHighScore);

        SharedPreferences preferences = getSharedPreferences("Puntuaciones", MODE_PRIVATE);
        int score = preferences.getInt("highScore", 0);
        highScore.setText(String.valueOf(score));
    }
}