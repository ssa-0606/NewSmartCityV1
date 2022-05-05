package com.imikasa.part.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.part.pojo.ParkRecord;

import java.util.List;

public class ParkRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<ParkRecord> parkRecords;
    private RecyclerView.ViewHolder holder;

    public ParkRecordAdapter(int resourceId, List<ParkRecord> parkRecords) {
        this.resourceId = resourceId;
        this.parkRecords = parkRecords;
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
        ParkRecord record = parkRecords.get(position);
        View itemView = holder.itemView;
        TextView plateNum = (TextView) itemView.findViewById(R.id.park_record_plate);
        TextView entry = (TextView) itemView.findViewById(R.id.park_record_entry);
        TextView out = (TextView) itemView.findViewById(R.id.park_out_record);
        TextView parkName = (TextView) itemView.findViewById(R.id.park_record_name);
        TextView pay = (TextView) itemView.findViewById(R.id.park_record_pay);

        plateNum.setText(record.getPlateNumber());
        entry.setText(record.getEntryTime());
        out.setText(record.getOutTime());
        parkName.setText(record.getParkName());
        pay.setText("消费"+record.getMonetary()+"元");
    }

    @Override
    public int getItemCount() {
        return parkRecords.size();
    }
}
