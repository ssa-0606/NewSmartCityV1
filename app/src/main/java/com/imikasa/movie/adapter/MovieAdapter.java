package com.imikasa.movie.adapter;

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
import com.imikasa.movie.MovieViewModel;
import com.imikasa.movie.fragments.MovieDetailFragment;
import com.imikasa.movie.pojo.MovieDetail;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<MovieDetail> details;
    private RecyclerView.ViewHolder holder;

    public MovieAdapter(int resourceId, List<MovieDetail> details) {
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
        MovieDetail detail = details.get(position);
        View itemView = holder.itemView;
        ImageView imageView = itemView.findViewById(R.id.movie_img);
        TextView name = itemView.findViewById(R.id.movie_name);
        TextView longTime = itemView.findViewById(R.id.movie_long);
        TextView time = itemView.findViewById(R.id.movie_time);
        LinearLayout jump = itemView.findViewById(R.id.movie_jump);
        Glide.with(itemView.getContext()).load("http://124.93.196.45:10001"+detail.getCover()).into(imageView);
        name.setText(detail.getName());
        longTime.setText(detail.getDuration()+"分钟");
        time.setText("上映时间:"+detail.getPlayDate());
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
                MovieViewModel movieViewModel = new ViewModelProvider(appCompatActivity).get(MovieViewModel.class);
                movieViewModel.setDetailLiveData(detail.getId());
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.movie_contain, MovieDetailFragment.class,null,"movie-detail")
                        .hide(appCompatActivity.getSupportFragmentManager().findFragmentByTag("movie-main"))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
}
