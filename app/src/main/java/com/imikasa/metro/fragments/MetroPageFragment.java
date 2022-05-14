package com.imikasa.metro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imikasa.R;
import com.imikasa.metro.MetroViewModel;
import com.imikasa.metro.adapters.LinePageAdapter;
import com.imikasa.tools.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MetroPageFragment extends Fragment {

    private TextView toolText;
    private RecyclerView recyclerView;
    private ImageView imageView;


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tool_metro,menu);
        menu.clear();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_metro_page, container, false);
        setHasOptionsMenu(true);
        initView(inflate);
        MetroViewModel metroViewModel = new ViewModelProvider(requireActivity()).get(MetroViewModel.class);
        metroViewModel.getListLineMutableLiveData().observe(requireActivity(),lines -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new LinePageAdapter(R.layout.layout_metro_page_item,lines));
        });

        getImg();

        return inflate;
    }

    private void getImg() {
        Thread thread = new Thread(()->{
            try {
                String get = MyUtils.GET("http://124.93.196.45:10001/prod-api/api/metro/city");
                String string = new JSONObject(get).getJSONObject("data").getString("imgUrl");
                getActivity().runOnUiThread(()->{
                    Glide.with(getContext()).load("http://124.93.196.45:10001"+string).into(imageView);
                });
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void initView(View inflate) {
        toolText = getActivity().findViewById(R.id.metro_tool_text);
        recyclerView = inflate.findViewById(R.id.metro_page_left);
        imageView = inflate.findViewById(R.id.metro_page_img);
    }
}
