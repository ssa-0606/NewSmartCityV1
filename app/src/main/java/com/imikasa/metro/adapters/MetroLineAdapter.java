package com.imikasa.metro.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.metro.MetroViewModel;
import com.imikasa.metro.fragments.MetroDetailActivity;
import com.imikasa.metro.pojo.MetroLine;

import java.util.List;

public class MetroLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<MetroLine> metroLines;
    private RecyclerView.ViewHolder holder;

    public MetroLineAdapter(int resourceId, List<MetroLine> metroLines) {
        this.resourceId = resourceId;
        this.metroLines = metroLines;
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
        MetroLine metroLines = this.metroLines.get(position);
        View itemView = holder.itemView;

        TextView lineName = (TextView) itemView.findViewById(R.id.metro_line_name);
        TextView nextStep = (TextView) itemView.findViewById(R.id.metro_line_next);
        TextView reachTime = (TextView) itemView.findViewById(R.id.metro_line_reach);
        LinearLayout linearLayout = (LinearLayout) itemView.findViewById(R.id.metro_to_detail);

        lineName.setText(metroLines.getLineName());
        nextStep.setText(metroLines.getNextStep());
        reachTime.setText(String.valueOf(metroLines.getReachTime()));
        linearLayout.setOnClickListener(view -> {
            AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
            new ViewModelProvider(appCompatActivity).get(MetroViewModel.class).setMetroStationMutableLiveData(metroLines.getLineId());
            appCompatActivity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.metro_contain, MetroDetailActivity.class,null,"metro-detail")
                    .hide(appCompatActivity.getSupportFragmentManager().findFragmentByTag("metro-lines"))
                    .commit();
        });
        
    }

    @Override
    public int getItemCount() {
        return metroLines.size();
    }
}
