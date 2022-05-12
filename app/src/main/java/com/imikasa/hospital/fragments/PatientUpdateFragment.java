package com.imikasa.hospital.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imikasa.R;
import com.imikasa.hospital.HospitalViewModel;

public class PatientUpdateFragment extends Fragment {

    private TextView toolText;
    private EditText name;
    private EditText sex;
    private EditText card;
    private EditText phone;
    private EditText address;
    private EditText data;
    private Button btn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_patient_change, container, false);
        HospitalViewModel hospitalViewModel = new ViewModelProvider(requireActivity()).get(HospitalViewModel.class);
        initView(inflate);
        toolText.setText("修改就诊人信息");
        btn.setText("确认修改就诊人信息");
        hospitalViewModel.getPatientMutableLiveData().observe(requireActivity(),patient -> {
            name.setText(patient.getName());
            if (TextUtils.equals(patient.getSex(),"0")){sex.setText("男");}else{sex.setText("女");}
            card.setText(patient.getCardId());
            phone.setText(patient.getTel());
            address.setText(patient.getAddress());
            data.setText(patient.getBirthday());
        });
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
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
    }
}
