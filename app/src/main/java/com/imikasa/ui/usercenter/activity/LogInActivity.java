package com.imikasa.ui.usercenter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imikasa.MainActivity;
import com.imikasa.R;
import com.imikasa.tools.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LogInActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button button;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(userNameEditText.getText())||TextUtils.isEmpty(passwordEditText.getText())){
                    Toast.makeText(LogInActivity.this, "输入不得为空", Toast.LENGTH_SHORT).show();
                }else{
                    submit();
                }

            }
        });

    }

    private void submit() {
        String usernameValue = userNameEditText.getText().toString().trim();
        String passwordValue = passwordEditText.getText().toString().trim();
        Log.d("TAG", "submit: "+usernameValue+passwordValue);
        Thread thread = new Thread(() -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username",usernameValue);
                jsonObject.put("password",passwordValue);
                Log.d("TAG", "submit: "+jsonObject.toString());
                String result = MyUtils.POST("http://124.93.196.45:10001/prod-api/api/login", jsonObject.toString());
                int code = new JSONObject(result).getInt("code");
                Log.d("TAG", "submit: "+code);
                if(code == 200){
                    String token = new JSONObject(result).getString("token");
                    runOnUiThread(()->{
                        Toast.makeText(this, "登录成功！！！", Toast.LENGTH_SHORT).show();
                        editor.putString("username",usernameValue);
                        editor.putString("password",passwordValue);
                        editor.putString("token",token);
                        editor.commit();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    });
                }else{
                    String msg = new JSONObject(result).getString("msg");
                    runOnUiThread(()->{
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        userNameEditText.setText("");
                        passwordEditText.setText("");
                    });
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void initView() {

        userNameEditText = (EditText) findViewById(R.id.log_in_username);
        passwordEditText = (EditText) findViewById(R.id.log_in_password);
        button = (Button) findViewById(R.id.log_in_btn);
        sharedPreferences = getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
    }
}