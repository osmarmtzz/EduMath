package com.example.edumath;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LeccionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String operacion = getIntent().getStringExtra("operacion");

        if ("suma".equals(operacion)) {
            setContentView(R.layout.suma);
        } else if ("resta".equals(operacion)) {
            setContentView(R.layout.resta);
        } else if ("multiplicacion".equals(operacion)) {
            setContentView(R.layout.multiplicacion);
        } else if ("division".equals(operacion)) {
            setContentView(R.layout.division);
        } else if ("comparacion".equals(operacion)) {
            setContentView(R.layout.comparacion);
        }
    }
}
