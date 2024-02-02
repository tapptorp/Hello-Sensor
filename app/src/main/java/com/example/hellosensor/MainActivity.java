package com.example.hellosensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean accelerometerActive = false;

    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        textView = findViewById(R.id.text_accelerometer);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);


        View rootView = getWindow().getDecorView().getRootView();

        rootView.setBackgroundColor(Color.MAGENTA);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textView.setText(sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                toggleAccelerometer();
                break;

            case R.id.button2:
                //Toast.makeText(this, "button 2 clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ActivityMarshmallow.class));


        }
    }

    private void toggleAccelerometer() {
        if (accelerometerActive) {
            // Code to turn off the accelerometer
            sensorManager.unregisterListener(this, accelerometer);
            Toast.makeText(this, "Accelerometer turned off", Toast.LENGTH_SHORT).show();
        } else {
            // Code to turn on the accelerometer
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Accelerometer turned on", Toast.LENGTH_SHORT).show();
        }

        accelerometerActive = !accelerometerActive; // Toggle the accelerometer state
    }
}