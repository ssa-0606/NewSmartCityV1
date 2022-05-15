package com.imikasa.movie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.movie.MovieViewModel;

public class MovieDetailFragment extends Fragment {

    private TextView toolText;
    private ImageView imageView;
    private TextView name;
    private TextView longTime;
    private TextView time;
    private TextView score;
    private TextView want;
    private WebView webView;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        MovieViewModel movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        initView(inflate);
        toolText.setText("电影详情");
        movieViewModel.getDetailLiveData().observe(requireActivity(),movieDetail -> {
            Glide.with(getContext()).load("http://124.93.196.45:10001"+movieDetail.getCover()).into(imageView);
            name.setText(movieDetail.getName());
            longTime.setText(movieDetail.getDuration()+"分钟");
            time.setText(movieDetail.getPlayDate()+"上映");
            score.setText(movieDetail.getScore()+"评分");
            want.setText("有"+movieDetail.getLikeNum()+"人想看");
            webView.loadDataWithBaseURL(null,movieDetail.getIntroduction(),"text/html","UTF-8",null);
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolText.setText("看电影");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .hide(getActivity().getSupportFragmentManager().findFragmentByTag("movie-detail"))
                        .show(getActivity().getSupportFragmentManager().findFragmentByTag("movie-main"))
                        .commit();
            }
        });
        return inflate;
    }

    private void initView(View inflate) {
        toolText = getActivity().findViewById(R.id.movie_tool_text);
        imageView = inflate.findViewById(R.id.movie_detail_img);
        name = inflate.findViewById(R.id.movie_detail_name);
        longTime = inflate.findViewById(R.id.movie_detail_long);
        time = inflate.findViewById(R.id.movie_detail_time);
        score = inflate.findViewById(R.id.movie_detail_score);
        want = inflate.findViewById(R.id.movie_detail_want);
        webView = inflate.findViewById(R.id.movie_detail_web);
        btn = inflate.findViewById(R.id.movie_detail_btn);
    }
}
