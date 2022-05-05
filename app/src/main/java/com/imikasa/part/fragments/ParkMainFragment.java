package com.imikasa.part.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.part.ParkViewModel;
import com.imikasa.part.adapter.ParkMainAdapter;
import com.imikasa.part.pojo.Park;

import java.util.ArrayList;
import java.util.List;

public class ParkMainFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btn;
    private List<Park> parkList;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_park_main, container, false);

        ParkViewModel parkViewModel = new ViewModelProvider(requireActivity()).get(ParkViewModel.class);
        initView(view);
        parkViewModel.getParkLiveData().observe(requireActivity(),parks -> {
            for (int i = 0; i < 5; i++) {
                parkList.add(parks.get(i));
            }
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new ParkMainAdapter(R.layout.layout_park_main_ite,parkList));
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               parkViewModel.getParkLiveData().observe(requireActivity(),parks -> {
                   btn.setVisibility(View.GONE);
                   Toast.makeText(getContext(), "已经加载更多", Toast.LENGTH_SHORT).show();
                   recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                   recyclerView.setAdapter(new ParkMainAdapter(R.layout.layout_park_main_ite,parks));
               });
            }
        });

        return view;
    }

    private void initView(View view) {
        textView = getActivity().findViewById(R.id.park_tool_text);
        textView.setText("停车场");
        recyclerView = (RecyclerView) view.findViewById(R.id.park_main_recycler);
        btn = (Button) view.findViewById(R.id.park_show_more);
        parkList = new ArrayList<>();
    }
}
