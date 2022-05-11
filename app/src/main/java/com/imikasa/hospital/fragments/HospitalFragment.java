package com.imikasa.hospital.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.hospital.HospitalViewModel;
import com.imikasa.hospital.adapter.HospitalAdapter;
import com.imikasa.hospital.pojo.HospitalLunBo;

public class HospitalFragment extends Fragment {

    private TextView toolText;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_hospital_main, container, false);
        HospitalViewModel hospitalViewModel = new ViewModelProvider(requireActivity()).get(HospitalViewModel.class);

        initView(inflate);
        toolText.setText("医院");

        hospitalViewModel.getHospitalInfoListLiveData().observe(requireActivity(),hospitalInfos -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new HospitalAdapter(R.layout.layout_hospital_list,hospitalInfos));
        });

        return inflate;
    }

    private void initView(View inflate) {
        toolText = getActivity().findViewById(R.id.hospital_tool_text);
        recyclerView = inflate.findViewById(R.id.hospital_list);
    }
}
