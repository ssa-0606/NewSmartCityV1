package com.imikasa.activity.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imikasa.R;
import com.imikasa.activity.ActivityViewModel;
import com.imikasa.activity.adapter.ActivityAdapter;
import com.imikasa.activity.adapter.ActivityCommentAdapter;
import com.imikasa.tools.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ActivityDetailFragment extends Fragment {

    private TextView toolText;
    private WebView webView;
    private RecyclerView commentRecycler;
    private Button submit;
    private EditText editText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    ActivityViewModel activityViewModel;
    private RecyclerView recommentRecycler;
    private Button sign;
    private int id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_activity_detail, container, false);
        activityViewModel = new ViewModelProvider(requireActivity()).get(ActivityViewModel.class);
        initView(inflate);
        WebSettings settings = webView.getSettings();
        settings.setTextZoom(300);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        activityViewModel.getDetailMutableLiveData().observe(requireActivity(),activityDetail -> {
            id = activityDetail.getId();
            toolText.setText(activityDetail.getName());
            webView.loadDataWithBaseURL(null,activityDetail.getContent(),"text/html","UTF-8",null);
        });
        activityViewModel.getCommentLiveData().observe(requireActivity(),activityComments -> {
            commentRecycler.setLayoutManager(new GridLayoutManager(getContext(),1));
            commentRecycler.setAdapter(new ActivityCommentAdapter(R.layout.layout_comment,activityComments));
        });
        activityViewModel.getRecommentLiveData().observe(requireActivity(),activityDetails -> {
            recommentRecycler.setLayoutManager(new GridLayoutManager(getContext(),1));
            recommentRecycler.setAdapter(new ActivityAdapter(R.layout.layout_activity_item,activityDetails));
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText.getText())){
                    Toast.makeText(getContext(), "??????????????????", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("activityId",id);
                        jsonObject.put("content",editText.getText());
                        doSubmit(jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(id);
            }
        });

        return inflate;
    }

    private void check(int id) {
        Thread thread = new Thread(()->{
            try {
                String result = MyUtils.GET_T("http://124.93.196.45:10001/prod-api/api/activity/signup/check?activityId=" + id, sharedPreferences.getString("token", "k"));
                int code = new JSONObject(result).getInt("code");
                if(code == 401){
                    getActivity().runOnUiThread(()->{
                        Toast.makeText(getContext(), "????????????", Toast.LENGTH_SHORT).show();
                    });
                }else if(code == 200){
                    boolean isSignup = new JSONObject(result).getBoolean("isSignup");
                    if(isSignup){
                        getActivity().runOnUiThread(()->{
                            Toast.makeText(getContext(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                        });
                    }else{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("activityId",String.valueOf(id));
                        doSign(jsonObject.toString());
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void doSign(String toString) {
        Thread thread = new Thread(()->{
            try {
                String result = MyUtils.POST_T("http://124.93.196.45:10001/prod-api/api/activity/signup", toString, sharedPreferences.getString("token", "k"));
                int code = new JSONObject(result).getInt("code");
                if(code == 200 ){
                    getActivity().runOnUiThread(()-> Toast.makeText(getContext(), "????????????", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void doSubmit(String json) {
        Thread thread = new Thread(()->{
            try {
                String result = MyUtils.POST_T("http://124.93.196.45:10001/prod-api/api/activity/comment", json, sharedPreferences.getString("token", "k"));
                int code = new JSONObject(result).getInt("code");
                if(code == 200){
                    getActivity().runOnUiThread(()->{
                        editText.setText("");
                        Toast.makeText(getContext(), "????????????", Toast.LENGTH_SHORT).show();
                        activityViewModel.setCommentLiveData(id);
                    });
                }else{
                    getActivity().runOnUiThread(()->{
                        editText.setText("");
                        Toast.makeText(getContext(), "????????????", Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void initView(View inflate) {
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
        toolText = getActivity().findViewById(R.id.activity_tool_text);
        webView = inflate.findViewById(R.id.activity_detail_web);
        commentRecycler = inflate.findViewById(R.id.activity_detail_comment_list);
        submit = inflate.findViewById(R.id.activity_detail_btn);
        editText = inflate.findViewById(R.id.activity_detail_comment);
        recommentRecycler = inflate.findViewById(R.id.activity_detail_recommend);
        sign = inflate.findViewById(R.id.activity_detail_sign);
    }
}
