package com.whut.smartinspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.whut.smartinspection.R;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tw.com.a_i_t.IPCamViewer.HelmetActivity;

/**
 * Created by Fortuner on 2017/12/15.
 */

public class HelpActivity  extends SwipeBackActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        CustomToolBar.goBack(HelpActivity.this);
    }
    @OnClick({R.id.first,R.id.second,R.id.third,R.id.fourth})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.first://智能安全帽
                intent = new Intent(HelpActivity.this, HelmetActivity.class);
                startActivity(intent);
                break;
            case R.id.second://知识中心
                intent = new Intent(HelpActivity.this, KnowledgeCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.third://无线门禁
                intent = new Intent(HelpActivity.this, BluetoothActivity.class);
                startActivity(intent);
                break;
            case R.id.fourth://缺陷管理
                intent = new Intent(HelpActivity.this, DefectManagementActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
