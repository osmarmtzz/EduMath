package com.example.edumath;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Etapa1Activity extends AppCompatActivity {
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etapa1);

        TextView question = findViewById(R.id.textQuestion);
        ImageView figure = findViewById(R.id.imageFigure);
        EditText answer = findViewById(R.id.editAnswer);
        Button submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(v -> {
            v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(200);

                String userAnswer = answer.getText().toString().trim().toLowerCase();
                if (userAnswer.equals("triangulo")) {
                    score += 10;
                    Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                    saveScore(score);
                    finish();
                } else {
                    Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void saveScore(int score) {
        SharedPreferences preferences = getSharedPreferences("Puntuaciones", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("score", score);
        editor.apply();
    }
}
