package com.example.edumath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

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

        // Configura los botones del menú
        Button btnCreditos = findViewById(R.id.btn_creditos);
        btnCreditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreditosActivity.class);
                startActivity(intent);
            }
        });

        Button btnConfiguraciones = findViewById(R.id.btn_configuraciones);
        btnConfiguraciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfiguracionesActivity.class);
                startActivity(intent);
            }
        });



        // Puedes configurar más botones aquí (btn_matematicas, btn_geometria, etc.)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Libera recursos del MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausa la música si la actividad se detiene
        if (mediaPlayer != null && mediaPlayer.isPlaying() && !isMuted) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reanuda la música si la actividad se reanuda
        if (mediaPlayer != null && !isMuted) {
            mediaPlayer.start();
        }
    }
}
