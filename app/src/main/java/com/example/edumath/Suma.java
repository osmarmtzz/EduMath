package com.example.edumath;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Suma extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suma);

        // Botón para regresar al menú principal
        Button buttonMenuSum = findViewById(R.id.buttonMenuSum);
        buttonMenuSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Suma.this, InstruccionesMateActivity.class);
                startActivity(intent);
            }
        });

        // Botón para ir al siguiente layout
        Button buttonSigSum = findViewById(R.id.buttonSigSum);
        buttonSigSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Suma.this, Resta.class);
                startActivity(intent);
            }
        });
    }
}
