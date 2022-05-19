package com.imikasa.ui.dashboard;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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
    private SearchView searchView;
    private SearchView realSearch;
    String[] types = new String[]{"车主服务","生活服务","便民服务"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View inflate = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initView(inflate);
        searchView.setVisibility(View.GONE);
        realSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                dashboardViewModel.setServiceLiveDataByMsg(query);

                Dialog dialog = new Dialog(requireActivity());
                View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_service, null);
                dialog.setContentView(inflate1);
                RecyclerView recyclerView = inflate1.findViewById(R.id.dialog_recycler);
                Button btn = inflate1.findViewById(R.id.dialog_service_btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dashboardViewModel.getShowService().observe(requireActivity(),mainServices -> {
                    if(mainServices == null){

                    }else{
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                        recyclerView.setAdapter(new ServiceAdapter(R.layout.layout_main_service,mainServices));
                    }

                });
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
        searchView = getActivity().findViewById(R.id.main_search);
        realSearch = view.findViewById(R.id.service_search);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchView.setVisibility(View.VISIBLE);
    }
}