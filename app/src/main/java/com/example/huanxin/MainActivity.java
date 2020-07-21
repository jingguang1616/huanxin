package com.example.huanxin;

import android.os.Bundle;
import android.widget.TextView;

import com.example.huanxin.utils.SpUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.mTextView);
        initdata();
    }

    private void initdata() {
        String name = (String) SpUtils.getParam(this, "name", "未登录");
        mTextView.setText("当前登录人："+name);
    }
}
