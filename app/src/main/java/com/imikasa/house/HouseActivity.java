package com.imikasa.house;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.house.fragments.HouseFragment;

public class HouseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(TextUtils.equals(textView.getText(),"找房子")){
                    finish();
                }

                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.house_container, HouseFragment.class,null,"house-main")
                .commit();

    }

    private void initView() {

        toolbar = findViewById(R.id.house_tool);
        textView = findViewById(R.id.house_tool_text);

    }
}