package com.imikasa.ui.usercenter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.imikasa.R;

public class FeedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editText;
    private TextView textView;
    private Button btn;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ininView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(charSequence.length()>150){
                    editText.setText(TextUtils.substring(charSequence,0,149));
                    editText.setSelection(149);
                   Toast.makeText(FeedActivity.this, "输入已满", Toast.LENGTH_SHORT).show();
               }
                textView.setText("已经输入"+charSequence.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                Toast.makeText(FeedActivity.this, "提交成功，我们会认真考虑您的意见", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void ininView() {
        toolbar =(Toolbar) findViewById(R.id.user_feed_tool);
        editText = (EditText) findViewById(R.id.feed_edit);
        textView = (TextView) findViewById(R.id.feed_text);
        btn = (Button) findViewById(R.id.feed_btn);
    }
}