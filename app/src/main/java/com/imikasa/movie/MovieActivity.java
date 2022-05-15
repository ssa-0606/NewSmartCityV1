package com.imikasa.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.imikasa.R;
import com.imikasa.movie.fragments.MovieMainFragment;

import org.w3c.dom.Text;

public class MovieActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(TextUtils.equals(textView.getText(),"看电影")){
                    finish();
                }else if(TextUtils.equals(textView.getText(),"电影详情")){
                    textView.setText("看电影");
                    getSupportFragmentManager().beginTransaction()
                            .hide(getSupportFragmentManager().findFragmentByTag("movie-detail"))
                            .show(getSupportFragmentManager().findFragmentByTag("movie-main"))
                            .commit();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.movie_contain, MovieMainFragment.class,null,"movie-main")
                .commit();



    }

    private void initView() {
        toolbar = findViewById(R.id.movie_tool);
        textView = findViewById(R.id.movie_tool_text);
    }
}