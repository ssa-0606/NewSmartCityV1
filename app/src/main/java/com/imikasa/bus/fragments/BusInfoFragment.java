package com.imikasa.bus.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.bus.BusViewModel;
import com.imikasa.bus.adapter.BusStopAdapter;

public class BusInfoFragment extends Fragment {

    private TextView tool;
    private RecyclerView recyclerView;
    private TextView lineName;
    private TextView start;
    private TextView end;
    private TextView payMoney;
    private TextView mileage;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bus_info, container, false);
        BusViewModel busViewModel = new ViewModelProvider(requireActivity()).get(BusViewModel.class);
        initView(inflate);
        tool.setText("班车信息");
        busViewModel.getBusLineLiveDataDetail().observe(requireActivity(),busLine -> {
            Log.d("TAG", "onCreateView: "+busLine+busLine.getName());
            lineName.setText(busLine.getName());
            start.setText("始发站："+busLine.getFirst());
            end.setText("终点站："+busLine.getEnd());
            payMoney.setText("票价："+busLine.getPrice()+"元");
            mileage.setText("全程"+busLine.getMileage()+"km");
        });

        busViewModel.getBusStopLiveData().observe(requireActivity(),busStops -> {
            recyclerView.setAdapter(new BusStopAdapter(R.layout.layout_bus_stop_item,busStops));
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .hide(getActivity().getSupportFragmentManager().findFragmentByTag("bus-info"))
                        .add(R.id.bus_container,BusDataFragment.class,null,"bus-data")
                        .commit();
            }
        });


        return inflate;
    }

    private void initView(View inflate) {
        tool = getActivity().findViewById(R.id.bus_tool_text);
        recyclerView = inflate.findViewById(R.id.bus_info_recycler);
        lineName = inflate.findViewById(R.id.bus_info_lin_name);
        start = inflate.findViewById(R.id.bus_info_start);
        end = inflate.findViewById(R.id.bus_info_end);
        payMoney = inflate.findViewById(R.id.bus_info_pay);
        mileage = inflate.findViewById(R.id.bus_info_mileage);
        btn = inflate.findViewById(R.id.bus_info_jump);
    }


}
