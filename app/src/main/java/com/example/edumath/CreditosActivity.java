package com.example.edumath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreditosActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private boolean isMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditos);

        // Inicializa el MediaPlayer con el archivo de audio
        mediaPlayer = MediaPlayer.create(this, R.raw.musica_menu);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Configura el botón para silenciar
        Button btnSilencio = findViewById(R.id.btn_silencio);
        btnSilencio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMuted) {
                    mediaPlayer.start(); // Reproduce la música
                    btnSilencio.setText("🔊"); // Cambia el texto del botón
                } else {
                    mediaPlayer.pause(); // Pausa la música
                    btnSilencio.setText("🔇"); // Cambia el texto del botón
                }
                isMuted = !isMuted; // Alterna el estado de silencio
            }
        });

        Button btnAtras = findViewById(R.id.BtnAtras);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
