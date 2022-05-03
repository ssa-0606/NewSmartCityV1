package com.imikasa.ui.usercenter;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.imikasa.MainActivity;
import com.imikasa.R;
import com.imikasa.ui.notifications.NotificationsViewModel;
import com.imikasa.ui.usercenter.activity.ChangePwdActivity;
import com.imikasa.ui.usercenter.activity.FeedActivity;
import com.imikasa.ui.usercenter.activity.LogInActivity;
import com.imikasa.ui.usercenter.activity.UserInfoActivity;
import com.imikasa.ui.usercenter.pojo.UserInfo;

public class UserCenterFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView nickName;
    private TextView userId;

    private LinearLayout userInfo;
    private LinearLayout userOrder;
    private LinearLayout userChange;
    private LinearLayout userFeed;
    private Button btn;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UserCenterViewModel userCenterViewModel =
                new ViewModelProvider(this).get(UserCenterViewModel.class);

        View inflate = inflater.inflate(R.layout.user_center_fragment, container, false);
        init(inflate);
        userCenterViewModel.getUserInfoLiveData().observe(requireActivity(),userInfo -> {
            Log.d("TAG000", "onCreateView: "+userInfo);
            verifyUser(userInfo);
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(nickName.getText(),"点击登录")){
                    Intent intent = new Intent(getActivity(), LogInActivity.class);
                    getActivity().startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "您已经登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(nickName.getText(),"点击登录")){
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
        userChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(nickName.getText(),"点击登录")){
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), ChangePwdActivity.class);
                    startActivity(intent);
                }
            }
        });
        userFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(nickName.getText(),"点击登录")){
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), FeedActivity.class);
                    startActivity(intent);
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return inflate;
    }

    private void verifyUser(UserInfo userInfo) {

        if(userInfo == null){
            nickName.setText("点击登录");
        }else{
            nickName.setText(userInfo.getNickName());
            userId.setText("智慧城市号:"+userInfo.getUserId());
//            Glide.with(getContext()).load("http://124.93.196.45:10001"+userInfo.getAvatar()).into(imageView);
            Glide.with(getContext()).load(userInfo.getAvatar()).into(imageView);
        }

    }
    //初始化
    private void init(View view) {
        sharedPreferences = getActivity().getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
        linearLayout = (LinearLayout) view.findViewById(R.id.user_center_layout);
        imageView = (ImageView) view.findViewById(R.id.user_center_avatar);
        nickName = (TextView) view.findViewById(R.id.user_center_nick);
        userId = (TextView) view.findViewById(R.id.user_center_userId);
        userInfo = (LinearLayout) view.findViewById(R.id.user_center_info);
        userOrder = (LinearLayout) view.findViewById(R.id.user_center_order);
        userChange = (LinearLayout) view.findViewById(R.id.user_center_change);
        userFeed = (LinearLayout) view.findViewById(R.id.user_center_feed);
        btn = (Button) view.findViewById(R.id.user_center_btn);
    }


}