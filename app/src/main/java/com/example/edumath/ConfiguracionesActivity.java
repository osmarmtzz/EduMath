package com.example.edumath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfiguracionesActivity extends AppCompatActivity {

    private SeekBar sbMusica, sbSonidos;
    private Switch swModoNocturno, swOrientacion;
    private Spinner spTam, spIdioma;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuraciones);

        // Initialize the SharedPreferences
        sharedPreferences = getSharedPreferences("ConfigPreferences", MODE_PRIVATE);

        // Find views by ID
        sbMusica = findViewById(R.id.SBMusica);
        sbSonidos = findViewById(R.id.SBSonidos);
        swModoNocturno = findViewById(R.id.SWModo);
        swOrientacion = findViewById(R.id.SWOrientacion);
        spTam = findViewById(R.id.SPTam);
        spIdioma = findViewById(R.id.spinner_idioma);
        Button btnAtras = findViewById(R.id.BtnAtras);

        // Set listeners for controls
        setVolumeControl(sbMusica, AudioManager.STREAM_MUSIC);
        setVolumeControl(sbSonidos, AudioManager.STREAM_NOTIFICATION);
        configureSwitches();
        configureSpinners();

        // Button to return to the main menu
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save preferences when returning to the menu
                savePreferences();
                Intent intent = new Intent(ConfiguracionesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Load saved preferences
        loadPreferences();
    }

    private void setVolumeControl(SeekBar seekBar, int streamType) {
        final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        seekBar.setMax(audioManager.getStreamMaxVolume(streamType));
        seekBar.setProgress(audioManager.getStreamVolume(streamType));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(streamType, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void configureSwitches() {
        swModoNocturno.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setTheme(R.style.Theme_EduMath); // Apply your dark theme
            } else {
                setTheme(R.style.Theme_EduMath); // Apply your light theme
            }

        });

        swOrientacion.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Allow screen rotation
                setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            } else {
                // Lock screen orientation to portrait
                setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

    private void configureSpinners() {
        // Set up spinner logic here for font size and language
        spTam.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                // Adjust font size based on selection
                String selectedSize = (String) parent.getItemAtPosition(position);
                adjustFontSize(selectedSize);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        spIdioma.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                // Change language based on selection
                String selectedLanguage = (String) parent.getItemAtPosition(position);
                changeLanguage(selectedLanguage);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });
    }

    private void adjustFontSize(String size) {
        // Adjust the application font size based on user selection
        // For demonstration purposes, this can be an implementation detail
    }

    private void changeLanguage(String language) {
        // Change the app language, implementation depends on localization setup
        // For demonstration purposes, this can be a stub method
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ModoNocturno", swModoNocturno.isChecked());
        editor.putBoolean("Orientacion", swOrientacion.isChecked());
        editor.putInt("VolumenMusica", sbMusica.getProgress());
        editor.putInt("VolumenSonidos", sbSonidos.getProgress());
        editor.putInt("TamLetra", spTam.getSelectedItemPosition());
        editor.putInt("Idioma", spIdioma.getSelectedItemPosition());
        editor.apply();
    }

    private void loadPreferences() {
        swModoNocturno.setChecked(sharedPreferences.getBoolean("ModoNocturno", false));
        swOrientacion.setChecked(sharedPreferences.getBoolean("Orientacion", false));
        sbMusica.setProgress(sharedPreferences.getInt("VolumenMusica", 50));
        sbSonidos.setProgress(sharedPreferences.getInt("VolumenSonidos", 50));
        spTam.setSelection(sharedPreferences.getInt("TamLetra", 0));
        spIdioma.setSelection(sharedPreferences.getInt("Idioma", 0));
    }
}
