package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Resta extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resta);

        // Botón para regresar al layout anterior-
        Button buttonRegRes = findViewById(R.id.buttonRegRes);
        buttonRegRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resta.this, Suma.class);
                startActivity(intent);
            }
        });

        // Botón para regresar al menú principal
        Button buttonMenuRes = findViewById(R.id.buttonMenuRes);
        buttonMenuRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resta.this, InstruccionesMateActivity.class);
                startActivity(intent);
            }
        });

        // Botón para ir al siguiente layout
        Button buttonSigRes = findViewById(R.id.buttonSigRes);
        buttonSigRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resta.this, Multiplicacion.class);
                startActivity(intent);
            }
        });
    }
}