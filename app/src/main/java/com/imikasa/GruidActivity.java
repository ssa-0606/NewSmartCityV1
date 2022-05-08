package com.imikasa;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imikasa.tools.MyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GruidActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private Button btn;
    private FloatingActionButton fbtn;
    private List<String> imgList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public GruidActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gruid);

        initView();

        getLunBo();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        radioGroup.check(R.id.radioButton2);
                        break;
                    case 1:
                        radioGroup.check(R.id.radioButton3);
                        break;
                    case 2:
                        radioGroup.check(R.id.radioButton4);
                        break;
                    case 3:
                        radioGroup.check(R.id.radioButton5);
                        break;
                }
                if (position < 3){
                    btn.setVisibility(View.GONE);
                    fbtn.setVisibility(View.GONE);
                }else{
                    btn.setVisibility(View.VISIBLE);
                    fbtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(GruidActivity.this);
                View inflate = LayoutInflater.from(GruidActivity.this).inflate(R.layout.layout_dialog, null);
                dialog.setContentView(inflate);
                EditText ip = inflate.findViewById(R.id.dialog_ip);
                EditText port = inflate.findViewById(R.id.dialog_port);
                Button btn = inflate.findViewById(R.id.dialog_btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(TextUtils.isEmpty(ip.getText())||TextUtils.isEmpty(port.getText())){
                            Toast.makeText(GruidActivity.this, "输入不得为空", Toast.LENGTH_SHORT).show();
                        }else{
                            String ipValue = ip.getText().toString().trim();
                            String portValue = port.getText().toString().trim();
                            String url = "http://"+ipValue+":"+portValue;

                            new Thread(()->{
                                try {
                                    OkHttpClient client = new OkHttpClient();
                                    Request request = new Request.Builder().url(url).build();
                                    Response re = client.newCall(request).execute();
                                    int code = re.code();
                                    runOnUiThread(()->{
                                        if(code ==200){
                                            Toast.makeText(GruidActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                                            editor.putString("ip",ipValue);
                                            editor.putString("port",portValue);
                                            editor.commit();
                                            dialog.dismiss();
                                        }else{
                                            ip.setText("");
                                            port.setText("");
                                            Toast.makeText(GruidActivity.this, "超时了", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                } catch (IOException e) {
                                    runOnUiThread(()->{
                                        ip.setText("");
                                        port.setText("");
                                        Log.d("TAG", "onClick: 超时了");
                                        Toast.makeText(GruidActivity.this, "设置不正确，请重新设置", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    });
                                }
                            }).start();

                        }
                    }
                });
                dialog.show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(sharedPreferences.getString("ip","k"),"k")){
                    Toast.makeText(GruidActivity.this, "请先设置网络", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(GruidActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getLunBo() {
        imgList = new ArrayList<>();
        Thread thread = new Thread(()->{
            try {
                String result = MyUtils.GET("http://124.93.196.45:10001/prod-api/api/rotation/list");
                JSONArray rows = new JSONObject(result).getJSONArray("rows");
                for (int i = 0; i < rows.length(); i++) {
                    String advImg = rows.getJSONObject(i).getString("advImg");
                    imgList.add(advImg);
                }
                handler.sendEmptyMessage(1);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        });
        thread.start();

    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        radioGroup = findViewById(R.id.radioGroup2);
        btn = findViewById(R.id.button);
        fbtn = findViewById(R.id.floatingActionButton);
        sharedPreferences = getSharedPreferences("data",0);
        editor = sharedPreferences.edit();
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    viewPager.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                            return imgList.size();
                        }

                        @Override
                        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                            return view == object;
                        }

                        @Override
                        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                            container.removeView((View) object);
                        }

                        @NonNull
                        @Override
                        public Object instantiateItem(@NonNull ViewGroup container, int position) {
                            ImageView imageView = new ImageView(GruidActivity.this);
//                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            Log.d("TAG", "http://124.93.196.45:10001"+imgList.get(position));
                            Glide.with(GruidActivity.this).load("http://124.93.196.45:10001"+imgList.get(position)).into(imageView);
                            container.addView(imageView);
                            return imageView;
                        }
                    });
                    viewPager.setCurrentItem(0);
                    break;
            }
        }
    };

}