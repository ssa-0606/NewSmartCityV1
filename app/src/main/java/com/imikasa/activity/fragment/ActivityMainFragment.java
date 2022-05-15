package com.imikasa.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.imikasa.R;
import com.imikasa.activity.ActivityViewModel;
import com.imikasa.activity.adapter.ActivityAdapter;
import com.imikasa.activity.pojo.ActivityCategory;
import com.imikasa.activity.pojo.ActivityLunBo;

public class ActivityMainFragment extends Fragment {

    private TextView toolText;
    private ViewFlipper vf;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_activity_main, container, false);
        ActivityViewModel activityViewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
        initView(inflate);
        toolText.setText("活动管理");


        activityViewModel.getLunBoLiveData().observe(requireActivity(),activityLunBos -> {
            for (ActivityLunBo activityLunBo : activityLunBos) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activityViewModel.setCommentLiveData(activityLunBo.getTargetId());
                        activityViewModel.setDetailMutableLiveData(activityLunBo.getTargetId());
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .add(R.id.activity_contain,ActivityDetailFragment.class,null,"activity-detail")
                                .hide(getActivity().getSupportFragmentManager().findFragmentByTag("activity-main"))
                                .commit();
                    }
                });
                Glide.with(getContext()).load("http://124.93.196.45:10001"+activityLunBo.getAdvImg()).into(imageView);
                vf.addView(imageView);
            }
        });
        activityViewModel.getCategoryLiveData().observe(requireActivity(),activityCategories -> {
            for (ActivityCategory activityCategory : activityCategories) {
                tabLayout.addTab(tabLayout.newTab().setText(activityCategory.getName()).setTag(activityCategory.getId()));
            }
        });

        activityViewModel.getDetailLiveData().observe(requireActivity(),activityDetails -> {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            recyclerView.setAdapter(new ActivityAdapter(R.layout.layout_activity_item,activityDetails));
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activityViewModel.setDetailLiveData((Integer) tab.getTag());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return inflate;
    }

    private void initView(View inflate) {

        toolText = getActivity().findViewById(R.id.activity_tool_text);
        vf = inflate.findViewById(R.id.activity_vf);
        tabLayout = inflate.findViewById(R.id.activity_tab);
        recyclerView = inflate.findViewById(R.id.activity_recycler);

    }
}
