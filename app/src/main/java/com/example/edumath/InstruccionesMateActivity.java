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

        Button btnAtras = findViewById(R.id.buttonMenuMath);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesMateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button buttonContinueMath = findViewById(R.id.buttonContinueMath);
        buttonContinueMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstruccionesMateActivity.this, IntroduccionMateActivity.class);
                startActivity(intent);
            }
        });
    }
}
