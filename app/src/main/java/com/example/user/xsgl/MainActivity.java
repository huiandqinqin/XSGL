package com.example.user.xsgl;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPersonName;
    private EditText etPassword;
    private Button btnLogin;
    private CheckBox chkRemember;
    private TextView tvForget;
    Helper helper;
    Cursor cursor;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        etPersonName = (EditText) findViewById(R.id.etPersonName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chkRemember = (CheckBox) findViewById(R.id.chkRemember);
        tvForget = (TextView) findViewById(R.id.tvForget);

        btnLogin.setOnClickListener(this);
        chkRemember.setOnClickListener(this);
        tvForget.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String xmlPersonName="",xmlPassword="";
        SharedPreferences share=getSharedPreferences("user", Activity.MODE_PRIVATE);
        etPersonName.setText(share.getString("PersonName",xmlPersonName));
        etPassword.setText(share.getString("Password",xmlPassword));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                helper=new Helper(this);
                cursor=helper.queryUser();
                String PersonName="",Password="";
                while(cursor.moveToNext()){
                    PersonName=cursor.getString(0);
                    Password=cursor.getString(1);
                }
                if(PersonName.equals(etPersonName.getText().toString().trim())&&
                        Password.equals(etPassword.getText().toString().trim()))
                {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(this, "帐号或密码不正确", Toast.LENGTH_SHORT).show();
                    etPersonName.setText("");
                    etPassword.setText("");
                }
                break;
            case R.id.chkRemember:
                if(chkRemember.isChecked())
                {
                    SharedPreferences share=getSharedPreferences("user", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=share.edit();
                    editor.putString("PersonName",etPersonName.getText().toString().trim());
                    editor.putString("Password",etPassword.getText().toString().trim());
                    editor.commit();
                }
                break;
            case R.id.tvForget:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setIcon(R.mipmap.key).setTitle("找回密码");
                etPass=new EditText(this);
                builder.setView(etPass);
                builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Notification.Builder builder=new Notification.Builder(MainActivity.this);
                        builder.setSmallIcon(R.mipmap.sms).setContentTitle("密码");
                        helper=new Helper(MainActivity.this);
                        cursor=helper.queryUserByPersonName(etPass.getText().toString().trim());
                        String backPassword="";
                        while(cursor.moveToNext()){
                            backPassword=cursor.getString(1);
                        }
                        builder.setContentText("你好！密码是："+backPassword);
                        Notification msg=builder.build();
                        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(1,msg);
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
                break;
        }
    }

    private void submit() {
        // validate
        String etPersonNameString = etPersonName.getText().toString().trim();
        if (TextUtils.isEmpty(etPersonNameString)) {
            Toast.makeText(this, "账号:", Toast.LENGTH_SHORT).show();
            return;
        }

        String etPasswordString = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(etPasswordString)) {
            Toast.makeText(this, "密码:", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
