package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor lightSensor, proximitySensor, gravitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        findViewById(R.id.btnStartLight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sensorManager!=null) {
                    lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

                    if (lightSensor != null) {
                        sensorManager.registerListener(MainActivity.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
                    } else {
                        Toast.makeText(MainActivity.this, "Device doesnt contain Light Sensor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        findViewById(R.id.btnStartProximity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sensorManager!=null) {
                    proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

                    if (proximitySensor != null) {
                        sensorManager.registerListener(MainActivity.this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
                    } else {
                        Toast.makeText(MainActivity.this, "Device doesnt contain Light Sensor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        findViewById(R.id.btnStartGravity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sensorManager!=null) {
                    gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

                    if (gravitySensor != null) {
                        sensorManager.registerListener(MainActivity.this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
                    } else {
                        Toast.makeText(MainActivity.this, "Device doesnt contain Light Sensor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType()==Sensor.TYPE_LIGHT){
            ((TextView) findViewById(R.id.txtLight)).setText("Values : "+event.values[0]);
        } else if (event.sensor.getType()==Sensor.TYPE_PROXIMITY) {
            ((TextView) findViewById(R.id.txtProximity)).setText("Values : "+event.values[0]);

            if (event.values[0]>0){
                Toast.makeText(this, "Object is Far", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Object is Close", Toast.LENGTH_SHORT).show();
            }
        } else if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            ((TextView) findViewById(R.id.txtGravity)).setText(
                    "x: "+event.values[0]+"\n"+
                    "y: "+event.values[1]+"\n"+
                    "z: "+event.values[2]
            );
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}