package com.example.edumath;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class JuegoMateActivity extends AppCompatActivity implements SensorEventListener {
    private TextView problemTexto, puntuacionTexto, tiempoTexto, nivelTexto, comboTexto, vidasTexto;
    private Button[] botonesRespuesta;
    private ProgressBar barraProgreso;

    private int puntuacion = 0;
    private int respuestaCorrecta;
    private int nivelActual = 1;
    private int comboActual = 0;
    private int vidasRestantes = 3;
    private int ejerciciosResueltos = 0;
    private boolean segundaDinamica = false;

    private Random generadorAleatorio;
    private CountDownTimer timer;

    private SensorManager sensorManager;
    private Sensor acelerometro;

    private static final long TIEMPO_POR_PREGUNTA = 15000; // 15 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_mate);

        inicializarComponentes();
        iniciarJuego();
    }

    private void inicializarComponentes() {
        problemTexto = findViewById(R.id.problema_texto);
        puntuacionTexto = findViewById(R.id.puntuacion_texto);
        tiempoTexto = findViewById(R.id.tiempo_texto);
        nivelTexto = findViewById(R.id.nivel_texto);
        comboTexto = findViewById(R.id.combo_texto);
        vidasTexto = findViewById(R.id.vidas_texto);
        barraProgreso = findViewById(R.id.barra_tiempo);

        botonesRespuesta = new Button[4];
        botonesRespuesta[0] = findViewById(R.id.boton_respuesta1);
        botonesRespuesta[1] = findViewById(R.id.boton_respuesta2);
        botonesRespuesta[2] = findViewById(R.id.boton_respuesta3);
        botonesRespuesta[3] = findViewById(R.id.boton_respuesta4);

        generadorAleatorio = new Random();

        for (Button boton : botonesRespuesta) {
            boton.setOnClickListener(v -> verificarRespuesta(Integer.parseInt(boton.getText().toString())));
        }

        // Configuración del sensor de acelerómetro
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    private void iniciarJuego() {
        actualizarInterfaz();
        generarNuevoProblema();
        iniciarTemporizador();
    }

    private void generarNuevoProblema() {
        if (segundaDinamica) {
            generarProblemaComparativo();
        } else {
            generarProblemaMatematico();
        }
    }

    private void generarProblemaMatematico() {
        int[] operaciones = {1, 2, 3, 4}; // 1:suma, 2:resta, 3:multiplicación, 4:división
        int tipoOperacion = operaciones[generadorAleatorio.nextInt(operaciones.length)];

        int rangoNumeros = 10 + (nivelActual * 5);
        int num1 = generadorAleatorio.nextInt(rangoNumeros) + 1;
        int num2 = generadorAleatorio.nextInt(rangoNumeros) + 1;

        switch (tipoOperacion) {
            case 1:
                problemTexto.setText(num1 + " + " + num2 + " = ?");
                respuestaCorrecta = num1 + num2;
                break;
            case 2:
                if (num1 < num2) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                problemTexto.setText(num1 + " - " + num2 + " = ?");
                respuestaCorrecta = num1 - num2;
                break;
            case 3:
                problemTexto.setText(num1 + " × " + num2 + " = ?");
                respuestaCorrecta = num1 * num2;
                break;
            case 4:
                num1 = num2 * (generadorAleatorio.nextInt(rangoNumeros / 2) + 1);
                problemTexto.setText(num1 + " ÷ " + num2 + " = ?");
                respuestaCorrecta = num1 / num2;
                break;
        }
        generarOpciones();
    }

    private void generarProblemaComparativo() {
        int num1 = generadorAleatorio.nextInt(100);
        int num2 = generadorAleatorio.nextInt(100);

        respuestaCorrecta = num1 > num2 ? 1 : -1;

        problemTexto.setText(num1 + " vs " + num2 + " (Izq: menor, Der: mayor)");
    }

    private void generarOpciones() {
        int[] opciones = new int[4];
        opciones[generadorAleatorio.nextInt(4)] = respuestaCorrecta;

        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i] == 0) {
                opciones[i] = respuestaCorrecta + (generadorAleatorio.nextInt(5) - 2);
            }
            botonesRespuesta[i].setText(String.valueOf(opciones[i]));
        }
    }

    private void verificarRespuesta(int respuestaSeleccionada) {
        cancelarTemporizador();

        if (respuestaSeleccionada == respuestaCorrecta) {
            puntuacion += 10 + comboActual * 2;
            comboActual++;
            ejerciciosResueltos++;

            if (ejerciciosResueltos % 5 == 0) {
                segundaDinamica = !segundaDinamica;
            }
        } else {
            vidasRestantes--;
            comboActual = 0;

            if (vidasRestantes <= 0) {
                mostrarDialogoFinJuego();
                return;
            }
        }

        actualizarInterfaz();
        generarNuevoProblema();
        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
        barraProgreso.setMax(100);
        barraProgreso.setProgress(100);

        timer = new CountDownTimer(TIEMPO_POR_PREGUNTA, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progreso = (int) (millisUntilFinished * 100 / TIEMPO_POR_PREGUNTA);
                barraProgreso.setProgress(progreso);
                tiempoTexto.setText(String.format("%.1f", millisUntilFinished / 1000.0) + "s");
            }

            @Override
            public void onFinish() {
                vidasRestantes--;
                if (vidasRestantes <= 0) {
                    mostrarDialogoFinJuego();
                } else {
                    generarNuevoProblema();
                    iniciarTemporizador();
                }
            }
        }.start();
    }

    private void cancelarTemporizador() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void mostrarDialogoFinJuego() {
        cancelarTemporizador();
        new AlertDialog.Builder(this)
                .setTitle("Fin del Juego")
                .setMessage("Puntuación final: " + puntuacion + "\nNivel alcanzado: " + nivelActual)
                .setPositiveButton("Reiniciar", (dialog, which) -> reiniciarJuego())
                .setNegativeButton("Salir", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    private void reiniciarJuego() {
        puntuacion = 0;
        vidasRestantes = 3;
        nivelActual = 1;
        comboActual = 0;
        ejerciciosResueltos = 0;
        segundaDinamica = false;
        iniciarJuego();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (segundaDinamica && event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            if (x > 5) { // Derecha
                verificarRespuesta(1);
            } else if (x < -5) { // Izquierda
                verificarRespuesta(-1);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (segundaDinamica && acelerometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        cancelarTemporizador();
    }

    private void actualizarInterfaz() {
        puntuacionTexto.setText("Puntuación: " + puntuacion);
        nivelTexto.setText("Nivel: " + nivelActual);
        comboTexto.setText("Combo: x" + comboActual);
        vidasTexto.setText("Vidas: " + vidasRestantes);
    }
}
