package com.imikasa.metro.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.metro.pojo.Line;

import java.util.List;
import java.util.Random;

public class LinePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<Line> lines;
    private RecyclerView.ViewHolder holder;

    public LinePageAdapter(int resourceId, List<Line> lines) {
        this.resourceId = resourceId;
        this.lines = lines;
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
        Line line = lines.get(position);
        View itemView = holder.itemView;
        TextView textView = itemView.findViewById(R.id.metro_page_text);
        Random random = new Random();
        textView.setText(line.getLineName());
        textView.setTextColor(Color.rgb(random.nextInt(150),random.nextInt(150),random.nextInt(150)));
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }
}
