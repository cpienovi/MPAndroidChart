package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.renderer.BarChartRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class RoundedBarChartActivity extends FragmentActivity {

    private static final float Y_MAX = 100f;
    private static final float Y_MIN = 0f;

    private BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_bar_chart);

        barChart = findViewById(R.id.activity_rounded_bar_chart_bar_chart);

        int colorTextAxis = Color.BLACK;
        int colorDashLine = Color.GRAY;

        barChart.getLegend().setEnabled(false);
        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisLeft().setTextColor(colorTextAxis);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setAxisLineColor(colorDashLine);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setDrawLimitLinesBehindData(true);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setTextColor(colorTextAxis);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "Mon\n" + String.valueOf(value);
            }
        });
        barChart.setTouchEnabled(false);
        barChart.setNoDataTextColor(Color.WHITE);
        barChart.setMinOffset(0f);
        ((BarChartRenderer) barChart.getRenderer()).setRadius(getResources().getDimensionPixelSize(R.dimen.sleep_score_chart_bar_radius));

        loadScore();
    }

    private void loadScore() {
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            float yValue = new Random().nextInt((int) Y_MAX);
            entries.add(new BarEntry(i, new float[]{Y_MIN, yValue, Y_MAX - yValue}));
        }
        BarDataSet dataSet = new BarDataSet(entries, null);
        dataSet.setColors(Color.TRANSPARENT, Color.GREEN, Color.TRANSPARENT);
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.3f);
        barData.setDrawValues(false);
        barChart.setData(barData);
        barChart.invalidate();
    }

}
