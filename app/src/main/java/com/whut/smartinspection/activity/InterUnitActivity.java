package com.whut.smartinspection.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.whut.greendao.gen.DeviceDao;
import com.whut.greendao.gen.DeviceTypeDao;
import com.whut.greendao.gen.IntervalUnitDao;
import com.whut.greendao.gen.PatrolContentDao;
import com.whut.greendao.gen.PatrolTaskDetailDao;
import com.whut.greendao.gen.PerPatrolCardDao;
import com.whut.greendao.gen.RecordDao;
import com.whut.greendao.gen.TaskItemDao;
import com.whut.greendao.gen.WholePatrolCardDao;
import com.whut.smartinspection.R;
import com.whut.smartinspection.adapters.FullWheelAdapter;
import com.whut.smartinspection.adapters.InterUnitWheelAdapter;
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.model.Device;
import com.whut.smartinspection.model.DeviceType;
import com.whut.smartinspection.model.IntervalUnit;
import com.whut.smartinspection.model.PatrolContent;
import com.whut.smartinspection.model.PatrolTaskDetail;
import com.whut.smartinspection.model.PerPatrolCard;
import com.whut.smartinspection.model.Record;
import com.whut.smartinspection.model.TaskItem;
import com.whut.smartinspection.model.WholePatrolCard;
import com.whut.smartinspection.utils.ButtonUtils;
import com.whut.smartinspection.utils.SystemUtils;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;
import com.wx.wheelview.widget.WheelView;
import com.wx.wheelview.widget.WheelViewDialog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Fortuner on 2018/1/1.
 * 全面巡视activity （第三版）
 */

public class InterUnitActivity extends SwipeBackActivity {
    private List<Record> records;
    private List<Record> recordList = new ArrayList<>();
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.wheelview_inter_unit)
    WheelView wheelView;
    @BindView(R.id.data_input)
    Button btnDataInput;

    List<PatrolTaskDetail> lpatrolNameId;//任务对应的内容
    private InterUnitWheelAdapter wheelAdapter;
    PerPatrolCardDao patrolCardDao = SApplication.getInstance().getDaoSession().getPerPatrolCardDao();
    RecordDao recordDao = SApplication.getInstance().getDaoSession().getRecordDao();
    private TaskItem item;//传过来的任务item
    private String taskId;
    private String patrolNameId;

    List<IntervalUnit> intervalUnits = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_unit);

        //返回按钮监听
        CustomToolBar.goBack(InterUnitActivity.this);
        item = (TaskItem) getIntent().getSerializableExtra("item");
        taskId  = item.getIdd();
        //查询任务对应的细节
        PatrolTaskDetailDao patrolTaskDetailDao = SApplication.getInstance().getDaoSession().getPatrolTaskDetailDao();
        QueryBuilder<PatrolTaskDetail> qbPatrolTDetail = patrolTaskDetailDao.queryBuilder();
        lpatrolNameId = qbPatrolTDetail.where(PatrolTaskDetailDao.Properties.TaskId.eq(taskId)).list();
        patrolNameId = lpatrolNameId.size()>0?lpatrolNameId.get(0).getPatrolNameId():null;
        //先初始化数据库
        insertData(lpatrolNameId);
        initData();
        initView();
    }

    /**
     * 第一次进入页面时初始化插入提交的实体
     * @param patrolTaskDetails
     */
    private void insertData(List<PatrolTaskDetail> patrolTaskDetails){
    }

    /**
     * 初始化界面上的数据
     */
    private void initData(){
        //初始化间隔
        IntervalUnitDao intervalUnitDao = SApplication.getInstance().getDaoSession().getIntervalUnitDao();
        QueryBuilder<IntervalUnit> IUqueryBuilder = intervalUnitDao.queryBuilder();
        //SApplication.getInstance().getDaoSession().getIntervalUnitDao().queryBuilder()，作用就相当于Select * from table
        intervalUnits = IUqueryBuilder.list();
    }
    /**
     * 初始化界面
     */
    private void initView(){
        wheelAdapter = new InterUnitWheelAdapter(this,intervalUnits,null);
        final WheelView wheelView = (WheelView) findViewById(R.id.wheelview_inter_unit);
        wheelView.setWheelAdapter(wheelAdapter); // 文本数据源
        wheelView.setSkin(WheelView.Skin.None); // common 皮肤
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
//        wheelView.setMinimumHeight(100);
        style.selectedTextColor = R.color.bg_toolbar;
        style.textColor = Color.GRAY;
        if(SApplication.isPad(InterUnitActivity.this)){
            //平板屏幕适配
            style.selectedTextSize = 25;
            style.textSize =20;
        }else{
            //手机屏幕适配
            style.selectedTextSize = 20;
            style.textSize =17;
        }
        wheelView.setWheelSize(7);
        wheelView.setStyle(style);
        wheelView.setWheelClickable(true);
        wheelView.setSelection(0);
        wheelView.setWheelData(intervalUnits);  // 数据集合
    }
    @OnClick({R.id.name_device,R.id.style_device,R.id.btn_help,R.id.toolbar_right_tv,R.id.name_dis})
    public void onClick(View view){
        switch (view.getId()){
            //提交任务 发送
            case R.id.toolbar_right_tv:
                if(!ButtonUtils.isFastDoubleClick(R.id.btn_login_user_login)){
                    QueryBuilder<Record> qbRecord = recordDao.queryBuilder();
                    records = qbRecord.list();
                    Intent intent = new Intent();
                    intent.putExtra("item",item);
                    intent.putExtra("flag","1");
                    intent.setAction("com.whut.smartinspection.activity.FullInspectActivity");
                    sendBroadcast(intent);
                }else{
                    SystemUtils.showToast(InterUnitActivity.this,"正在提交数据！");
                    CountDownTimer cdt = new CountDownTimer(30000, 1000) {
                        int time = 30;
                        @Override
                        public void onTick(long millisUntilFinished) {
                            toolbarRightTv.setClickable(false);
                            toolbarRightTv.setText("提交("+time+ "s)");
                            time --;
                        }
                        @Override
                        public void onFinish() {
                            toolbarRightTv.setClickable(true);
                            toolbarRightTv.setText("提交");
                        }
                    };
                    cdt.start();
                }
                break;
            default:
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
