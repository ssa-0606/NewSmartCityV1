package com.imikasa.part;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.part.fragments.ParkDetailFragment;
import com.imikasa.part.fragments.ParkMainFragment;
import com.imikasa.part.fragments.ParkRecordFragment;

import org.w3c.dom.Text;

import java.util.List;

public class ParkActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(TextUtils.equals(textView.getText(),"停车场")){
                    finish();
                }else if (TextUtils.equals(textView.getText(),"停车场详情")){
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("detail-park"))
                            .show(getSupportFragmentManager().findFragmentByTag("index-park"))
                            .commit();
                    textView.setText("停车场");
                }else if (TextUtils.equals(textView.getText(),"停车记录")){
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("record-park"))
                            .show(getSupportFragmentManager().findFragmentByTag("index-park"))
                            .commit();
                    textView.setText("停车场");
                }
                break;
            case R.id.park_record:
                getSupportFragmentManager().beginTransaction()
                        .hide(getSupportFragmentManager().findFragmentByTag("index-park"))
                        .add(R.id.park_container, ParkRecordFragment.class,null,"record-park")
                        .commit();
                textView.setText("停车记录");
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_park,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        textView = findViewById(R.id.park_tool_text);
        toolbar = (Toolbar) findViewById(R.id.park_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.park_container, ParkMainFragment.class,null,"index-park")
                .commit();

    }
}