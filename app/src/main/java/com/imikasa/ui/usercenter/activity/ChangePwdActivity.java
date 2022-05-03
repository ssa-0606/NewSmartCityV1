package com.imikasa.ui.usercenter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imikasa.R;
import com.imikasa.tools.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ChangePwdActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText oldPwd;
    private EditText newPwd;
    private Button btn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chage_pwd);

        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(oldPwd.getText().toString().trim())||TextUtils.isEmpty(newPwd.getText().toString().trim())){
                    Toast.makeText(ChangePwdActivity.this, "输入不得为空", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("oldPassword",oldPwd.getText().toString().trim());
                        jsonObject.put("newPassword",newPwd.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    sumit(jsonObject.toString());
                }
            }
        });




    }

    private void sumit(String msg) {
        Thread thread = new Thread(() -> {
            try {
                String result = MyUtils.PUT_T("http://124.93.196.45:10001/prod-api/api/common/user/resetPwd",msg,sharedPreferences.getString("token","k"));
                int code = new JSONObject(result).getInt("code");
                if(code == 200){
                    String msg1 = new JSONObject(result).getString("msg");
                    runOnUiThread(()->{
                        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,LogInActivity.class);
                        startActivity(intent);
                    });
                }else{
                    String msg2 = new JSONObject(result).getString("msg");
                    runOnUiThread(()->{
                        Toast.makeText(this, msg2, Toast.LENGTH_SHORT).show();
                        oldPwd.setText("");
                        newPwd.setText("");
                    });
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        });
        thread.start();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.user_pwd_tool);
        oldPwd = (EditText) findViewById(R.id.old_pwd);
        newPwd = (EditText) findViewById(R.id.new_pwd);
        btn = (Button) findViewById(R.id.change_pwd_btn);
        sharedPreferences = getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
    }
}