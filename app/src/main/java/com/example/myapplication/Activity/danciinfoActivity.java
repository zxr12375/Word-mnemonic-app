package com.example.myapplication.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import com.example.myapplication.R;

/**
 * 单词查询
 */
public class danciinfoActivity extends AppCompatActivity {
    private static final String TAG = "danciinfoActivity";
    private int type;
    private TextView word_info;
    private TextView shiyi_info;

    private String Word = "body";
    private String translate = "";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danciinfo);
        init();
    }

    public void init(){
        word_info = (TextView)findViewById(R.id.info_word);
        shiyi_info =(TextView)findViewById(R.id.info_shiyi);
        Bundle bundle = getIntent().getExtras();
        Word = bundle.getString("word");
        translate = bundle.getString("translate");
        type = bundle.getInt("type");
        word_info.setText(Word);
        shiyi_info.setText(translate);
    }
}