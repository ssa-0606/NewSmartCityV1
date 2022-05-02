package com.imikasa.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.databinding.FragmentDashboardBinding;
import com.imikasa.ui.home.adapter.ServiceAdapter;

public class DashboardFragment extends Fragment {

    private ListView serviceLeft;
    private RecyclerView serviceRight;
    String[] types = new String[]{"车主服务","生活服务","便民服务"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View inflate = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initView(inflate);

        serviceLeft.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,types));
        dashboardViewModel.getService().observe(requireActivity(),mainServices -> {
            serviceRight.setLayoutManager(new GridLayoutManager(getActivity(),3));
            serviceRight.setAdapter(new ServiceAdapter(R.layout.layout_main_service,mainServices));
        });
        serviceLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dashboardViewModel.setServiceLiveData(types[i]);
            }
        });


        return inflate;
    }

    private void initView(View view) {
        serviceLeft = (ListView) view.findViewById(R.id.all_service_left);
        serviceRight = (RecyclerView) view.findViewById(R.id.all_service_right);
    }


}