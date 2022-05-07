package com.imikasa.bus.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.imikasa.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BusDataFragment extends Fragment {

    private TextView textView;
    private CalendarView calendarView;
    private TextView show;
    private Button btn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bus_data, container, false);

        initView(inflate);
        textView.setText("乘车日期");
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH)+1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        show.setText("您选择的大巴出行日期为："+year+"年"+month+"月"+day+"日");
        data = year+"年"+month+"月"+day+"日";
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                show.setText("您选择的大巴出行日期为："+i+"年"+(i1+1)+"月"+i2+"日");
                data = i+"年"+(i1+1)+"月"+i2+"日";
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("bus-data",data);
                editor.commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.bus_container,BusUserFragment.class,null,"bus-user")
                        .hide(getActivity().getSupportFragmentManager().findFragmentByTag("bus-data"))
                        .commit();
            }
        });
        return inflate;
    }

    private void initView(View inflate) {
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
        textView = getActivity().findViewById(R.id.bus_tool_text);
        calendarView = (CalendarView) inflate.findViewById(R.id.bus_data_cal);
        show = (TextView) inflate.findViewById(R.id.bus_data_show);
        btn = (Button) inflate.findViewById(R.id.bus_data_jump);
    }
}
