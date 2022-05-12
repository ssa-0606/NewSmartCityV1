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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imikasa.R;
import com.imikasa.hospital.HospitalViewModel;
import com.imikasa.tools.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

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
    private int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_patient_change, container, false);
        HospitalViewModel hospitalViewModel = new ViewModelProvider(requireActivity()).get(HospitalViewModel.class);
        initView(inflate);
        toolText.setText("修改就诊人信息");
        btn.setText("确认修改就诊人信息");
        hospitalViewModel.getPatientMutableLiveData().observe(requireActivity(),patient -> {
            id = patient.getId();
            name.setText(patient.getName());
            if (TextUtils.equals(patient.getSex(),"0")){sex.setText("男");}else{sex.setText("女");}
            card.setText(patient.getCardId());
            phone.setText(patient.getTel());
            address.setText(patient.getAddress());
            data.setText(patient.getBirthday());
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText())||TextUtils.isEmpty(card.getText())||TextUtils.isEmpty(phone.getText())){
                    Toast.makeText(getContext(), "姓名,身份证号或者手机号不得为空", Toast.LENGTH_SHORT).show();
                }else{
                    String nameVal = name.getText().toString();
                    String sexVal = sex.getText().toString();
                    String cardVal = card.getText().toString();
                    String phoneVal = phone.getText().toString();
                    String addressVal = address.getText().toString();
                    String dataVal = data.getText().toString();
                    String gender = "0";
                    if(TextUtils.equals(sexVal,"男")){
                        gender = "0";
                    }else if(TextUtils.equals(sexVal,"女")){
                        gender = "1";
                    }else {
                        Toast.makeText(getContext(), "请输入男或女", Toast.LENGTH_SHORT).show();
                    }

                    if(!mathch(dataVal)){
                        Toast.makeText(getContext(), "请输入正确格式的日期", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("id",id);
                            jsonObject.put("name",nameVal);
                            jsonObject.put("sex",gender);
                            jsonObject.put("cardId",cardVal);
                            jsonObject.put("tel",phoneVal);
                            jsonObject.put("address",addressVal);
                            jsonObject.put("birthday",dataVal);
                            autoGen(jsonObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        });



        return inflate;
    }


    private void autoGen(String json) {
        Thread thread = new Thread(()->{

            try {
                String result = MyUtils.PUT_T("http://124.93.196.45:10001/prod-api/api/hospital/patient", json, sharedPreferences.getString("token", "k"));
                int code = new JSONObject(result).getInt("code");
                if (code == 200){
                    getActivity().runOnUiThread(()->{
                        Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        });
        thread.start();

    }


    private boolean mathch(String data) {
        boolean matches = Pattern.matches("^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$", data);
        return matches;
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
