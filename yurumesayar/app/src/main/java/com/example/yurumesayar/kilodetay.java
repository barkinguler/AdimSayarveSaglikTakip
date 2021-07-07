package com.example.yurumesayar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.yurumesayar.Models.Bilgiler;
import com.example.yurumesayar.RestApi.ManagerAll;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kilodetay extends AppCompatActivity {
    public String isim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilodetay);
        yap();
    }

    public void yap() {

        isim = MainActivity.isim;
        Call<List<Bilgiler>> bilgiList = ManagerAll.getInstance().getirKilogecmis();
        bilgiList.enqueue(new Callback<List<Bilgiler>>() {
            @Override
            public void onResponse(Call<List<Bilgiler>> call, Response<List<Bilgiler>> response) {
                DataTable dataTable = findViewById(R.id.data_table);

                DataTableHeader header = new DataTableHeader.Builder()
                        .item("TARİH", 4)
                        .item("KİLO", 2)

                        .build();
                ArrayList<DataTableRow> rows = new ArrayList<>();
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                Log.d("xxx", Integer.toString(response.body().size()));
                for (int i = 0; i < response.body().size(); i++) {
                    if (isim.equals(response.body().get(i).getKULLANICI())) {
                        Log.d("xyxy", response.body().get(i).getKULLANICI() + response.body().get(i).getADIM());
                        DataTableRow row = new DataTableRow.Builder()
                                .value(response.body().get(i).getTARIH().substring(0, 10))
                                .value(df.format(response.body().get(i).getKILO()))
                                .build();
                        rows.add(row);
                    }


                }

                dataTable.setHeader(header);
                dataTable.setRows(rows);
                dataTable.inflate(kilodetay.this);


            }

            @Override
            public void onFailure(Call<List<Bilgiler>> call, Throwable t) {
                Log.d("xxx", t.toString());
            }
        });


    }

}
