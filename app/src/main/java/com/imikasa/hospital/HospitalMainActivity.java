package com.imikasa.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.hospital.fragments.HospitalFragment;

public class HospitalMainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(TextUtils.equals(textView.getText(),"医院")){
                    finish();
                }else if (TextUtils.equals(textView.getText(),"医院简介")){
                    textView.setText("医院");
                    getSupportFragmentManager().beginTransaction()
                            .show(getSupportFragmentManager().findFragmentByTag("hospital-main"))
                            .hide(getSupportFragmentManager().findFragmentByTag("hospital-detail"))
                            .commit();
                }else if (TextUtils.equals(textView.getText(),"就诊人卡片")){
                    textView.setText("医院简介");
                    getSupportFragmentManager().beginTransaction()
                            .show(getSupportFragmentManager().findFragmentByTag("hospital-detail"))
                            .hide(getSupportFragmentManager().findFragmentByTag("patient-show"))
                            .commit();
                }else if(TextUtils.equals(textView.getText(),"添加就诊人")){
                    textView.setText("就诊人卡片");
                    getSupportFragmentManager().beginTransaction()
                            .show(getSupportFragmentManager().findFragmentByTag("patient-show"))
                            .hide(getSupportFragmentManager().findFragmentByTag("patient-add"))
                            .commit();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_main);

        toolbar = findViewById(R.id.hospital_tool);
        textView = findViewById(R.id.hospital_tool_text);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.hospital_container, HospitalFragment.class,null,"hospital-main")
                .commit();

    }
}