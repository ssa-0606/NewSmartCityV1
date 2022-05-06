package com.imikasa.bus.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.bus.pojo.BusStop;

import java.util.List;

public class BusStopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<BusStop> busStops;
    private RecyclerView.ViewHolder holder;

    public BusStopAdapter(int resourceId, List<BusStop> busStops) {
        this.resourceId = resourceId;
        this.busStops = busStops;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resourceId, null);
        holder = new RecyclerView.ViewHolder(inflate) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BusStop busStop = busStops.get(position);
        TextView textView = (TextView) holder.itemView.findViewById(R.id.bus_stop_name);
        textView.setText(busStop.getName());
        Log.d("TAG", "onBindViewHolder: "+busStop.getName());
    }

    @Override
    public int getItemCount() {
        return busStops.size();
    }
}
