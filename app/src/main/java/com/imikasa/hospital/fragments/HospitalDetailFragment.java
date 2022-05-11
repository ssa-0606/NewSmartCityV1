package com.imikasa.hospital.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.hospital.HospitalViewModel;
import com.imikasa.hospital.pojo.HospitalLunBo;

public class HospitalDetailFragment extends Fragment {

    private TextView tool;
    private ViewFlipper vf;
    private TextView jiesao;
    private Button btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_hospital_detail, container, false);
        HospitalViewModel hospitalViewModel = new ViewModelProvider(requireActivity()).get(HospitalViewModel.class);
        initView(inflate);
        tool.setText("医院简介");
        hospitalViewModel.getHospitalLunBoListLiveData().observe(requireActivity(),hospitalLunBos -> {
            vf.removeAllViews();
            for (HospitalLunBo hospitalLunBo : hospitalLunBos) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getContext()).load("http://124.93.196.45:10001"+hospitalLunBo.getImgUrl()).into(imageView);
                vf.addView(imageView);
            }
        });
        hospitalViewModel.getHospitalInfoMutableLiveData().observe(requireActivity(),hospitalInfo -> {
            jiesao.setText(Html.fromHtml(hospitalInfo.getBrief()));
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.hospital_container,PatientFragment.class,null,"patient-show")
                        .hide(getActivity().getSupportFragmentManager().findFragmentByTag("hospital-detail"))
                        .commit();
            }
        });

        return inflate;
    }

    private void initView(View inflate) {

        tool = (TextView) getActivity().findViewById(R.id.hospital_tool_text);
        vf = (ViewFlipper) inflate.findViewById(R.id.hospital_vf);
        jiesao = (TextView) inflate.findViewById(R.id.hospital_jianjie);
        btn = (Button) inflate.findViewById(R.id.hospital_guahao_btn);
    }
}
