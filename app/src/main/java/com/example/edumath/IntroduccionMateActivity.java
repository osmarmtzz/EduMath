package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class IntroduccionMateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduccion_matematicas);

        Button btnIrAlJuego = findViewById(R.id.btnIrAlJuego);
        Button btnVolverMenu = findViewById(R.id.btnVolverMenu);

        // Botón para ir al menú de operaciones de matemáticas
        btnIrAlJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroduccionMateActivity.this, MenuOperacionesActivity.class);
                startActivity(intent);
            }
        });

        // Botón para volver al menú principal
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // O regresar a la actividad principal si es necesario
            }
        });
    }
}