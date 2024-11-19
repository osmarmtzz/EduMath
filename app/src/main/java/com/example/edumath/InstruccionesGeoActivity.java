package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InstruccionesGeoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intrucciones_geo);

        Button btnAtras = findViewById(R.id.buttonMenuMath);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesGeoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button buttonContinueGeo = findViewById(R.id.buttonContinueGeo);

        buttonContinueGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesGeoActivity.this, Juego_Geo.class);
                startActivity(intent);
                finish();
            }
        });
        Button buttonLessons = findViewById(R.id.buttonLessons);

        buttonLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesGeoActivity.this, GeometriaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
