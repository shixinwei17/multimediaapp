package com.neusoft.shixinwei.multimediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void registerClick(View v){
        //获取用户名、密码的用户数据
        EditText etName=findViewById(R.id.editText3);
        EditText etrelName=findViewById(R.id.editText4);
        EditText etPassword=findViewById(R.id.editText5);

        //信息保存到数据库
        boolean ret;
        DBHelper dbHelper=new DBHelper(this);
        dbHelper.open();
        ret=dbHelper.intert(etName.getText().toString(),
                etrelName.getText().toString(),
                etPassword.getText().toString()
                );
        if(!ret){
            Toast.makeText(this,"数据库操作失败！",Toast.LENGTH_SHORT).show();
        }

        //返回登录界面
        Toast.makeText(this,"账号注册成功！",Toast.LENGTH_SHORT).show();
        finish();
    }
}
