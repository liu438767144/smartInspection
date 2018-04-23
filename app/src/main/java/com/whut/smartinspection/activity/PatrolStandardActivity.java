package com.whut.smartinspection.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.whut.smartinspection.R;
import com.whut.smartinspection.model.IntervalUnit;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fortuner on 2018/3/14.
 */

public class PatrolStandardActivity extends SwipeBackActivity {
    @BindView(R.id.patrol_standard)
    TextView patrolStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrol_standard);
        ButterKnife.bind(this);
        //返回按钮监听
        CustomToolBar.goBack(PatrolStandardActivity.this);
        IntervalUnit intervalUnit = (IntervalUnit)getIntent().getSerializableExtra("item");
        String interUnitName = intervalUnit.getName();
        patrolStandard.setText("间隔:"+interUnitName);

    }
}
