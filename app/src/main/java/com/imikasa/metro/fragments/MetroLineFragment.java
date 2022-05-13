package com.imikasa.metro.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imikasa.R;
import com.imikasa.metro.MetroViewModel;

public class MetroLineFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_metro_line, container, false);
        MetroViewModel metroViewModel = new ViewModelProvider(requireActivity()).get(MetroViewModel.class);
        metroViewModel.getListMetroLineLiveData().observe(requireActivity(),metroLines -> {
            Log.d("TAG", "onCreateView: test"+metroLines.get(0));
        });


        return inflate;
    }
}
