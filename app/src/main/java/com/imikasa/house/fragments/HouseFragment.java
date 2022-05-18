package com.imikasa.house.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.imikasa.house.HouseViewModel;
import com.imikasa.house.adapter.HouseAdapter;

import org.w3c.dom.Text;

public class HouseFragment extends Fragment {


    private TextView textView;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_house_main, container, false);
        HouseViewModel houseViewModel = new ViewModelProvider(requireActivity()).get(HouseViewModel.class);

        initView(inflate);
        textView.setText("找房子");

        houseViewModel.getHouseListLiveData().observe(requireActivity(),houses -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new HouseAdapter(R.layout.layout_house_item,houses));
        });


        return inflate;
    }

    private void initView(View inflate) {

        textView = getActivity().findViewById(R.id.house_tool_text);
        recyclerView = inflate.findViewById(R.id.house_main_recycler);
    }
}
