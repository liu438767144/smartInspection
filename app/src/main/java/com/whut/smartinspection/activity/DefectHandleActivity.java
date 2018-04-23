package com.whut.smartinspection.activity;

import android.os.Bundle;
import com.whut.smartinspection.R;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;

import butterknife.ButterKnife;

public class DefectHandleActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_handle);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        //返回按钮监听
        CustomToolBar.goBack(DefectHandleActivity.this);
    }
}
