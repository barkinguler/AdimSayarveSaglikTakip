package com.example.yurumesayar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurumesayar.Models.Bilgiler;
import com.example.yurumesayar.Post.APIService;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.util.concurrent.TimeUnit.*;

public class pedometer extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private Button BtnStart;
    private TextView TvSteps;
    private Button BtnStop;
    private Button kaydet;
    private Button toplamadım;
    private Button oturum;
    private Button endex;
    private TextView kaloriresim;
    private TextView kalori;
    static Switch simpleSwitch;
    long firebaseadım = 0;
    String document = MainActivity.document;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);
        TvSteps = (TextView) findViewById(R.id.tv_steps);
        BtnStart = (Button) findViewById(R.id.btn_start);
        BtnStop = (Button) findViewById(R.id.btn_stop);
        endex = (Button) findViewById(R.id.endeks);
        kalori = (TextView) findViewById(R.id.kalori);
        kaloriresim = (TextView) findViewById(R.id.kaloriresim);
        toplamadım = (Button) findViewById(R.id.toplamadımsayısı);

        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (simpleSwitch.isChecked()) {
                    MyService.kontrol = true;
                    Intent myService = new Intent(pedometer.this, MyService.class);

                    startService(myService);

                } else {

                    Intent myService = new Intent(pedometer.this, MyService.class);
                    MyService.kontrol = false;


                }
            }
        });

        endex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endeks();
            }
        });
        oturum = (Button) findViewById(R.id.oturum);
        oturum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oturum();
            }
        });

        toplamadım.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toplamAdımSayfası();
            }
        });


        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(pedometer.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MyService.kontrol = false;

                sensorManager.unregisterListener(pedometer.this);

            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        System.out.println(df.format((float) numSteps * 0.05));

        numSteps++;

        TvSteps.setText(numSteps + "");
        kaloriresim.setVisibility(TextView.VISIBLE);

        kalori.setText(("Harcanan Cal :" + df.format((float) numSteps * 0.05)));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.99.1:8081/RestAp_/webresources/testUpdate/")
                .build();
        APIService api = retrofit.create(APIService.class);
        Bilgiler a = new Bilgiler();

        a.setKULLANICI(MainActivity.isim);

        String json = a.toStringUpdate();
        Log.d("hata2", json);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        api.createPost1(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("hata1", t.toString());
            }
        });


    }


    public void toplamAdımSayfası() {


        Intent i = new Intent(getBaseContext(), Toplam.class);

        startActivity(i);
    }

    public void oturum() {
        MainActivity.isim = "";
        MainActivity.sifr = "";
        Intent i = new Intent(getBaseContext(), MainActivity.class);

        startActivity(i);
        finish();
    }

    public void endeks() {
        Intent i = new Intent(getBaseContext(), Endex.class);

        startActivity(i);


    }


}
