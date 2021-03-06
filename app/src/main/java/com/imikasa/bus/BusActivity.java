package com.imikasa.bus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.bus.fragments.BusListFragment;

public class BusActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(TextUtils.equals(textView.getText(),"智慧巴士")){
                    finish();
                }else if (TextUtils.equals(textView.getText(),"班车信息")){
                    textView.setText("智慧巴士");
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("bus-info"))
                            .show(getSupportFragmentManager().findFragmentByTag("bus-list"))
                            .commit();
                }else if (TextUtils.equals(textView.getText(),"乘车日期")){
                    textView.setText("班车信息");
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("bus-data"))
                            .show(getSupportFragmentManager().findFragmentByTag("bus-info"))
                            .commit();
                }else if(TextUtils.equals(textView.getText(),"用户信息")){
                    textView.setText("乘车日期");
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("bus-user"))
                            .show(getSupportFragmentManager().findFragmentByTag("bus-data"))
                            .commit();
                }else if(TextUtils.equals(textView.getText(),"提交订单")){
                    textView.setText("用户信息");
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("bus-commit"))
                            .show(getSupportFragmentManager().findFragmentByTag("bus-user"))
                            .commit();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        toolbar = (Toolbar) findViewById(R.id.bus_tool);
        textView = (TextView) findViewById(R.id.bus_tool_text);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.bus_container,BusListFragment.class,null,"bus-list")
                .commit();
    }
}