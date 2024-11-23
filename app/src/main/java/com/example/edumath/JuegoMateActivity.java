package com.example.edumath;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class JuegoMateActivity extends AppCompatActivity implements SensorEventListener {

    private TextView problemaTexto, puntuacionTexto, tiempoTexto, vidasTexto, comboTexto;
    private ProgressBar barraTiempo;
    private Button[] botonesRespuesta;
    private GridLayout gridRespuestas;

    private int puntuacion = 0, ejerciciosResueltos = 0, vidasRestantes = 3, comboActual = 0;
    private boolean segundaDinamica = false;
    private int respuestaCorrecta;
    private CountDownTimer temporizador;

    private Random generadorAleatorio;
    private SensorManager sensorManager;
    private Sensor acelerometro;
    private static final float UMBRAL_ACELEROMETRO = 3.0f;
    private long ultimoMovimiento = 0;
    private static final long DELAY_ENTRE_MOVIMIENTOS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_mate);

        problemaTexto = findViewById(R.id.problema_texto);
        puntuacionTexto = findViewById(R.id.puntuacion_texto);
        tiempoTexto = findViewById(R.id.tiempo_texto);
        vidasTexto = findViewById(R.id.vidas_texto);
        comboTexto = findViewById(R.id.combo_texto);
        barraTiempo = findViewById(R.id.barra_tiempo);
        gridRespuestas = findViewById(R.id.grid_respuestas);

        botonesRespuesta = new Button[]{
                findViewById(R.id.boton_respuesta1),
                findViewById(R.id.boton_respuesta2),
                findViewById(R.id.boton_respuesta3),
                findViewById(R.id.boton_respuesta4)
        };

        generadorAleatorio = new Random();
        configurarBotones();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        generarNuevoProblema();
        iniciarTemporizador();
    }

    private void configurarBotones() {
        for (int i = 0; i < botonesRespuesta.length; i++) {
            int finalI = i;
            botonesRespuesta[i].setOnClickListener(v -> verificarRespuesta(finalI));
        }
    }

    private void generarNuevoProblema() {
        if (ejerciciosResueltos < 5) {
            segundaDinamica = false;
            mostrarBotones(4);
            generarProblemaMatematico();
        } else {
            segundaDinamica = true;
            mostrarBotones(2);
            generarProblemaComparativo();
        }
    }

    private void generarProblemaMatematico() {
        int num1 = generadorAleatorio.nextInt(20) + 1;
        int num2 = generadorAleatorio.nextInt(15) + 1;
        int operacion = generadorAleatorio.nextInt(4);

        int resultado;
        switch (operacion) {
            case 0: // Suma
                problemaTexto.setText(num1 + " + " + num2);
                resultado = num1 + num2;
                break;
            case 1: // Resta
                if (num2 > num1) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                problemaTexto.setText(num1 + " - " + num2);
                resultado = num1 - num2;
                break;
            case 2: // Multiplicación
                num1 = generadorAleatorio.nextInt(12) + 1;
                num2 = generadorAleatorio.nextInt(10) + 1;
                problemaTexto.setText(num1 + " × " + num2);
                resultado = num1 * num2;
                break;
            default: // División
                num2 = generadorAleatorio.nextInt(10) + 1;
                resultado = generadorAleatorio.nextInt(10) + 1;
                num1 = num2 * resultado;
                problemaTexto.setText(num1 + " ÷ " + num2);
                break;
        }

        int posicionCorrecta = generadorAleatorio.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == posicionCorrecta) {
                botonesRespuesta[i].setText(String.valueOf(resultado));
            } else {
                int incorrecto = resultado + (generadorAleatorio.nextInt(10) - 5);
                while (incorrecto == resultado || incorrecto < 0) {
                    incorrecto = resultado + (generadorAleatorio.nextInt(10) - 5);
                }
                botonesRespuesta[i].setText(String.valueOf(incorrecto));
            }
        }
        respuestaCorrecta = posicionCorrecta;
    }

    private void generarProblemaComparativo() {
        int num1 = generadorAleatorio.nextInt(20) + 1;
        int num2 = generadorAleatorio.nextInt(15) + 1;
        int operacion = generadorAleatorio.nextInt(4);

        int resultadoCorrecto;
        switch (operacion) {
            case 0:
                problemaTexto.setText(num1 + " + " + num2);
                resultadoCorrecto = num1 + num2;
                break;
            case 1:
                if (num2 > num1) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                problemaTexto.setText(num1 + " - " + num2);
                resultadoCorrecto = num1 - num2;
                break;
            case 2:
                num1 = generadorAleatorio.nextInt(12) + 1;
                num2 = generadorAleatorio.nextInt(10) + 1;
                problemaTexto.setText(num1 + " × " + num2);
                resultadoCorrecto = num1 * num2;
                break;
            default:
                num2 = generadorAleatorio.nextInt(10) + 1;
                resultadoCorrecto = generadorAleatorio.nextInt(10) + 1;
                num1 = num2 * resultadoCorrecto;
                problemaTexto.setText(num1 + " ÷ " + num2);
                break;
        }

        // Ahora siempre ponemos la respuesta correcta a la derecha
        respuestaCorrecta = 1;
        int incorrecto = resultadoCorrecto + (generadorAleatorio.nextInt(5) + 1);

        // La respuesta correcta siempre va a la derecha
        botonesRespuesta[0].setText(String.valueOf(incorrecto));
        botonesRespuesta[1].setText(String.valueOf(resultadoCorrecto));
    }

    private void mostrarBotones(int cantidad) {
        for (int i = 0; i < botonesRespuesta.length; i++) {
            botonesRespuesta[i].setVisibility(i < cantidad ? View.VISIBLE : View.GONE);
        }
    }

    private void verificarRespuesta(int respuestaSeleccionada) {
        cancelarTemporizador();

        if (respuestaSeleccionada == respuestaCorrecta) {
            actualizarPuntuacion();
        } else {
            decrementarVida();
        }

        if (vidasRestantes <= 0) {
            mostrarDialogoFinJuego();
            return;
        }

        generarNuevoProblema();
        iniciarTemporizador();
    }

    private void actualizarPuntuacion() {
        puntuacion += 10 + comboActual * 2;
        comboActual++;
        ejerciciosResueltos++;
        actualizarInterfaz();
    }

    private void decrementarVida() {
        vidasRestantes--;
        comboActual = 0;
        actualizarInterfaz();
    }

    private void actualizarInterfaz() {
        puntuacionTexto.setText("Puntuación: " + puntuacion);
        comboTexto.setText("Combo: x" + comboActual);
        vidasTexto.setText("Vidas: " + vidasRestantes);
    }

    private void iniciarTemporizador() {
        temporizador = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoTexto.setText(millisUntilFinished / 1000 + "s");
                barraTiempo.setProgress((int) (millisUntilFinished / 150));
            }

            @Override
            public void onFinish() {
                decrementarVida();
                if (vidasRestantes > 0) {
                    generarNuevoProblema();
                    iniciarTemporizador();
                } else {
                    mostrarDialogoFinJuego();
                }
            }
        };
        temporizador.start();
    }

    private void cancelarTemporizador() {
        if (temporizador != null) {
            temporizador.cancel();
        }
    }

    private void mostrarDialogoFinJuego() {
        new AlertDialog.Builder(this)
                .setTitle("¡Juego terminado!")
                .setMessage("Puntuación final: " + puntuacion)
                .setPositiveButton("Jugar de nuevo", (dialog, which) -> reiniciarJuego())
                .setNegativeButton("Salir al menú", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    private void reiniciarJuego() {
        puntuacion = 0;
        vidasRestantes = 3;
        comboActual = 0;
        ejerciciosResueltos = 0;
        segundaDinamica = false;
        actualizarInterfaz();
        generarNuevoProblema();
        iniciarTemporizador();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (segundaDinamica && event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long tiempoActual = System.currentTimeMillis();

            if (tiempoActual - ultimoMovimiento < DELAY_ENTRE_MOVIMIENTOS) {
                return;
            }

            float x = event.values[0];

            if (Math.abs(x) > UMBRAL_ACELEROMETRO) {
                ultimoMovimiento = tiempoActual;

                // x positivo = inclinación izquierda, x negativo = inclinación derecha
                if (x > UMBRAL_ACELEROMETRO) { // Inclinación izquierda
                    botonesRespuesta[0].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    new Handler().postDelayed(() -> {
                        botonesRespuesta[0].setBackgroundColor(getResources().getColor(android.R.color.background_light));
                        verificarRespuesta(0);
                    }, 200);
                } else if (x < -UMBRAL_ACELEROMETRO) { // Inclinación derecha
                    botonesRespuesta[1].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    new Handler().postDelayed(() -> {
                        botonesRespuesta[1].setBackgroundColor(getResources().getColor(android.R.color.background_light));
                        verificarRespuesta(1);
                    }, 200);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onResume() {
        super.onResume();
        if (acelerometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        cancelarTemporizador();
    }
}