/*
package com.example.hellosensor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    private int counter = 0;

    MediaPlayer mediaPlayer;
    private boolean xPositiveIntervalDone = false;
    private boolean xNegativeIntervalDone = false;

    private boolean zPositiveIntervalDone = false;
    private boolean zNegativeIntervalDone = false;


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

        View rootView = getWindow().getDecorView().getRootView();

        rootView.setBackgroundColor(Color.MAGENTA);
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
        float yValue = event.values[1];
        float zValue = event.values[2];

        // Check if the X-axis value is between -11 and -9
        if (xValue >= -13 && xValue <= -7) {
            if (!timerRunning) {
                // Start the timer if it's not already running
                startTimerNegativeX();
            }

        }
        else if (xValue >= 7 && xValue <= 13) {
            if (!timerRunning) {
                // Start the timer if it's not already running
                startTimerNegativeX();
            }

        }
        else if (zValue >= 7 && zValue <= 13) {
            if (!timerRunning) {
                // Start the timer if it's not already running
                startTimerNegativeX();
            }

        }

        else if (zValue >= -13 && zValue <= -7) {
            if (!timerRunning) {
                // Start the timer if it's not already running
                startTimerNegativeX();
            }

        }

        else {
            if (timerRunning) {
                // Stop the timer if it's running
                stopTimer();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void startTimerNegativeX() {
        timer = new CountDownTimer(7000, 4000) {

            public void onTick(long millisUntilFinished) {
                if (counter == 0) {
                    System.out.println("0");
                    counter ++;
                }
                else {
                    System.out.println("inte första");
                    mediaPlayer = MediaPlayer.create(ActivityMarshmallow.this, R.raw.foodsizzlingsoundeffect);
                    mediaPlayer.start();
                }





                // Code to execute on each tick of the timer
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
*/


