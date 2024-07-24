package com.example.myapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Bean.User;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SQL.MySqliteOpenHelper;
import com.example.myapplication.Utils.SPUtils;


/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity {
    MySqliteOpenHelper helper = null;
    private EditText etAccount;//账号s
    private EditText etPassword;//密码
    private TextView tvRegister;//注册
    private Button btnLogin;//登录按钮
    private Switch btnSwitch;//
    boolean isChecked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);
        helper = new MySqliteOpenHelper(this);
        etAccount = (EditText) findViewById(R.id.et_account);//获取手机号
        etPassword = (EditText) findViewById(R.id.et_password);//获取密码
        tvRegister = (TextView) findViewById(R.id.tv_register);//获取注册
        btnLogin = (Button) findViewById(R.id.btn_login);//获取登录
        btnSwitch = (Switch) findViewById(R.id.btn_switch);//获取登录
        final SharedPreferences pref =   LoginActivity.this.getSharedPreferences("data", MODE_PRIVATE);
        isChecked = pref.getBoolean("switch", false);
        etAccount.setText(pref.getString("account", ""));
        etPassword.setText(pref.getString("password", ""));
        btnSwitch.setChecked(isChecked);
        //手机号注册
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册页面
                Intent intent = new Intent(  LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //切换
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean flag) {
                isChecked = flag;
            }
        });
        //设置点击按钮
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取请求参数
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                if ("".equals(account)) {//账号不能为空
                    Toast.makeText(  LoginActivity.this, "账号为空！", Toast.LENGTH_LONG).show();
                    return;
                }
                if ("".equals(password)) {//密码为空
                    Toast.makeText(  LoginActivity.this, "密码为空！", Toast.LENGTH_LONG).show();
                    return;
                }
                User mUser = null;
                String sql = "select * from user where account = ?";
                SQLiteDatabase db = helper.getWritableDatabase();
                Cursor cursor = db.rawQuery(sql, new String[]{account});
                if (cursor != null && cursor.getColumnCount() > 0) {
                    while (cursor.moveToNext()) {
                        String dbId = cursor.getString(0);
                        String dbAccount = cursor.getString(1);
                        String dbName = cursor.getString(2);
                        String dbPassword = cursor.getString(3);
                        mUser = new User(dbId, dbAccount, dbName, dbPassword);
                    }
                }
                db.close();
                if (mUser != null) {
                    if (!password.equals(mUser.getPassword())) {
                        Toast.makeText(  LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences.Editor editor = pref.edit();
                        if (isChecked) {
                            editor.putString("account", account);
                            editor.putString("password", password);
                        } else {
                            editor.putString("account", "");
                            editor.putString("password", "");
                        }
                        editor.putBoolean("switch", isChecked);
                        editor.commit();
                        SPUtils.put(LoginActivity.this,"username",account);
                        Intent intent = new Intent(  LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(  LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
