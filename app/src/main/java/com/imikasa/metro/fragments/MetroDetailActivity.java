package com.imikasa.metro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.metro.MetroViewModel;
import com.imikasa.metro.adapters.LineAdapter;

public class MetroDetailActivity extends Fragment {

    private TextView toolText;
    private TextView first;
    private TextView end;
    private TextView reachTime;
    private TextView stationNum;
    private TextView distance;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_metro_detail, container, false);
        MetroViewModel metroViewModel = new ViewModelProvider(requireActivity()).get(MetroViewModel.class);
        initView(inflate);

        metroViewModel.getMetroStationMutableLiveData().observe(requireActivity(),metroStation -> {

            toolText.setText(metroStation.getName());
            first.setText(metroStation.getFirst());
            end.setText(metroStation.getEnd());
            reachTime.setText(String.valueOf(metroStation.getRemainingTime())+"分钟");
            stationNum.setText(metroStation.getStationsNumber()+"站");
            distance.setText(metroStation.getKm()+"km");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new LineAdapter(R.layout.layout_lines,metroStation.getLineStations(),metroStation.getRunStationsName()));
        });


        return inflate;
    }

    private void initView(View inflate) {

        toolText = getActivity().findViewById(R.id.metro_tool_text);
        first = inflate.findViewById(R.id.metro_first);
        end = inflate.findViewById(R.id.metro_end);
        reachTime = inflate.findViewById(R.id.metro_reach_time);
        stationNum = inflate.findViewById(R.id.metro_station_num);
        distance = inflate.findViewById(R.id.metro_distance);
        recyclerView = inflate.findViewById(R.id.metro_line_recycler);
    }
}
