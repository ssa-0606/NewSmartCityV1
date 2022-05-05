package com.imikasa.part.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.part.ParkViewModel;

public class ParkDetailFragment extends Fragment {

    private ImageView imageView;
    private TextView parkName;
    private TextView parkAddress;
    private TextView parkDistance;
    private TextView allParks;
    private TextView vacancy;
    private TextView rates;
    private TextView open;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_park_detail, container, false);
        ParkViewModel parkViewModel = new ViewModelProvider(requireActivity()).get(ParkViewModel.class);
        initView(inflate);
        parkViewModel.getParkLive().observe(requireActivity(),park -> {
            Glide.with(getContext()).load("http://124.93.196.45:10001"+park.getImgUrl()).into(imageView);
            parkName.setText(park.getParkName());
            parkAddress.setText(park.getAddress());
            parkDistance.setText(park.getDistance()+"km");
            allParks.setText(park.getAllPark());
            vacancy.setText(park.getVacancy());
            rates.setText("收费标准："+park.getRates()+"元/小时(最高"+park.getPriceCaps()+"元)");
            if(TextUtils.equals(park.getOpen(),"Y")){
                open.setText("对外开放");
            }else{
                open.setTextColor(Color.rgb(255,0,0));
                open.setText("暂未开放");
            }
        });

        return inflate;
    }

    private void initView(View inflate) {
        textView = getActivity().findViewById(R.id.park_tool_text);
        textView.setText("停车场详情");
        imageView = inflate.findViewById(R.id.park_detail_img);
        parkName = inflate.findViewById(R.id.park_detail_name);
        parkAddress = inflate.findViewById(R.id.park_detail_address);
        parkDistance = inflate.findViewById(R.id.park_detail_distance);
        allParks = inflate.findViewById(R.id.park_detail_allparks);
        vacancy = inflate.findViewById(R.id.park_detail_vacancy);
        rates = inflate.findViewById(R.id.park_detail_rates);
        open = inflate.findViewById(R.id.park_detail_open);
    }
}
