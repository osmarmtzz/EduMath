package com.example.edumath;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Etapa3Activity extends AppCompatActivity {
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etapa3);

        TextView question = findViewById(R.id.textQuestion);
        EditText answer = findViewById(R.id.editAnswer);
        Button submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(v -> {
            String userAnswer = answer.getText().toString().trim();
            if (userAnswer.equals("25")) {
                score += 20;
                Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                saveScore(score);
                finish();
            } else {
                Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveScore(int score) {
        SharedPreferences preferences = getSharedPreferences("Puntuaciones", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int currentHighScore = preferences.getInt("highScore", 0);

        if (score > currentHighScore) {
            editor.putInt("highScore", score);
            editor.apply();
        }
    }
}