package com.imikasa.hospital.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.hospital.HospitalViewModel;
import com.imikasa.hospital.adapter.PatientAdapter;
import com.imikasa.hospital.pojo.Patient;
import com.imikasa.tools.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientFragment extends Fragment {

    private TextView toolText;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Patient> patientList;
    HospitalViewModel hospitalViewModel;
    private Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_patient_show, container, false);
        hospitalViewModel = new ViewModelProvider(requireActivity()).get(HospitalViewModel.class);
        initView(inflate);
        toolText.setText("就诊人卡片");
        hospitalViewModel.setUserInfoMutableLiveData(sharedPreferences.getString("token","k"));
        hospitalViewModel.setListPatientMutableLiveData(sharedPreferences.getString("token","k"));
        hospitalViewModel.getListPatientMutableLiveData().observe(requireActivity(),patients -> {
            if(patients.size()==0){
                hospitalViewModel.getUserInfoMutableLiveData().observe(requireActivity(),userInfo -> {
                    Log.d("TAG", "onCreateView: "+userInfo.getUserName());
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name",userInfo.getUserName());
                        jsonObject.put("cardId",userInfo.getIdCard());
                        autoGen(jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                recyclerView.setAdapter(new PatientAdapter(R.layout.layout_patient_item,patients));
            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.hospital_container,PatientalAddFragment.class,null,"patient-add")
                        .hide(getActivity().getSupportFragmentManager().findFragmentByTag("patient-show"))
                        .commit();
            }
        });

        return inflate;
    }

    private void autoGen(String json) {
        Thread thread = new Thread(()->{

            try {
                String result = MyUtils.POST_T("http://124.93.196.45:10001/prod-api/api/hospital/patient", json, sharedPreferences.getString("token", "k"));
                int code = new JSONObject(result).getInt("code");
                if (code == 200){
                    getActivity().runOnUiThread(()->{
                        Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                        hospitalViewModel.setListPatientMutableLiveData(sharedPreferences.getString("token","k"));
                        hospitalViewModel.getListPatientMutableLiveData().observe(requireActivity(),patients -> {
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                            recyclerView.setAdapter(new PatientAdapter(R.layout.layout_patient_item,patients));
                        });
                    });

                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        });
        thread.start();

    }

    private void initView(View inflate) {
        patientList = new ArrayList<>();
        toolText = getActivity().findViewById(R.id.hospital_tool_text);
        recyclerView = inflate.findViewById(R.id.patient_recycler);
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
        btn = inflate.findViewById(R.id.patient_jump_change_add);
    }
}
