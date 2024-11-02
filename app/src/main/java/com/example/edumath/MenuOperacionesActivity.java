package com.example.edumath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edumath.LeccionActivity;
import com.example.edumath.R;

public class MenuOperacionesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_operaciones);

        Button btnSuma = findViewById(R.id.btnSuma);
        Button btnResta = findViewById(R.id.btnResta);
        Button btnMultiplicacion = findViewById(R.id.btnMultiplicacion);
        Button btnDivision = findViewById(R.id.btnDivision);
        Button btnComparacion = findViewById(R.id.btnComparacion);

        btnSuma.setOnClickListener(v -> openLeccion("suma"));
        btnResta.setOnClickListener(v -> openLeccion("resta"));
        btnMultiplicacion.setOnClickListener(v -> openLeccion("multiplicacion"));
        btnDivision.setOnClickListener(v -> openLeccion("division"));
        btnComparacion.setOnClickListener(v -> openLeccion("comparacion"));
    }

    private void openLeccion(String operacion) {
        Intent intent = new Intent(MenuOperacionesActivity.this, LeccionActivity.class);
        intent.putExtra("operacion", operacion);
        startActivity(intent);
    }
}
