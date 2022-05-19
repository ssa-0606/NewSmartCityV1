package com.imikasa.ui.home.adapter;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
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
import com.imikasa.ui.home.HomeViewModel;
import com.imikasa.ui.home.NewsDetailActivity;
import com.imikasa.ui.home.pojo.MainNewsItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int resourceId;
    private List<MainNewsItem> newsItemList;

    private RecyclerView.ViewHolder holder;

    public NewsAdapter(int resourceId, List<MainNewsItem> newsItemList) {
        this.resourceId = resourceId;
        this.newsItemList = newsItemList;
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
        View itemView = holder.itemView;
        TextView title = (TextView) itemView.findViewById(R.id.main_news_title);
        TextView content = (TextView) itemView.findViewById(R.id.main_news_content);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.main_news_img);
        TextView comment = (TextView) itemView.findViewById(R.id.main_news_comment);
        TextView publish = (TextView) itemView.findViewById(R.id.main_news_publish);
        LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.main_news_jump);

        MainNewsItem newsItem = newsItemList.get(position);

        title.setText(newsItem.getTitle());
//        Log.d("TAG1111", newsItem.getContent());
        CharSequence replace = TextUtils.replace(newsItem.getContent(), new String[]{"&nbsp;"," "}, new String[]{"",""});
        content.setText(Html.fromHtml(replace.toString()));
        Glide.with(itemView).load("http://124.93.196.45:10001"+newsItem.getCover()).into(imageView);
        comment.setText(String.valueOf(newsItem.getCommentNum()));
        publish.setText(newsItem.getPublishDate());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), NewsDetailActivity.class);
                intent.putExtra("id",newsItem.getId());
                itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }
}
