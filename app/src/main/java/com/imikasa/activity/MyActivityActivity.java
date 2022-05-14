package com.imikasa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.activity.fragment.ActivityMainFragment;

import java.util.ArrayList;
import java.util.List;

public class MyActivityActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;
    private String name;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(TextUtils.equals(textView.getText(),"活动管理")){
                    finish();
                }else if (TextUtils.equals(textView.getText(),name)){
                    textView.setText("活动管理");
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("activity-detail"))
                            .show(getSupportFragmentManager().findFragmentByTag("activity-main"))
                            .commit();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);

        toolbar = findViewById(R.id.activity_tool);
        textView = findViewById(R.id.activity_tool_text);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActivityViewModel activityViewModel = new ViewModelProvider(this).get(ActivityViewModel.class);
        activityViewModel.getDetailMutableLiveData().observe(this,activityDetail -> {
            name = activityDetail.getName();
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_contain, ActivityMainFragment.class,null,"activity-main")
                .commit();

    }
}