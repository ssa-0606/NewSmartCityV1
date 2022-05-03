package com.imikasa.ui.home.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.MainActivity;
import com.imikasa.R;
import com.imikasa.part.ParkActivity;
import com.imikasa.ui.home.pojo.MainService;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<MainService> serviceList;
    private RecyclerView.ViewHolder holder;

    public ServiceAdapter(int resourceId, List<MainService> serviceList) {
        this.resourceId = resourceId;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceId, null);
        holder = new RecyclerView.ViewHolder(view) {};
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;
        ImageView imageView = (ImageView) itemView.findViewById(R.id.main_service_img);
        TextView textView = (TextView) itemView.findViewById(R.id.main_service_name);

        if (TextUtils.equals(serviceList.get(position).getServiceName(),"全部服务")){
            imageView.setImageResource(R.drawable.ic_dashboard_black_24dp);
        }else {
            Glide.with(itemView).load("http://124.93.196.45:10001"+serviceList.get(position).getImgUrl()).into(imageView);
        }
        textView.setText(serviceList.get(position).getServiceName());

        imageView.setOnClickListener(view -> {
            if(TextUtils.equals(serviceList.get(position).getServiceName(),"全部服务")){
                NavController navController = Navigation.findNavController(itemView);
                navController.navigate(R.id.action_navigation_home_to_navigation_dashboard);
            }else if(TextUtils.equals(serviceList.get(position).getServiceName(),"停哪儿")){
                Intent intent = new Intent(itemView.getContext(), ParkActivity.class);
                itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }
}
