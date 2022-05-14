package com.imikasa.activity.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.activity.pojo.ActivityComment;

import java.util.List;

public class ActivityCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<ActivityComment> activityComments;
    private RecyclerView.ViewHolder holder;

    public ActivityCommentAdapter(int resourceId, List<ActivityComment> activityComments) {
        this.resourceId = resourceId;
        this.activityComments = activityComments;
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
        ActivityComment activityComment = activityComments.get(position);
        View itemView = holder.itemView;
        ImageView imageView = itemView.findViewById(R.id.comment_avatar);
        TextView nick = itemView.findViewById(R.id.comment_nick);
        TextView time = itemView.findViewById(R.id.comment_time);
        TextView content = itemView.findViewById(R.id.comment_content);

        if (activityComment.getAvatar().contains("http")){
            Glide.with(itemView).load(activityComment.getAvatar()).into(imageView);
        }else{
            Glide.with(itemView).load("http://124.93.196.45:10001"+activityComment.getAvatar()).into(imageView);
        }

        nick.setText(activityComment.getNickName());
        time.setText(activityComment.getCommentTime());
        content.setText(activityComment.getContent());
    }

    @Override
    public int getItemCount() {
        return activityComments.size();
    }
}
