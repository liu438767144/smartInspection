package com.whut.smartinspection.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.whut.smartinspection.R;
import com.whut.smartinspection.widgets.CustomToolBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by 11142 on 18/03/05.
 */

public class KnowledgeCenterActivity extends Activity{
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.button6)
    Button button6;
    // 权限请求码
    private final int PERMISSION_REQUEST_CODE= 1;
    // 权限集合
    private String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_center);
        ButterKnife.bind(this);
        CustomToolBar.goBack(KnowledgeCenterActivity.this);
        callPermission();


    }

    public void callPermission() {

        if(lacksPermissions(PERMISSIONS)) {// 缺少权限，则向用户申请权限

            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);

        }

    }

    // 判断权限集合

    public boolean lacksPermissions(String... permissions) {

        for(String permission : permissions) {

            if(ContextCompat.checkSelfPermission(KnowledgeCenterActivity.this, permission)
                    == PackageManager.PERMISSION_DENIED) {
                return true;
            }

        }

        return false;

    }

    // 请求运行时权限回调函数

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode,permissions, grantResults);

    }

// 含有全部的权限

    protected boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {

        for (int grantResult : grantResults) {

            if (grantResult == PackageManager.PERMISSION_DENIED) {

                return false;

            }

        }

        return true;

    }

    @OnClick({R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6})
    public void onClick(View view){

        switch (view.getId()){
            case R.id.button1:
                Intent intent1 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent1.putExtra("name","检测");
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent2 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent2.putExtra("name","检修");
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent3 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent3.putExtra("name","评价");
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent4 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent4.putExtra("name","验收");
                startActivity(intent4);
                break;
            case R.id.button5:
                Intent intent5 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent5 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent5.putExtra("name","运维");
                startActivity(intent5);
                break;
            case R.id.button6:
                Intent intent6 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent6 = new Intent(KnowledgeCenterActivity.this, FtpActivity.class);
                intent6.putExtra("name","其他");
                startActivity(intent6);
                break;
            default:
                break;
        }

    }
}
