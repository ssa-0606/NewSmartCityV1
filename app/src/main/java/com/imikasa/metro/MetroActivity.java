package com.imikasa.metro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.metro.fragments.MetroLineFragment;

public class MetroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        Toolbar toolbar = findViewById(R.id.metro_tool);
        TextView textView = findViewById(R.id.metro_tool_text);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.metro_contain, MetroLineFragment.class,null,"metro-lines")
                .commit();


    }
}