package com.imikasa.bus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.bus.BusViewModel;
import com.imikasa.bus.adapter.BusListAdapter;

public class BusListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_bus_list, container, false);
        BusViewModel busViewModel = new ViewModelProvider(requireActivity()).get(BusViewModel.class);
        initView(inflate);
        textView.setText("智慧巴士");

        busViewModel.getBusLineLiveData().observe(requireActivity(),busLines -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new BusListAdapter(R.layout.layout_bus_list_item,busLines));
        });


        return inflate;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.bus_list_recycler);
        textView = getActivity().findViewById(R.id.bus_tool_text);
    }
}
