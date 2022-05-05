package com.imikasa.part.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.part.ParkViewModel;
import com.imikasa.part.adapter.ParkRecordAdapter;
import com.imikasa.part.pojo.Park;
import com.imikasa.part.pojo.ParkRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ParkRecordFragment extends Fragment {

    private EditText parkIn;
    private EditText parkOut;
    private Button parkQuery;
    private TextView parkTip;
    private RecyclerView recyclerView;
    private Button parkShow;
    private List<ParkRecord> show;
    private ParkViewModel parkViewModel;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tool_park,menu);
        menu.clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_park_record, container, false);
        setHasOptionsMenu(true);
        parkViewModel = new ViewModelProvider(requireActivity()).get(ParkViewModel.class);
        initView(inflate);
        parkViewModel.getParkRecordLiveData().observe(requireActivity(),parkRecords -> {
            for (int i = 0; i < 5; i++) {
                show.add(parkRecords.get(i));
            }
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new ParkRecordAdapter(R.layout.layout_park_record_item,show));
        });

        parkShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parkViewModel.getParkRecordLiveData().observe(requireActivity(),parkRecords -> {
                    Toast.makeText(getContext(), "已经加载更多", Toast.LENGTH_SHORT).show();
                    parkShow.setVisibility(View.GONE);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                    recyclerView.setAdapter(new ParkRecordAdapter(R.layout.layout_park_record_item,parkRecords));
                });
            }
        });

        parkQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inTime = parkIn.getText().toString().trim();
                String outTime = parkOut.getText().toString().trim();

                boolean inIsEmpty = TextUtils.isEmpty(inTime);
                boolean outIsEmpty = TextUtils.isEmpty(outTime);

                boolean inMathch = mathch(inTime);
                boolean outMathch = mathch(outTime);

                if(inMathch&&outMathch){
                    changeView(inTime,outTime);
                }else if (inMathch&&!outMathch&&outIsEmpty){
                    changeView(inTime,outTime);
                }else if (!inMathch&&outMathch&&inIsEmpty){
                    changeView(inTime,outTime);
                }else{
                    Toast.makeText(getContext(), "请输入正确的格式", Toast.LENGTH_SHORT).show();
                }

            }
        });



        return inflate;
    }

    public void changeView(String inTime,String outTime){
        parkViewModel.setParkRecordLiveData(inTime,outTime);
        parkViewModel.getQueryRecord().observe(requireActivity(),parkRecords -> {
            if(parkRecords.size() == 0){
                Toast.makeText(getContext(), "当前时段没有车辆光临", Toast.LENGTH_SHORT).show();
            }else{
                parkShow.setVisibility(View.GONE);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                recyclerView.setAdapter(new ParkRecordAdapter(R.layout.layout_park_record_item,parkRecords));
            }

        });
    }

    private boolean mathch(String data) {
        boolean matches = Pattern.matches("^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][1-9]|3[01])$", data);
        return matches;
    }

    private void initView(View inflate) {
        show = new ArrayList<>();
        parkIn = (EditText) inflate.findViewById(R.id.park_record_in);
        parkOut = (EditText) inflate.findViewById(R.id.park_record_out);
        parkQuery = (Button) inflate.findViewById(R.id.park_record_btn);
        parkTip = (TextView) inflate.findViewById(R.id.park_record_tip);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.park_record_recycler);
        parkShow = (Button) inflate.findViewById(R.id.park_record_show_more);

    }
}
