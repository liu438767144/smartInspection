package com.whut.smartinspection.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.whut.smartinspection.R;
import com.whut.smartinspection.model.IntervalUnit;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;
import com.wx.wheelview.widget.WheelView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Fortuner on 2018/1/1.
 * 全面巡视 数据录入（附件一）
 */
public class DataInputActivity extends SwipeBackActivity {
//    @BindView(R.id.wheelview_inter_unit)
//    WheelView wheelView;
    @BindView(R.id.tv_data_input)
    TextView dataInput;

    HashMap<String,List<String>> inputTips = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        ButterKnife.bind(this);
        //返回按钮监听
        CustomToolBar.goBack(DataInputActivity.this);
        IntervalUnit intervalUnit = (IntervalUnit)getIntent().getSerializableExtra("item");
        String interUnitName = intervalUnit.getName();
        dataInput.setText("间隔："+interUnitName);

        String key = getIntent().getStringExtra("key");
        List<String> values =  new ArrayList<>();
        values.add("断路器SF6气体压力(Mpa)");
        values.add("断路器动作计数器指示");
        inputTips.put(key,values);
    }
    @OnClick({})
    public void onClick(View view){
        switch (view.getId()) {
        }
    }

}
