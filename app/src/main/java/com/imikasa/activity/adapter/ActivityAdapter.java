package com.imikasa.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.activity.ActivityViewModel;
import com.imikasa.activity.fragment.ActivityDetailFragment;
import com.imikasa.activity.pojo.ActivityDetail;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<ActivityDetail> details;
    private RecyclerView.ViewHolder holder;

    public ActivityAdapter(int resourceId, List<ActivityDetail> details) {
        this.resourceId = resourceId;
        this.details = details;
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
        ActivityDetail activityDetail = details.get(position);
        View itemView = holder.itemView;
        TextView name = itemView.findViewById(R.id.activity_list_name);
        ImageView img = itemView.findViewById(R.id.activity_list_img);
        TextView sign = itemView.findViewById(R.id.activity_list_sign);
        TextView publish = itemView.findViewById(R.id.activity_list_publish);
        LinearLayout layout = itemView.findViewById(R.id.activity_jump);

        name.setText(activityDetail.getName());
        Glide.with(itemView).load("http://124.93.196.45:10001"+activityDetail.getImgUrl()).into(img);
        sign.setText("已报名"+activityDetail.getSignupNum()+"人");
        publish.setText(activityDetail.getPublishTime());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
                ActivityViewModel activityViewModel = new ViewModelProvider(appCompatActivity).get(ActivityViewModel.class);
                activityViewModel.setDetailMutableLiveData(activityDetail.getId());
                activityViewModel.setCommentLiveData(activityDetail.getId());
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.activity_contain, ActivityDetailFragment.class,null,"activity-detail")
                        .hide(appCompatActivity.getSupportFragmentManager().findFragmentByTag("activity-main"))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
}
