package com.whut.smartinspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.whut.smartinspection.R;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 缺陷管理
 */
public class DefectManagementActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_management);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        //返回按钮监听
        CustomToolBar.goBack(DefectManagementActivity.this);
    }
    @OnClick({R.id.defect_registration, R.id.defect_review, R.id.defect_treatment, R.id.defective_acceptance})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.defect_registration://缺陷登记
                intent = new Intent(DefectManagementActivity.this, DefectRegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.defect_review://缺陷审核
                intent = new Intent(DefectManagementActivity.this, DefectReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.defect_treatment://缺陷处理
                intent = new Intent(DefectManagementActivity.this, DefectHandleActivity.class);
                startActivity(intent);
                break;
            case R.id.defective_acceptance://缺陷验收
                intent = new Intent(DefectManagementActivity.this, DefectAcceptanceActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
