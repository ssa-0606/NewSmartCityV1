package com.imikasa.house.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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
    private HouseViewModel houseViewModel;
    private CardView cat1;
    private CardView cat2;
    private CardView cat3;
    private CardView cat4;
    private EditText search;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_house_main, container, false);
        houseViewModel = new ViewModelProvider(requireActivity()).get(HouseViewModel.class);

        initView(inflate);
        textView.setText("找房子");

        houseViewModel.getHouseListLiveData().observe(requireActivity(),houses -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new HouseAdapter(R.layout.layout_house_item,houses));
        });
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                houseViewModel.setHouseListLiveData("二手");
            }
        });
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                houseViewModel.setHouseListLiveData("租房");
            }
        });
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                houseViewModel.setHouseListLiveData("楼盘");
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                houseViewModel.setHouseListLiveData("中介");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(search.getText())){
                    Toast.makeText(getContext(), "输入不得为空", Toast.LENGTH_SHORT).show();
                }else{
                    houseViewModel.setHouseListLiveDataByName(search.getText().toString());
                }
            }
        });


        return inflate;
    }

    private void initView(View inflate) {

        textView = getActivity().findViewById(R.id.house_tool_text);
        recyclerView = inflate.findViewById(R.id.house_main_recycler);
        cat1 = inflate.findViewById(R.id.house_cat1);
        cat2 = inflate.findViewById(R.id.house_cat2);
        cat3 = inflate.findViewById(R.id.house_cat3);
        cat4 = inflate.findViewById(R.id.house_cat4);
        search = inflate.findViewById(R.id.house_main_search);
        btn = inflate.findViewById(R.id.house_main_btn);
    }


}
