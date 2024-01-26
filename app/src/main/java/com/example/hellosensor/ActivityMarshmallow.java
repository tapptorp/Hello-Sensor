/*package com.example.hellosensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.os.Vibrator;

public class ActivityMarshmallow extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marshmallow);


        Button buttonM1 = findViewById(R.id.buttonM1);
        buttonM1.setOnClickListener(this);
        Button buttonM2 = findViewById(R.id.buttonM2);
        buttonM2.setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonM1:
                startActivity(new Intent(ActivityMarshmallow.this, MainActivity.class));
                break;
            case R.id.buttonM2:
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }

        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

 */
package com.example.hellosensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMarshmallow extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private CountDownTimer timer;
    private boolean timerRunning = false;

    MediaPlayer mediaPlayer;

    //MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marshmallow);

        Button buttonM1 = findViewById(R.id.buttonM1);
        buttonM1.setOnClickListener(this);

        Button buttonM2 = findViewById(R.id.buttonM2);
        buttonM2.setOnClickListener(this);

        // Initialize the accelerometer
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener when the activity is resumed
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener when the activity is paused
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float xValue = event.values[0];

        // Check if the X-axis value is between -11 and -9
        if (xValue >= -11 && xValue <= -9) {
            if (!timerRunning) {
                // Start the timer if it's not already running
                startTimer();
            }
        } else {
            if (timerRunning) {
                // Stop the timer if it's running
                stopTimer();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this example
    }

    private void startTimer() {
        timer = new CountDownTimer(7000, 1000) {
            // Timer interval is set to 1000 milliseconds (1 second)
            public void onTick(long millisUntilFinished) {
                System.out.println("OnTick");
                // Code to execute on each tick of the timer
                // You can perform actions like updating UI or triggering events here
            }

            public void onFinish() {
                // Code to execute when the timer finishes
                System.out.println("Klar");
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(500);
                }
            }
        };

        timerRunning = true;
        timer.start();
        Toast.makeText(this, "Timer started", Toast.LENGTH_SHORT).show();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timerRunning = false;
        Toast.makeText(this, "Timer stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonM1:
                startActivity(new Intent(ActivityMarshmallow.this, MainActivity.class));
                break;

            case R.id.buttonM2:
                System.out.println("Tryckte");
                mediaPlayer = MediaPlayer.create(this, R.raw.foodsizzlingsoundeffect);
                mediaPlayer.start();
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
