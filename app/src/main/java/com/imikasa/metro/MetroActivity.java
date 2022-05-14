package com.imikasa.metro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.metro.fragments.MetroLineFragment;
import com.imikasa.metro.fragments.MetroPageFragment;

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
                }else if (TextUtils.equals(textView.getText(),"地铁总览图")){
                    textView.setText("城市地铁");
                    getSupportFragmentManager().beginTransaction()
                            .show(getSupportFragmentManager().findFragmentByTag("metro-lines"))
                            .hide(getSupportFragmentManager().findFragmentByTag("metro-page"))
                            .commit();
                }
                break;
            case R.id.metro_menu:
                textView.setText("地铁总览图");
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.metro_contain, MetroPageFragment.class,null,"metro-page")
                        .hide(getSupportFragmentManager().findFragmentByTag("metro-lines"))
                        .commit();

                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_metro,menu);
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