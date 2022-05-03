package com.imikasa.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.imikasa.R;
import com.imikasa.ui.notifications.tool.MyRender;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private BarChart barChart;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        View inflate = inflater.inflate(R.layout.fragment_notifications, container, false);
        barChart = (BarChart) inflate.findViewById(R.id.bar_chart);


        notificationsViewModel.getText().observe(requireActivity(),mainNewsItems -> {
            List<BarEntry> barEntries = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            mainNewsItems.forEach(mainNewsItem -> barEntries.add(new BarEntry(mainNewsItem.getId(),(mainNewsItem.getLikeNum()))));
            mainNewsItems.forEach(mainNewsItem -> titles.add(TextUtils.substring(mainNewsItem.getTitle(),0,4)+"\n"+TextUtils.substring(mainNewsItem.getTitle(),4,8)+"\n"+TextUtils.substring(mainNewsItem.getTitle(),8,12)+"\n"+TextUtils.substring(mainNewsItem.getTitle(),13,18)+"\n..."));
//            Log.d("TAG999", "onCreateView: "+titles);
            barChart.getXAxis().setDrawGridLines(false);
            barChart.getAxisLeft().setDrawGridLines(false);
            barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            barChart.getAxisRight().setEnabled(false);
            barChart.getXAxis().setDrawLabels(true);
            barChart.setScaleEnabled(false);
            barChart.getAxisLeft().setAxisLineWidth(2);
            barChart.getXAxis().setAxisLineWidth(2);
            barChart.getDescription().setText("新闻点赞统计");
            barChart.getDescription().setPosition(900,100);
            barChart.getDescription().setTextSize(20f);
            barChart.getLegend().setEnabled(false);

            barChart.getXAxis().setLabelCount(5,false);

            BarDataSet barDataSet = new BarDataSet(barEntries,null);
            barDataSet.setColor(Color.parseColor("#FF6363"));
            barDataSet.setValueTextSize(15f);

            barChart.getXAxis().setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    super.getFormattedValue(value);
//                    Log.d("TAG9999", "getFormattedValue: "+value);
                    for (int i = 0; i < mainNewsItems.size(); i++) {
                        if(value==mainNewsItems.get(i).getId()) {
                            return titles.get(i);
                        }
                    }
                    return "";
                }
            });
            barChart.setXAxisRenderer(new MyRender(barChart.getViewPortHandler(),barChart.getXAxis(),barChart.getTransformer(YAxis.AxisDependency.LEFT)));
            barChart.setExtraBottomOffset(4* 13f);
            barChart.getXAxis().setTextSize(13f);
            BarData barData = new BarData(barDataSet);
            barData.setBarWidth(0.6f);

            barChart.setData(barData);
            barChart.animateY(3000);
        });



        return inflate;
    }



}