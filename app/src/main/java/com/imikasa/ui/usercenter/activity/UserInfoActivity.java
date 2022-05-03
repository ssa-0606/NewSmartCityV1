package com.imikasa.ui.usercenter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.imikasa.MainActivity;
import com.imikasa.R;
import com.imikasa.ui.usercenter.UserCenterViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView avatar;
    private EditText nickName;
    private RadioButton man;
    private RadioButton woman;
    private EditText phoneNum;
    private Button btn;

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
        setContentView(R.layout.activity_user_info);

        initView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        UserCenterViewModel userCenterViewModel = new ViewModelProvider(this).get(UserCenterViewModel.class);
        userCenterViewModel.getUserInfoLiveData().observe(this,userInfo -> {
            Glide.with(getBaseContext()).load(userInfo.getAvatar()).into(avatar);
            nickName.setText(userInfo.getNickName());
            if(TextUtils.equals(userInfo.getSex(),"0")){
                man.setChecked(true);
            }else{
                woman.setChecked(true);
            }
            phoneNum.setText(userInfo.getPhonenumber());
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(nickName.getText())||TextUtils.isEmpty(phoneNum.getText())){
                    Toast.makeText(UserInfoActivity.this, "输入不得为空", Toast.LENGTH_SHORT).show();
                }else {
                    String nickname = nickName.getText().toString().trim();
                    String phone = phoneNum.getText().toString().trim();
                    String sex;
                    if(man.isChecked()){
                        sex = "0";
                    }else {
                        sex = "1";
                    }
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("nickName",nickname);
                        jsonObject.put("phonenumber",phone);
                        jsonObject.put("sex",sex);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    userCenterViewModel.setUserInfoLiveData(jsonObject.toString());
                }
            }
        });
        userCenterViewModel.stringLiveData().observe(this,s -> {
            if(TextUtils.equals(s,"修改成功")){
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }else if(TextUtils.equals(s,"test")){
                Log.d("TAG", "onCreate: do nothing ");
            }else {
                Toast.makeText(this, "修改失败，请在尝试!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.user_info_tool);
        avatar = (ImageView) findViewById(R.id.user_info_avatar);
        nickName = (EditText) findViewById(R.id.user_info_nick);
        man = (RadioButton) findViewById(R.id.user_info_mall);
        woman = (RadioButton) findViewById(R.id.user_info_femall);
        phoneNum = (EditText) findViewById(R.id.user_info_phone);
        btn = (Button) findViewById(R.id.user_info_btn);
    }
}