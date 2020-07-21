package com.example.huanxin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import androidx.appcompat.app.AppCompatActivity;

public class RegisActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regis_name;
    private EditText regis_psw;
    private Button regis_zhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        initView();
    }

    private void initView() {
        regis_name = (EditText) findViewById(R.id.regis_name);
        regis_psw = (EditText) findViewById(R.id.regis_psw);
        regis_zhu = (Button) findViewById(R.id.regis_zhu);

        regis_zhu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regis_zhu:
                    regist();
                break;
        }
    }

    private void regist() {
        String name = regis_name.getText().toString();
        String psw = regis_psw.getText().toString();
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(psw)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(name, psw);//同步方法
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                RegisActivity.this.finish();
                            }
                        });
                    } catch (HyphenateException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}
