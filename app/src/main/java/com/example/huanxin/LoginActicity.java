package com.example.huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huanxin.utils.SpUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActicity extends AppCompatActivity implements View.OnClickListener {

    private EditText login_name;
    private EditText login_psw;
    private Button login_deng;
    private Button login_zhu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean before = EMClient.getInstance().isLoggedInBefore();
        if (before){
            startActivity(new Intent(LoginActicity.this,MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_login_acticity);
        initView();
    }

    private void initView() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_psw = (EditText) findViewById(R.id.login_psw);
        login_deng = (Button) findViewById(R.id.login_deng);
        login_zhu = (Button) findViewById(R.id.login_zhu);

        login_deng.setOnClickListener(this);
        login_zhu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_deng:
                    Login();
                break;
            case R.id.login_zhu:
                    startActivity(new Intent(this,RegisActivity.class));
                break;
        }
    }

    private void Login() {
        String name = login_name.getText().toString();
        String psw = login_psw.getText().toString();
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(psw)){
            EMClient.getInstance().login(name, psw, new EMCallBack() {
                @Override
                public void onSuccess() {
                    EMClient.getInstance().chatManager().loadAllConversations();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(LoginActicity.this,MainActivity.class));
                            SpUtils.setParam(LoginActicity.this,"name",name);
                            Toast.makeText(LoginActicity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });

                }

                @Override
                public void onError(int i, String s) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActicity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }else {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}
