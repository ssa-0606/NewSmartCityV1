package com.imikasa.bus.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.bus.BusViewModel;
import com.imikasa.bus.fragments.BusInfoFragment;
import com.imikasa.bus.fragments.BusListFragment;
import com.imikasa.bus.pojo.BusLine;

import java.util.List;

public class BusListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<BusLine> busLinesList;
    private RecyclerView.ViewHolder holder;
    private int current = 10;

    public BusListAdapter(int resourceId, List<BusLine> busLinesList) {
        this.resourceId = resourceId;
        this.busLinesList = busLinesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resourceId, null);
        holder = new RecyclerView.ViewHolder(inflate) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BusLine busLine = busLinesList.get(position);
        View itemView = holder.itemView;

        TextView lineName = itemView.findViewById(R.id.bus_list_line_name);lineName.setText(busLine.getName());
        TextView lineFirst = itemView.findViewById(R.id.bus_list_line_first);lineFirst.setText(busLine.getFirst());
        TextView lineEnd = itemView.findViewById(R.id.bus_list_line_end);lineEnd.setText(busLine.getEnd());
        TextView start = itemView.findViewById(R.id.bus_list_line_start);start.setText(busLine.getStartTime()+" 始发车");
        TextView over = itemView.findViewById(R.id.bus_list_line_over);over.setText(busLine.getEndTime()+" 末班车");
        TextView pay = itemView.findViewById(R.id.bus_list_line_money);pay.setText("票价:"+String.valueOf(busLine.getPrice()+"元"));
        TextView mileage = itemView.findViewById(R.id.bus_list_line_mileage);mileage.setText("全程"+busLine.getMileage()+"km");
        RecyclerView recyclerView = itemView.findViewById(R.id.bus_list_line_stations);
        LinearLayout linearLayout = itemView.findViewById(R.id.bus_list_jump);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
                new ViewModelProvider(appCompatActivity).get(BusViewModel.class).setBusLineLiveDataDetail(String.valueOf(busLine.getId()));
                new ViewModelProvider(appCompatActivity).get(BusViewModel.class).setBusStopLiveData(String.valueOf(busLine.getId()));
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.bus_container, BusInfoFragment.class,null,"bus-info")
                        .hide(appCompatActivity.getSupportFragmentManager().findFragmentByTag("bus-list"))
                        .commit();
            }
        });
        if(current == position){
            recyclerView.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.GONE);
        }
        BusViewModel busViewModel = new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(BusViewModel.class);
        busViewModel.getBusStopLiveData().observe((LifecycleOwner) itemView.getContext(), busStops -> {
            recyclerView.setAdapter(new BusStopAdapter(R.layout.layout_bus_stop_item,busStops));
        });


        itemView.findViewById(R.id.bus_list_line_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                busViewModel.setBusStopLiveData(String.valueOf(busLine.getId()));
                int lastIndex = current;
                current = position;
                notifyItemChanged(current);
                if (!(lastIndex<0)){
                    notifyItemChanged(lastIndex);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return busLinesList.size();
    }
}
