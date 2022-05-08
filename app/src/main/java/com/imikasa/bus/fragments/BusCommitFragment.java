package com.imikasa.bus.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.imikasa.R;
import com.imikasa.bus.BusViewModel;

public class BusCommitFragment extends Fragment {

    private TextView tool;
    private TextView username;
    private TextView phone;
    private TextView start;
    private TextView end;
    private TextView data;
    private Button btn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_user_commit, container, false);

        BusViewModel busViewModel = new ViewModelProvider(requireActivity()).get(BusViewModel.class);
        initView(inflate);
        tool.setText("提交订单");

        busViewModel.getBusUserLiveData().observe(requireActivity(),userInfo -> {
            if(userInfo==null){
                username.setText("请先登录！！！");
            }else{
                username.setText(userInfo.getNickName());
                phone.setText(userInfo.getPhonenumber());
            }
        });
        start.setText(sharedPreferences.getString("bus-up","unKnow"));
        end.setText(sharedPreferences.getString("bus-down","unKnow"));
        data.setText(sharedPreferences.getString("bus-data","unKnow"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(username.getText(),"请先登录！！！")){
                    Toast.makeText(getContext(), "未登录，不可以提交订单", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "订单提交成功！", Toast.LENGTH_SHORT).show();
                    //api不完善，暂时放着
                    getActivity().finish();
                }
            }
        });


        return inflate;
    }

    private void initView(View inflate) {
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
        tool = getActivity().findViewById(R.id.bus_tool_text);
        username = inflate.findViewById(R.id.bus_commit_name);
        phone = inflate.findViewById(R.id.bus_commit_phone);
        start = inflate.findViewById(R.id.bus_commit_up);
        end = inflate.findViewById(R.id.bus_commit_down);
        data = inflate.findViewById(R.id.bus_commit_data);
        btn = inflate.findViewById(R.id.bus_commit_btn);
    }
}
