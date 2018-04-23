package com.whut.smartinspection.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.whut.smartinspection.R;
import com.whut.smartinspection.model.IntervalUnit;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**巡视缺陷上报
 * Created by Fortuner on 2018/3/14.
 */
public class DefectFullInspectActivity extends SwipeBackActivity {
    @BindView(R.id.defect_inter_unit)
    TextView defectInterUnit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_full_inspect);
        ButterKnife.bind(this);
        //返回按钮监听
        CustomToolBar.goBack(DefectFullInspectActivity.this);

        IntervalUnit intervalUnit = (IntervalUnit)getIntent().getSerializableExtra("item");
        String interUnitName = intervalUnit.getName();
        defectInterUnit.setText("间隔:"+interUnitName);

    }
}
