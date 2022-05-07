package com.imikasa.bus.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imikasa.R;
import com.imikasa.bus.BusViewModel;
import com.imikasa.bus.pojo.BusStop;

import java.util.ArrayList;
import java.util.List;

public class BusUserFragment extends Fragment {

    private TextView tool;
    private TextView lineName;
    private TextView start;
    private TextView end;
    private TextView userName;
    private TextView phone;

    private Spinner up;
    private Spinner down;
    private Button btn;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bus_user, container, false);
        BusViewModel busViewModel = new ViewModelProvider(requireActivity()).get(BusViewModel.class);
        initView(inflate);
        busViewModel.setBusUserLiveData(sharedPreferences.getString("token","k"));

        tool.setText("用户信息");
        busViewModel.getBusLineLiveDataDetail().observe(requireActivity(),busLine -> {
            lineName.setText(busLine.getName());
            start.setText("始发站："+busLine.getFirst());
            end.setText("终点站："+busLine.getEnd());
        });
        busViewModel.getBusUserLiveData().observe(requireActivity(),userInfo -> {
            if(userInfo==null){
                userName.setText("请先登录!!!");
            }else{
                userName.setText("乘客姓名："+userInfo.getNickName());
                phone.setText("联系方式："+userInfo.getPhonenumber());
            }
        });

        busViewModel.getBusStopLiveData().observe(requireActivity(),busStops -> {
            List<String> list = new ArrayList<>();
            for (int i = 0; i <busStops.size()-1; i++) {
                list.add(busStops.get(i).getName());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,list);
            up.setAdapter(arrayAdapter);
            List<String> downList = new ArrayList<>();
            for (int i = 1; i < busStops.size(); i++) {
                downList.add(busStops.get(i).getName());
            }
            down.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,downList));
        });

        up.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemSelected: "+i+"|"+l);
                List<String> downList = new ArrayList<>();
                busViewModel.getBusStopLiveData().observe(requireActivity(),busStops -> {
                    for (int j = (i+1); j < busStops.size(); j++) {
                        downList.add(busStops.get(j).getName());
                    }
                    down.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,downList));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String upValue = (String) up.getSelectedItem();
                String downValue = (String) down.getSelectedItem();
                editor.putString("bus-up",upValue);
                editor.putString("bus-down",downValue);
                editor.commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .hide(getActivity().getSupportFragmentManager().findFragmentByTag("bus-user"))
                        .add(R.id.bus_container,BusCommitFragment.class,null,"bus-commit")
                        .commit();
            }
        });

        return inflate;
    }

    private void initView(View inflate) {

        tool = (TextView) getActivity().findViewById(R.id.bus_tool_text);
        lineName = (TextView) inflate.findViewById(R.id.bus_user_line_name);
        start = (TextView) inflate.findViewById(R.id.bus_user_first);
        end = (TextView) inflate.findViewById(R.id.bus_user_end);
        userName = (TextView) inflate.findViewById(R.id.bus_user_name);
        phone = (TextView) inflate.findViewById(R.id.bus_user_phone);
        up = (Spinner) inflate.findViewById(R.id.bus_user_up);
        down = (Spinner) inflate.findViewById(R.id.bus_user_down);
        btn = (Button) inflate.findViewById(R.id.bus_user_jump);
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
    }


}
