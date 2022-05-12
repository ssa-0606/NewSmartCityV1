package com.imikasa.hospital.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.imikasa.R;

public class PatientalAddFragment extends Fragment {

    private TextView toolText;
    private EditText name;
    private EditText sex;
    private EditText card;
    private EditText phone;
    private EditText address;
    private EditText data;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_patient_change, container, false);

        initView(inflate);
        toolText.setText("添加就诊人");
        btn.setText("确定添加就诊人卡片");


        return inflate;
    }

    private void initView(View inflate) {
        toolText = getActivity().findViewById(R.id.hospital_tool_text);
        name = inflate.findViewById(R.id.patient_change_name);
        sex = inflate.findViewById(R.id.patient_change_sex);
        card = inflate.findViewById(R.id.patient_change_card);
        phone = inflate.findViewById(R.id.patient_change_phone);
        address = inflate.findViewById(R.id.patient_change_address);
        data = inflate.findViewById(R.id.patient_change_data);
        btn = inflate.findViewById(R.id.patient_change_btn);
    }
}

