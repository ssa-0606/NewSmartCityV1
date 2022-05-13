package com.imikasa.metro.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.imikasa.R;
import com.imikasa.metro.pojo.LineStation;

import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<LineStation> lines;
    private String runStationName;
    private RecyclerView.ViewHolder holder;


    public LineAdapter(int resourceId, List<LineStation> lines,String runStationName) {
        this.resourceId = resourceId;
        this.lines = lines;
        this.runStationName = runStationName;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent,false);
        holder = new RecyclerView.ViewHolder(inflate) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LineStation line = lines.get(position);
        View itemView = holder.itemView;

        TextView textView = itemView.findViewById(R.id.line_station);
        textView.setText(line.getName());

        if(TextUtils.equals(line.getName(),runStationName)){
            itemView.findViewById(R.id.line_img).setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }
}
