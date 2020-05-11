package com.example.yurumesayar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yurumesayar.Models.Bilgiler;
import com.example.yurumesayar.Post.APIService;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Endex extends AppCompatActivity {
    private Button hesapla;
    private EditText boy;
    private EditText kilo;
    private TextView goster;
    private Button grafik;
    private Button tablo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endex);
        hesapla=(Button)findViewById(R.id.hesapla);
        boy=(EditText)findViewById(R.id.boy);
        kilo=(EditText)findViewById(R.id.kilo);
        tablo=(Button)findViewById(R.id.tablo);
        grafik=(Button)findViewById(R.id.grafik);

        tablo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), kilodetay.class);

                startActivity(i);
            }
        });

        grafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Kilografik.class);

                startActivity(i);
            }
        });

        //goster=(TextView)findViewById(R.id.goster);
        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(boy.getText().toString().equals("")||kilo.toString().equals(""))){
                hesap();
                kaydet();
                }
                else{

                    AlertDialog alertDialog = new AlertDialog.Builder(Endex.this).create();
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
            }
        });
    }

    public void kaydet(){


        float kiloo=Float.parseFloat(kilo.getText().toString());


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.99.1:8081/RestAp_/webresources/kitle/")
                .build();
        APIService api=retrofit.create(APIService.class);
        Bilgiler a=new Bilgiler();

        a.setKULLANICI(MainActivity.isim);
        a.setKILO(kiloo);

        String json=a.toKıtle();
        Log.d("hata2",json);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),json);
        api.createPost2(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("hata1",t.toString());
            }
        });

    }

    public void hesap(){

        AlertDialog alertDialog = new AlertDialog.Builder(Endex.this).create();
        alertDialog.setTitle("Kitle Endeksi");
        //alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        //alertDialog.show();


        float boyy=Float.parseFloat(boy.getText().toString());
        float kiloo=Float.parseFloat(kilo.getText().toString());
        float endex=kiloo/(boyy*boyy);
        if(endex>=40){

        alertDialog.setMessage("Aşırı Obez");
        alertDialog.show();}
        else if(endex>=30){

        alertDialog.setMessage("Obez");
        alertDialog.show();}
        else if(endex>=25){

        alertDialog.setMessage("Fazla Kilolu");
        alertDialog.show();}
        else if(endex>=18.5){

        alertDialog.setMessage("Normal Kilolu");
        alertDialog.show();}
        else if(endex<18.5){

        alertDialog.setMessage("Düşük Kilolu");
        alertDialog.show();}
    }
}
