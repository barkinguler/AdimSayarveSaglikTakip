package com.example.yurumesayar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.yurumesayar.Models.Bilgiler;
import com.example.yurumesayar.RestApi.ManagerAll;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kilografik extends AppCompatActivity {
    private BarChart mBarChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labesNames;
    ArrayList<Integer> labessayı;
    public String isim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilografik);
        mBarChart = findViewById(R.id.barChart);
        barEntryArrayList = new ArrayList<>();
        labesNames = new ArrayList<>();
        getGrownChart();
    }

    private void getGrownChart() {
        isim = MainActivity.isim;
        Call<List<Bilgiler>> bilgiList = ManagerAll.getInstance().getirKilogecmis();
        bilgiList.enqueue(new Callback<List<Bilgiler>>() {
            @Override
            public void onResponse(Call<List<Bilgiler>> call, Response<List<Bilgiler>> response) {

                List<String> tut = new ArrayList<>();
                List<String> tut2 = new ArrayList<>();

                for (int i = 0; i < response.body().size(); i++) {
                    if (isim.equals(response.body().get(i).getKULLANICI())) {


                        tut.add(Float.toString(response.body().get(i).getKILO()));
                        tut2.add(response.body().get(i).getTARIH().substring(0, 10));


                    }


                }

                for (int i = 0; i < tut.size(); i++) {

                    barEntryArrayList.add(new BarEntry(i, Float.parseFloat(tut.get(i))));
                    labesNames.add(tut2.get(i).substring(0, 10));


                }


                BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Günler");
                barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                Description description = new Description();
                description.setText("Günler");
                mBarChart.setDescription(description);
                BarData barData = new BarData(barDataSet);
                mBarChart.setData(barData);
                XAxis xAxis = mBarChart.getXAxis();

                xAxis.setValueFormatter(new IndexAxisValueFormatter(labesNames));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                xAxis.setDrawAxisLine(false);

                xAxis.setGranularityEnabled(true);

                xAxis.setLabelCount(labesNames.size());
                xAxis.setLabelRotationAngle(270);

                YAxis rightYAxis = mBarChart.getAxisRight();
                rightYAxis.setEnabled(true);
                mBarChart.getAxisLeft().setEnabled(true);

                Legend l = mBarChart.getLegend();
                l.setTextSize(20f);


                barData.setValueTextSize(20f);

                mBarChart.setExtraBottomOffset(100);
                xAxis.setTextSize(20f);


                mBarChart.getLegend().setEnabled(false);
                mBarChart.invalidate();

            }

            @Override
            public void onFailure(Call<List<Bilgiler>> call, Throwable t) {
                Log.d("xxx", t.toString());
            }
        });


    }
}
