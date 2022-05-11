package com.imikasa.hospital.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.hospital.HospitalViewModel;
import com.imikasa.hospital.fragments.HospitalDetailFragment;
import com.imikasa.hospital.pojo.HospitalInfo;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<HospitalInfo> hospitalInfos;
    private RecyclerView.ViewHolder holder;

    public HospitalAdapter(int resourceId, List<HospitalInfo> hospitalInfos) {
        this.resourceId = resourceId;
        this.hospitalInfos = hospitalInfos;
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
        HospitalInfo hospitalInfo = hospitalInfos.get(position);
        View itemView = holder.itemView;
        ImageView imageView = (ImageView) itemView.findViewById(R.id.hospital_list_img);
        TextView textView = (TextView) itemView.findViewById(R.id.hospital_list_name);
        RatingBar ratingBar = (RatingBar) itemView.findViewById(R.id.hospital_list_level);
        LinearLayout jump = (LinearLayout) itemView.findViewById(R.id.hospital_list_jump);

        Glide.with(itemView).load("http://124.93.196.45:10001"+hospitalInfo.getImgUrl()).into(imageView);
        textView.setText(hospitalInfo.getHospitalName());
        ratingBar.setRating(hospitalInfo.getLevel());

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
                HospitalViewModel hospitalViewModel = new ViewModelProvider(appCompatActivity).get(HospitalViewModel.class);
                hospitalViewModel.setHospitalLunBoListLiveData(hospitalInfo.getId());
                hospitalViewModel.setHospitalInfoMutableLiveData(hospitalInfo.getId());
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.hospital_container, HospitalDetailFragment.class,null,"hospital-detail")
                        .hide(appCompatActivity.getSupportFragmentManager().findFragmentByTag("hospital-main"))
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return hospitalInfos.size();
    }
}
