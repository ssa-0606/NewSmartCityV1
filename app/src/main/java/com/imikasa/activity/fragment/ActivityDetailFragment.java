package com.imikasa.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imikasa.R;
import com.imikasa.activity.ActivityViewModel;

public class ActivityDetailFragment extends Fragment {

    private TextView toolText;
    private WebView webView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_activity_detail, container, false);
        ActivityViewModel activityViewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
        initView(inflate);
        WebSettings settings = webView.getSettings();
        settings.setTextZoom(300);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        activityViewModel.getDetailMutableLiveData().observe(requireActivity(),activityDetail -> {
            toolText.setText(activityDetail.getName());
            webView.loadDataWithBaseURL(null,activityDetail.getContent(),"text/html","UTF-8",null);
        });


        return inflate;
    }

    private void initView(View inflate) {

        toolText = getActivity().findViewById(R.id.activity_tool_text);
        webView = inflate.findViewById(R.id.activity_detail_web);

    }
}
