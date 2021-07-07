package com.example.yurumesayar;


import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.yurumesayar.Models.Bilgiler;
import com.example.yurumesayar.Post.APIService;
import com.example.yurumesayar.Post.Post;
import com.example.yurumesayar.RestApi.ManagerAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Button giris;
    private Button kayıtol;
    private EditText ad;
    private EditText sifre;
    static public String document = "";
    private static final int NOTIF_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        giris = (Button) findViewById(R.id.button);
        kayıtol = (Button) findViewById(R.id.button2);
        ad = (EditText) findViewById(R.id.editText2);
        sifre = (EditText) findViewById(R.id.editText3);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                giris(v);
            }
        });
        kayıtol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createPost(v);
            }
        });


    }


    static String sifr;
    static String isim;


    public void giris(View v) {
        sifr = sifre.getText().toString();
        isim = ad.getText().toString();

        Call<List<Bilgiler>> bilgiList = ManagerAll.getInstance().getirBilgileri();
        bilgiList.enqueue(new Callback<List<Bilgiler>>() {
            @Override
            public void onResponse(Call<List<Bilgiler>> call, Response<List<Bilgiler>> response) {


                for (int i = 0; i < response.body().size(); i++) {

                    if (isim.equals(response.body().get(i).getKULLANICI()) && sifr.equals(response.body().get(i).getSIFRE())) {

                        Intent b = new Intent(getBaseContext(), pedometer.class);

                        startActivity(b);
                        finish();
                        return;
                    }


                }
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Hatalı Giriş");
                alertDialog.setMessage("Lütfen uygun giriş yapınız");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();


            }

            @Override
            public void onFailure(Call<List<Bilgiler>> call, Throwable t) {
                Log.d("xxx", t.toString());
            }
        });


    }

    private void createPost(View v) {

        if (ad.getText().toString().equals("") || sifre.getText().toString().equals("")) {

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Hatalı Kayıt");
            alertDialog.setMessage("Lütfen uygun şekilde kayıt olunuz");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return;
        }


        Call<List<Bilgiler>> bilgiList = ManagerAll.getInstance().getirHata();
        bilgiList.enqueue(new Callback<List<Bilgiler>>() {
            @Override
            public void onResponse(Call<List<Bilgiler>> call, Response<List<Bilgiler>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    if (response.body().get(i).getKULLANICI().equals(ad.getText().toString())) {

                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Hatalı kayıt");
                        alertDialog.setMessage("");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        return;

                    }


                }

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Başarılı Kayıt");
                alertDialog.setMessage("Artık giriş yapabilirsiniz");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<List<Bilgiler>> call, Throwable t) {
                Log.d("xxx", t.toString());
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.99.1:8081/RestAp_/webresources/testPost/")
                .build();
        APIService api = retrofit.create(APIService.class);
        Bilgiler a = new Bilgiler();

        a.setKULLANICI(ad.getText().toString());
        a.setSIFRE(sifre.getText().toString());

        String json = a.toString1();
        Log.d("hata2", json);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        api.createPost(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                Log.e("1111", String.valueOf(response.isSuccessful()));


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });


    }


}
