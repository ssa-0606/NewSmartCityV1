package com.imikasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GruidActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private RadioGroup radioGroup;
    private RadioButton btn2;
    private RadioButton btn3;
    private RadioButton btn4;
    private RadioButton btn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gruid);

        initView();


    }

    private void initView() {

    }
}