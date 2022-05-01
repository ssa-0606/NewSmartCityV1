package com.imikasa.ui.usercenter;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imikasa.R;
import com.imikasa.ui.notifications.NotificationsViewModel;

public class UserCenterFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UserCenterViewModel userCenterViewModel =
                new ViewModelProvider(this).get(UserCenterViewModel.class);

        View inflate = inflater.inflate(R.layout.user_center_fragment, container, false);




        return inflate;
    }



}