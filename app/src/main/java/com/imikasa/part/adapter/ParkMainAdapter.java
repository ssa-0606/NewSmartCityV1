package com.imikasa.part.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.part.ParkViewModel;
import com.imikasa.part.fragments.ParkDetailFragment;
import com.imikasa.part.pojo.Park;

import java.util.List;

public class ParkMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<Park> parkList;
    private RecyclerView.ViewHolder holder;

    public ParkMainAdapter(int resourceId, List<Park> parkList) {
        this.resourceId = resourceId;
        this.parkList = parkList;
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
        Park park = parkList.get(position);
        View itemView = holder.itemView;
        TextView name = itemView.findViewById(R.id.park_main_name);
        TextView distance = itemView.findViewById(R.id.park_main_distance);
        TextView vacancy = itemView.findViewById(R.id.park_main_vacancy);
        TextView address = itemView.findViewById(R.id.park_main_address);
        TextView rates = itemView.findViewById(R.id.park_main_rates);
        LinearLayout layout = itemView.findViewById(R.id.park_main_jump);

        name.setText(park.getParkName());
        distance.setText(park.getDistance()+"km");
        vacancy.setText("空余泊位:"+park.getVacancy());
        address.setText(park.getAddress());
        rates.setText("收费标准:"+park.getVacancy());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ViewModelProvider((ViewModelStoreOwner) itemView.getContext()).get(ParkViewModel.class).setParkLive(park);
                AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.park_container, ParkDetailFragment.class,null,"detail-park")
                        .hide(appCompatActivity.getSupportFragmentManager().findFragmentByTag("index-park"))
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return parkList.size();
    }
}