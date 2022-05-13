package com.imikasa.metro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.metro.fragments.MetroLineFragment;

public class MetroActivity extends AppCompatActivity {
    private  TextView textView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(TextUtils.equals(textView.getText(),"城市地铁")){
                    finish();
                }else if (TextUtils.equals(textView.getText(),"2号线")||TextUtils.equals(textView.getText(),"1号线")){
                    textView.setText("城市地铁");
                    getSupportFragmentManager().beginTransaction()
                            .show(getSupportFragmentManager().findFragmentByTag("metro-lines"))
                            .hide(getSupportFragmentManager().findFragmentByTag("metro-detail"))
                            .commit();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        Toolbar toolbar = findViewById(R.id.metro_tool);
        textView = findViewById(R.id.metro_tool_text);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.metro_contain, MetroLineFragment.class,null,"metro-lines")
                .commit();


    }
}