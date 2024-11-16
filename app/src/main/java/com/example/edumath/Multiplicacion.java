package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Multiplicacion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplicacion);

        // Botón para regresar al layout anterior-
        Button buttonRegMulti = findViewById(R.id.buttonRegMulti);
        buttonRegMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Multiplicacion.this, Resta.class);
                startActivity(intent);
            }
        });

        // Botón para regresar al menú principal
        Button buttonMenuMulti= findViewById(R.id.buttonMenuMulti);
        buttonMenuMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Multiplicacion.this, InstruccionesMateActivity.class);
                startActivity(intent);
            }
        });

        // Botón para ir al siguiente layout
        Button buttonSigMulti = findViewById(R.id.buttonSigMulti);
        buttonSigMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Multiplicacion.this, Division.class);
                startActivity(intent);
            }
        });
    }
}
