package com.imikasa.house.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.house.HouseViewModel;

public class HouseDetailFragment extends Fragment {

    private TextView toolText;
    private ImageView imageView;
    private TextView name;
    private TextView size;
    private TextView price;
    private TextView cate;
    private TextView desc;
    private Button btn ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_house_detail, container, false);
        HouseViewModel houseViewModel = new ViewModelProvider(requireActivity()).get(HouseViewModel.class);
        initView(inflate);
        toolText.setText("详细信息");

        houseViewModel.getHouseMutableLiveData().observe(requireActivity(),house -> {
            Glide.with(getContext()).load("http://124.93.196.45:10001"+house.getPic()).into(imageView);
            name.setText(house.getSourceName());
            size.setText(house.getAreaSize());
            price.setText(house.getPrice());
            cate.setText(house.getHouseType());
            desc.setText(house.getDescription());
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .show(getActivity().getSupportFragmentManager().findFragmentByTag("house-main"))
                        .hide(getActivity().getSupportFragmentManager().findFragmentByTag("house-detail"))
                        .commit();
            }
        });

        return inflate;
    }

    private void initView(View inflate) {

        toolText = getActivity().findViewById(R.id.house_tool_text);
        imageView = inflate.findViewById(R.id.house_detail_pic);
        name = inflate.findViewById(R.id.house_detail_name);
        size = inflate.findViewById(R.id.house_detail_size);
        price = inflate.findViewById(R.id.house_detail_price);
        cate = inflate.findViewById(R.id.house_detail_cate);
        desc = inflate.findViewById(R.id.house_detail_desc);
        btn = inflate.findViewById(R.id.house_detail_jump);
    }
}
