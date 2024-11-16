package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Division extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.division);

        // Botón para regresar al layout anterior-
        Button buttonRegDiv = findViewById(R.id.buttonRegDiv);
        buttonRegDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Division.this, Multiplicacion.class);
                startActivity(intent);
            }
        });

        // Botón para regresar al menú principal
        Button buttonMenuDiv = findViewById(R.id.buttonMenuDiv);
        buttonMenuDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Division.this, InstruccionesMateActivity.class);
                startActivity(intent);
            }
        });

        // Botón para ir al siguiente layout
        Button buttonSigDiv = findViewById(R.id.buttonSigDiv);
        buttonSigDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Division.this, Comparacion.class);
                startActivity(intent);
            }
        });

    }
}