package com.example.hellosensor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityMarshmallow extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private CountDownTimer timer;
    private boolean timerRunning = false;

    // Flags to keep track of interval status
    private boolean positiveXIntervalDone = false;
    private boolean negativeXIntervalDone = false;
    private boolean positiveZIntervalDone = false;
    private boolean negativeZIntervalDone = false;

    private int counter = 0;
    private MediaPlayer mediaPlayer;

    private ImageView imageView;

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

        View rootView = getWindow().getDecorView().getRootView();
        rootView.setBackgroundColor(Color.MAGENTA);

        imageView = findViewById(R.id.imageView2);
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
        float zValue = event.values[2];

        // Check if the positive X-axis interval is not yet done
        if (!positiveXIntervalDone && xValue >= 7 && xValue <= 13) {
            if (!timerRunning) {
                startTimerPositiveX();
                if (negativeXIntervalDone) {
                    imageView.setImageResource(R.drawable.donemarshmallow);
                }
                else {
                    imageView.setImageResource(R.drawable.marshmallow);
                }
            }
        } else {
            // Positive X-axis interval is marked done only in onFinish of the timer
        }

        // Check if the negative X-axis interval is not yet done
        if (!negativeXIntervalDone && xValue >= -13 && xValue <= -7) {
            if (!timerRunning) {
                startTimerNegativeX();
                if (positiveXIntervalDone) {
                    imageView.setImageResource(R.drawable.donemarshmallow);
                }
                else {
                    imageView.setImageResource(R.drawable.marshmallow);
                }
            }
        } else {
            // Negative X-axis interval is marked done only in onFinish of the timer
        }

        // Check if the positive Z-axis interval is not yet done
        if (!positiveZIntervalDone && zValue >= 7 && zValue <= 13) {
            if (!timerRunning) {
                startTimerPositiveZ();
                if (negativeZIntervalDone) {
                    imageView.setImageResource(R.drawable.donemarshmallow);
                }
                else {
                    imageView.setImageResource(R.drawable.marshmallow);
                }
            }
        } else {
            // Positive Z-axis interval is marked done only in onFinish of the timer
        }

        // Check if the negative Z-axis interval is not yet done
        if (!negativeZIntervalDone && zValue >= -13 && zValue <= -7) {
            if (!timerRunning) {
                startTimerNegativeZ();
                if (positiveZIntervalDone) {
                    imageView.setImageResource(R.drawable.donemarshmallow);
                }
                else {
                    imageView.setImageResource(R.drawable.marshmallow);
                }
            }
        } else {
            // Negative Z-axis interval is marked done only in onFinish of the timer
        }

        if (positiveXIntervalDone && negativeXIntervalDone && positiveZIntervalDone && negativeZIntervalDone) {

        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }

    private void startTimerPositiveX() {
        timer = new CountDownTimer(7000, 4000) {
            public void onTick(long millisUntilFinished) {
                if (counter == 0) {
                    System.out.println("0");
                    counter ++;
                }
                else {
                    System.out.println("inte första");
                    mediaPlayer = MediaPlayer.create(ActivityMarshmallow.this, R.raw.foodsizzlingsoundeffect);
                    mediaPlayer.start();
                }
            }

            public void onFinish() {
                // Code to execute when the timer finishes
                handleIntervalCompletion("Positive X");
            }
        };

        timerRunning = true;
        timer.start();
        Toast.makeText(this, "Positive X Timer started", Toast.LENGTH_SHORT).show();
    }

    private void startTimerNegativeX() {
        timer = new CountDownTimer(7000, 4000) {
            public void onTick(long millisUntilFinished) {
                if (counter == 0) {
                    System.out.println("0");
                    counter ++;
                }
                else {
                    System.out.println("inte första");
                    mediaPlayer = MediaPlayer.create(ActivityMarshmallow.this, R.raw.foodsizzlingsoundeffect);
                    mediaPlayer.start();
                }
            }

            public void onFinish() {
                // Code to execute when the timer finishes
                handleIntervalCompletion("Negative X");
            }
        };

        timerRunning = true;
        timer.start();
        Toast.makeText(this, "Negative X Timer started", Toast.LENGTH_SHORT).show();
    }

    private void startTimerPositiveZ() {
        timer = new CountDownTimer(7000, 4000) {
            public void onTick(long millisUntilFinished) {
                if (counter == 0) {
                    System.out.println("0");
                    counter ++;
                }
                else {
                    System.out.println("inte första");
                    mediaPlayer = MediaPlayer.create(ActivityMarshmallow.this, R.raw.foodsizzlingsoundeffect);
                    mediaPlayer.start();
                }
            }

            public void onFinish() {
                // Code to execute when the timer finishes
                handleIntervalCompletion("Positive Z");
            }
        };

        timerRunning = true;
        timer.start();
        Toast.makeText(this, "Positive Z Timer started", Toast.LENGTH_SHORT).show();
    }

    private void startTimerNegativeZ() {
        timer = new CountDownTimer(7000, 4000) {
            public void onTick(long millisUntilFinished) {
                if (counter == 0) {
                    System.out.println("0");
                    counter ++;
                }
                else {
                    System.out.println("inte första");
                    mediaPlayer = MediaPlayer.create(ActivityMarshmallow.this, R.raw.foodsizzlingsoundeffect);
                    mediaPlayer.start();
                }
            }

            public void onFinish() {
                // Code to execute when the timer finishes
                handleIntervalCompletion("Negative Z");
            }
        };

        timerRunning = true;
        timer.start();
        Toast.makeText(this, "Negative Z Timer started", Toast.LENGTH_SHORT).show();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timerRunning = false;
        Toast.makeText(this, "Timer stopped", Toast.LENGTH_SHORT).show();
    }

    private void handleIntervalCompletion(String intervalName) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(500);
        }

        // Mark the corresponding interval as done
        switch (intervalName) {
            case "Positive X":
                positiveXIntervalDone = true;
                break;
            case "Negative X":
                negativeXIntervalDone = true;
                break;
            case "Positive Z":
                positiveZIntervalDone = true;
                break;
            case "Negative Z":
                negativeZIntervalDone = true;
                break;
        }

        stopTimer(); // Stop the timer after finishing
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonM1:
                startActivity(new Intent(ActivityMarshmallow.this, MainActivity.class));
                break;

            case R.id.buttonM2:
                System.out.println("Tryckte");
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    // Deprecated in API 26
                    v.vibrate(500);
                }
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        // Handle pointer capture changes if needed
    }
}
