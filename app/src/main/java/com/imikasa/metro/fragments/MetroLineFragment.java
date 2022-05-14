package com.imikasa.metro.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.metro.MetroViewModel;
import com.imikasa.metro.adapters.MetroLineAdapter;

public class MetroLineFragment extends Fragment {

    private Toolbar toolbar ;
    private TextView textView;
    private RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_metro_line, container, false);
        MetroViewModel metroViewModel = new ViewModelProvider(requireActivity()).get(MetroViewModel.class);

        initView(inflate);
        textView.setText("城市地铁");
        metroViewModel.getListMetroLineLiveData().observe(requireActivity(),metroLines -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new MetroLineAdapter(R.layout.layout_metro_line,metroLines));
        });


        return inflate;
    }

    private void initView(View inflate) {
        toolbar = getActivity().findViewById(R.id.metro_tool);
        textView = getActivity().findViewById(R.id.metro_tool_text);
        recyclerView = inflate.findViewById(R.id.metro_recycler);
    }
}
