package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InstruccionesMateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intrucciones_mate);

        // Botón para volver al menú principal
        Button btnAtras = findViewById(R.id.buttonMenuMath);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesMateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnIrJuego = findViewById(R.id.buttonContinueMath);
        btnIrJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesMateActivity.this, JuegoMateActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear previous activities
                startActivity(intent);
                // Remove finish() to prevent immediately closing the game
            }
        });
        Button buttonLecMate = findViewById(R.id.buttonLecMate);
        btnIrJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesMateActivity.this, Suma.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear previous activities
                startActivity(intent);
            }
        });
    }
}