package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Comparacion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparacion);

        // Botón para regresar al layout anterior-
        Button buttonRegComp = findViewById(R.id.buttonRegComp);
        buttonRegComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comparacion.this, Division.class);
                startActivity(intent);
            }
        });

        // Botón para regresar al menú principal
        Button buttonMenuComp = findViewById(R.id.buttonMenuComp);
        buttonMenuComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comparacion.this, InstruccionesMateActivity.class);
                startActivity(intent);
            }
        });
    }
}