package com.example.savedatademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //用来获取存储在sharePreference中的数据
    private SharedPreferences pref;
    //用来将数据存储在sharePreference中
    private SharedPreferences.Editor editor;

    private  EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox rememberCheck;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化数据存储
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //获取按钮
         accountEdit = (EditText) findViewById(R.id.account);
         passwordEdit = (EditText) findViewById(R.id.password);
         rememberCheck = (CheckBox) findViewById(R.id.isRemember);
         login = (Button) findViewById(R.id.login);

        //获取上次是否勾选了复选框 若勾选了则获取sharePreference中存储的账号密码并显示
        boolean isRemember = pref.getBoolean("remember",false);

        if(isRemember){
            //显示密码到文本款
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberCheck.setChecked(true);

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取编辑文本中的账号密码
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("admin")&&password.equals("123456")){
                    editor = pref.edit();
                    //检查复选款是否被选中
                    if(rememberCheck.isChecked()){
                        //存储账号密码和信息
                        editor.putBoolean("remember",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}