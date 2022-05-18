package com.imikasa.house.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.house.pojo.House;

import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<House> houses;
    private RecyclerView.ViewHolder holder;

    public HouseAdapter(int resourceId, List<House> houses) {
        this.resourceId = resourceId;
        this.houses = houses;
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
        House house = houses.get(position);

        View itemView = holder.itemView;
        ImageView img = itemView.findViewById(R.id.house_pic);
        TextView name = itemView.findViewById(R.id.house_name);
        TextView size = itemView.findViewById(R.id.house_size);
        TextView price = itemView.findViewById(R.id.house_price);
        TextView desc = itemView.findViewById(R.id.house_desc);
        Glide.with(itemView).load("http://124.93.196.45:10001"+house.getPic()).into(img);
        name.setText(house.getSourceName().replace("\n",""));
        size.setText("面积："+house.getAreaSize()+"平");
        price.setText("价格："+house.getPrice());
        desc.setText(house.getDescription());
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }
}
