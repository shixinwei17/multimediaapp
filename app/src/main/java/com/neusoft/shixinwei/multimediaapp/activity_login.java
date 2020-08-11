package com.neusoft.shixinwei.multimediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {

    EditText etName;
    EditText etPassword;
    public static String inputName;
    public static int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName=findViewById(R.id.editText);
        etPassword=findViewById(R.id.editText2);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(activity_login.this,activity_register.class);
                startActivity(i);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //验证登录用户名、密码
                inputName=etName.getText().toString();
                String inputPassword=etPassword.getText().toString();

                DBHelper db=new DBHelper(activity_login.this);
                db.open();
                if (db.checkNamePassword(inputName,inputPassword)){
                    //验证成功
                    Intent i=new Intent(activity_login.this,activity_myself.class);
                    a=1;
                    startActivity(i);
                }else {
                    //验证失败
                    Toast.makeText(activity_login.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
