package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Juego_Geo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_geo);

        findViewById(R.id.buttonStage1).setOnClickListener(v -> startActivity(new Intent(this, Etapa1Activity.class)));
        findViewById(R.id.buttonStage2).setOnClickListener(v -> startActivity(new Intent(this, Etapa2Activity.class)));
        findViewById(R.id.buttonStage3).setOnClickListener(v -> startActivity(new Intent(this, Etapa3Activity.class)));
        findViewById(R.id.buttonScores).setOnClickListener(v -> startActivity(new Intent(this, ScoresGeo.class)));
    }
}