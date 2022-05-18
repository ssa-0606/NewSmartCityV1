package com.imikasa.movie.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.movie.MovieViewModel;
import com.imikasa.movie.adapter.MovieAdapter;
import com.imikasa.movie.pojo.MovieDetail;
import com.imikasa.movie.pojo.MovieLunBo;

import java.util.ArrayList;
import java.util.List;

public class MovieMainFragment extends Fragment {
    private MovieViewModel viewModel;                                                                                                                                                                                                 
    private TextView toolText;
    private ViewFlipper vf ;
    private RecyclerView recyclerView;
    private Button btn;
    private EditText editText;
    private Button search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_movie_main, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
        initView(inflate);
        toolText.setText("看电影");
        viewModel.getLunBoLiveData().observe(requireActivity(),movieLunBos -> {
            for (MovieLunBo movieLunBo : movieLunBos) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getContext()).load("http://124.93.196.45:10001"+movieLunBo.getAdvImg()).into(imageView);
                vf.addView(imageView);
            }
        });
        viewModel.getListDetailLiveData().observe(requireActivity(),movieDetails -> {
            List<MovieDetail> show = new ArrayList<>();
            if(movieDetails.size()>=5){
                for (int i = 0; i < 5; i++) {
                    show.add(movieDetails.get(i));
                }
            }else{
                for (MovieDetail movieDetail : movieDetails) {
                    show.add(movieDetail);
                }
            }

            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new MovieAdapter(R.layout.layout_movie_item,show));
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setVisibility(View.GONE);
                Toast.makeText(getContext(), "已加载全部", Toast.LENGTH_SHORT).show();
                viewModel.getListDetailLiveData().observe(requireActivity(),movieDetailList -> {
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                    recyclerView.setAdapter(new MovieAdapter(R.layout.layout_movie_item,movieDetailList));
                });
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText.getText())){
                    Toast.makeText(getContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    String val = editText.getText().toString();
                    viewModel.setListDetailLiveData(val);
                }
            }
        });
        return inflate;
    }

    private void initView(View inflate) {
        toolText = getActivity().findViewById(R.id.movie_tool_text);
        vf = inflate.findViewById(R.id.movie_vf);
        recyclerView = inflate.findViewById(R.id.movie_recycler);
        btn = inflate.findViewById(R.id.movie_show);
        editText = inflate.findViewById(R.id.movie_search);
        search = inflate.findViewById(R.id.movie_search_btn);
    }
}
