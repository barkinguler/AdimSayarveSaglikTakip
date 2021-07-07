package com.example.yurumesayar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yurumesayar.Models.Bilgiler;
import com.example.yurumesayar.RestApi.ManagerAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Toplam extends AppCompatActivity {
    private TextView göster;
    private Button deneme;
    private Button detay;
    private TextView toplam;
    private TextView toplamkal;
    private TextView ort;
    private TextView ortkal;
    private TextView max;
    private TextView maxkal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toplam);
        toplam = (TextView) findViewById(R.id.toplam);
        toplamkal = (TextView) findViewById(R.id.toplamkalori);
        ort = (TextView) findViewById(R.id.ort);
        ortkal = (TextView) findViewById(R.id.ortkalori);
        max = (TextView) findViewById(R.id.maxadim);
        maxkal = (TextView) findViewById(R.id.maxkalori);
        deneme = (Button) findViewById(R.id.gec);
        detay = (Button) findViewById(R.id.detay);
        detay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), grafikadim.class);

                startActivity(i);
            }
        });
        göster = (TextView) findViewById(R.id.toplam);
        toplam();

        deneme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getBaseContext(), adimdetay.class);

                startActivity(b);
            }
        });

    }

    String isim;

    public void toplam() {


        isim = MainActivity.isim;
        Call<List<Bilgiler>> bilgiList = ManagerAll.getInstance().getirAdim();
        bilgiList.enqueue(new Callback<List<Bilgiler>>() {
            @Override
            public void onResponse(Call<List<Bilgiler>> call, Response<List<Bilgiler>> response) {


                Log.d("xxx", Integer.toString(response.body().size()));
                for (int i = 0; i < response.body().size(); i++) {
                    if (isim.equals(response.body().get(i).getKULLANICI())) {


                        toplam.setText("Toplam adım sayısı :\n" + response.body().get(i).getTOPLAM());
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);


                        toplamkal.setText("Yakılan toplam kalori :\n" + df.format((float) (response.body().get(i).getTOPLAM() * 0.05)));

                        ort.setText("Ortalama Adım :\n" + (int) (response.body().get(i).getORT()));
                        ortkal.setText("Ortalama yakılan kalori :\n" + df.format((float) (response.body().get(i).getORT() * 0.05)));
                        max.setText("En Yüksek atılan Adım :\n" + (response.body().get(i).getMAX()));
                        maxkal.setText("En Yüksek yakılan kalori :\n" + df.format((float) (response.body().get(i).getMAX() * 0.05)));
                    }


                }


            }

            @Override
            public void onFailure(Call<List<Bilgiler>> call, Throwable t) {
                Log.d("xxx", t.toString());
            }
        });


    }

}
