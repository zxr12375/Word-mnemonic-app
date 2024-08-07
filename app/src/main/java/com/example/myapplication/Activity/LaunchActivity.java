package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.Utils.SPUtils;
import com.example.myapplication.http.BuildPath;

import com.squareup.picasso.Picasso;
//欢迎页面
public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ImageView i = findViewById(R.id.item_guide_iv_lunch);
        //加载网络图片
        Picasso.with(LaunchActivity.this).load("https://media-image1.baydn.com/soup_pub_image/drqaga/4f818317723f1125e8ba70645b9c0965.b867fe695902f26685e90e7814a31fae.jpeg").into(i);
        Handler handler = new Handler();
        //当计时结束,跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SPUtils.put(LaunchActivity.this,"LaunchLastTime",BuildPath.getDateString());
    }
}