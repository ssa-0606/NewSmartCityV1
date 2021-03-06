package com.imikasa.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.imikasa.R;
import com.imikasa.databinding.FragmentHomeBinding;
import com.imikasa.ui.home.adapter.NewsAdapter;
import com.imikasa.ui.home.adapter.ServiceAdapter;
import com.imikasa.ui.home.pojo.MainLunBo;
import com.imikasa.ui.home.pojo.MainNewsCategory;
import com.imikasa.ui.home.pojo.MainService;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewFlipper vf ;
    private RecyclerView serviceRecycler;
    private TabLayout tabLayout;
    private RecyclerView newsRecycler;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取viewModel
        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        //加载fragment布局
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        //实例化各个组件
        initView(inflate);



        //TabLayout 的 tab 中的监听事件 ，
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                homeViewModel.setNewsItems((Integer) tab.getTag());
//                Log.d("TAG3333", "onTabSelected: "+tab.getTag());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        //通过liveData中的数据渲染组件
        //渲染轮播
        homeViewModel.getLunBo().observe(requireActivity(), mainLunBos -> {
            for (MainLunBo mainLunBo : mainLunBos) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(getActivity()).load("http://124.93.196.45:10001"+mainLunBo.getAdvImg()).into(imageView);
                vf.addView(imageView);
            }
        });
        //渲染服务
        homeViewModel.getService().observe(requireActivity(),mainServices -> {
            List<MainService> show = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                show.add(mainServices.get(i));
            }
            show.add(new MainService(0,"全部服务","全部服务","",0));
            serviceRecycler.setLayoutManager(new GridLayoutManager(getContext(),5));
            serviceRecycler.setAdapter(new ServiceAdapter(R.layout.layout_main_service,show));
        });
        //渲染新闻
        homeViewModel.getNewsCategory().observe(requireActivity(),mainNewsCategories -> {
            for (MainNewsCategory mainNewsCategory : mainNewsCategories) {
                tabLayout.addTab(tabLayout.newTab().setText(mainNewsCategory.getName()).setTag(mainNewsCategory.getId()));
            }
//            newsRecycler.setLayoutManager(new GridLayoutManager(getContext(),1));
//            newsRecycler.setAdapter(new NewsAdapter(R.layout.layout_main_news,mainNewsCategories.get(0).getNewsItemList()));
        });

        homeViewModel.getNewsItem().observe(requireActivity(),mainNewsItems -> {
            newsRecycler.setLayoutManager(new GridLayoutManager(getContext(),1));
            newsRecycler.setAdapter(new NewsAdapter(R.layout.layout_main_news,mainNewsItems));
        });


        return inflate;
    }

    private void initView(View view) {
        vf = (ViewFlipper) view.findViewById(R.id.main_fv);
        serviceRecycler = (RecyclerView) view.findViewById(R.id.main_service);
        tabLayout = (TabLayout) view.findViewById(R.id.main_tab);
        newsRecycler = (RecyclerView) view.findViewById(R.id.main_news);
    }

}