package com.example.edumath;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Etapa2Activity extends AppCompatActivity {
    private SensorManager sensorManager;
    private ImageView figure;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etapa2);

        figure = findViewById(R.id.imageFigure);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                figure.setX(figure.getX() + x * 10);
                figure.setY(figure.getY() + y * 10);

                // Lógica de puntuación
                if (figure.getX() > 100 && figure.getY() > 100) {
                    score += 10;
                    saveScore(score);
                    finish();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        sensorManager.registerListener(listener, gyroscope, SensorManager.SENSOR_DELAY_UI);
    }

    private void saveScore(int score) {
        SharedPreferences preferences = getSharedPreferences("Puntuaciones", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("score", score);
        editor.apply();
    }
}