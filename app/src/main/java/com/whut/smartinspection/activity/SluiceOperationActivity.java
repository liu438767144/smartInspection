package com.whut.smartinspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nextgeneration.ExpandingLayout;
import com.whut.greendao.gen.PatrolTaskDetailDao;
import com.whut.greendao.gen.PatrolWorkCardDao;
import com.whut.greendao.gen.SluiceHeadPageDao;
import com.whut.greendao.gen.SluiceOperationContentDao;
import com.whut.greendao.gen.SluiceOperationRecordDao;
import com.whut.greendao.gen.WholeSluiceCardDao;
import com.whut.smartinspection.R;
import com.whut.smartinspection.adapters.SluiceOperationAdapter;
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.model.PatrolTaskDetail;
import com.whut.smartinspection.model.PatrolWorkCard;
import com.whut.smartinspection.model.SluiceHeadPage;
import com.whut.smartinspection.model.SluiceOperationContent;
import com.whut.smartinspection.model.SluiceOperationRecord;
import com.whut.smartinspection.model.TaskItem;
import com.whut.smartinspection.model.WholeSluiceCard;
import com.whut.smartinspection.utils.ButtonUtils;
import com.whut.smartinspection.utils.SystemUtils;
import com.whut.smartinspection.utils.TimeUtils;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyz on 2018/4/2.
 * 道闸操作
 */

public class SluiceOperationActivity extends SwipeBackActivity {

    @BindView(R.id.sluice_department)
    EditText sluiceDepartment;//单位
    @BindView(R.id.sluice_id)
    EditText sluiceId;//编号
    @BindView(R.id.sluice_task)
    EditText sluiceTask;//操作任务
    @BindView(R.id.sluice_sendPeople)
    EditText sluiceSendPeople;//发令人
    @BindView(R.id.sluice_receivePeople)
    EditText sluiceReceivePeople;//受令人
    @BindView(R.id.sluice_sendDate)
    EditText sluiceSendDate;//发令时间
    @BindView(R.id.sluice_start)
    EditText sluiceStart;//开始时间
    @BindView(R.id.sluice_end)
    EditText sluiceEnd;//结束时间
    @BindView(R.id.monitoring_operation)
    CheckBox monitoringOperation;//监护下操作
    @BindView(R.id.single_operation)
    CheckBox singleOperation;//单人操作
    @BindView(R.id.maintenance_operation)
    CheckBox maintenanceOperation;//检修人员操作
    @BindView(R.id.sluice_content)
    ListView sluiceContent;//操作内容列表
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;//提交按钮
    @BindView(R.id.expand)
    Button expand;//展开、收起按钮
    @BindView(R.id.expanding)
    ExpandingLayout expandingLayout;
    @BindView(R.id.sluice_remark)
    EditText sluiceRemark;//备注

    private int count = 1;
    private TaskItem item;//传过来的任务item
    private String taskId;//任务的ID
    private String taskName;//任务名称
    private Date startDate;//发令时间
    private String worker;//发令人 = 受令人
    private Long wholeId = 0L;//wholeId对应一个作业卡
    private String substationName;//变电站名称
    private String patrolNameId;
    private String patrolHeadPageId;
    private List<PatrolWorkCard> patrolWorkCards;
    private SluiceOperationAdapter sluiceOperationAdapter;
    private List<SluiceOperationRecord> sluiceOperationRecords;
    private List<SluiceOperationRecord> sluiceOperationRecordList = new ArrayList<>();
    private List<SluiceOperationContent> sluiceOperationContents;
    private List<SluiceOperationContent> sluiceOperationContentList = new ArrayList<>();
    private List<SluiceHeadPage> sluiceHeadPages;
    private List<PatrolTaskDetail> patrolTaskDetails;//任务对应的内容

    SluiceOperationContentDao sluiceOperationContentDao = SApplication.getInstance().getDaoSession().getSluiceOperationContentDao();
    SluiceOperationRecordDao sluiceOperationRecordDao = SApplication.getInstance().getDaoSession().getSluiceOperationRecordDao();
    SluiceHeadPageDao sluiceHeadPageDao = SApplication.getInstance().getDaoSession().getSluiceHeadPageDao();
    WholeSluiceCardDao wholeSluiceCardDao = SApplication.getInstance().getDaoSession().getWholeSluiceCardDao();
    PatrolTaskDetailDao patrolTaskDetailDao = SApplication.getInstance().getDaoSession().getPatrolTaskDetailDao();
    PatrolWorkCardDao patrolWorkCardDao = SApplication.getInstance().getDaoSession().getPatrolWorkCardDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sluice_operation);
        ButterKnife.bind(this);
        CustomToolBar.goBack(SluiceOperationActivity.this);//返回按钮监听
        item = (TaskItem) getIntent().getSerializableExtra("item");
        taskId = item.getIdd();
        taskName = item.getCommon();
        startDate = item.getStartDate();
        worker = item.getWorker();
        //查询任务的细节
        QueryBuilder<PatrolTaskDetail> qbPatrolTDetail = patrolTaskDetailDao.queryBuilder();
        patrolTaskDetails = qbPatrolTDetail.where(PatrolTaskDetailDao.Properties.TaskId.eq(taskId)).list();

        insertData(patrolTaskDetails);//初始化数据库数据
        initData();//初始化界面显示的内容
    }

    //初始化数据库中的数据
    private void insertData(List<PatrolTaskDetail> patrolTaskDetails) {
        for (PatrolTaskDetail patrolTaskDetail : patrolTaskDetails) {
            patrolNameId = patrolTaskDetail.getPatrolNameId();
            patrolHeadPageId = patrolTaskDetail.getPatrolHeadPageId();
            //按照作业卡的id保存数据
            WholeSluiceCard wholeSluiceCard = new WholeSluiceCard(null, patrolHeadPageId, false);
            wholeSluiceCardDao.insertOrReplace(wholeSluiceCard);
            wholeId = wholeSluiceCard.getId();
            //通过patrolNameId查找对应的任务内容
            QueryBuilder<SluiceOperationContent> qbSluiceOperationContent = sluiceOperationContentDao.queryBuilder();
            sluiceOperationContents = qbSluiceOperationContent.where(SluiceOperationContentDao.Properties.PatrolNameId.eq(patrolNameId)).list();
            for (int i = 0; i < sluiceOperationContents.size(); i++) {
                SluiceOperationRecord sluiceOperationRecord = new SluiceOperationRecord();
                sluiceOperationRecord.setIdd(taskId);
                sluiceOperationRecord.setValueChar(" ");
                sluiceOperationRecord.setSluiceOperationContentId(sluiceOperationContents.get(i).getIdd());
                sluiceOperationRecord.setSluiceOperationRecordDate(TimeUtils.setCurrentTime3());
                sluiceOperationRecord.setWholeID(wholeId);
                sluiceOperationRecordDao.insertOrReplace(sluiceOperationRecord);
            }

            sluiceOperationContentList.clear();
            for (SluiceOperationContent sluiceOperationContent : sluiceOperationContents) {
                sluiceOperationContentList.add(sluiceOperationContent);
            }

            QueryBuilder<SluiceOperationRecord> qbSluiceOperationRecord = sluiceOperationRecordDao.queryBuilder();
            sluiceOperationRecords = qbSluiceOperationRecord.list();
            sluiceOperationRecordList.clear();
            for (SluiceOperationRecord sluiceOperationRecord : sluiceOperationRecords) {
                sluiceOperationRecordList.add(sluiceOperationRecord);
            }
        }
    }

    //初始化界面显示的内容
    private void initData() {

        //查询变电站的名称
        QueryBuilder<PatrolWorkCard> qbPatrolWorkCard = patrolWorkCardDao.queryBuilder();
        patrolWorkCards = qbPatrolWorkCard.where(PatrolWorkCardDao.Properties.Idd.eq(patrolNameId)).list();
        for (PatrolWorkCard patrolWorkCard : patrolWorkCards) {
            substationName = patrolWorkCard.getOtherColumn();
        }

        sluiceOperationAdapter = new SluiceOperationAdapter(this, sluiceOperationContentList, sluiceOperationRecordList);
        sluiceContent.setAdapter(sluiceOperationAdapter);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sluiceDepartment.setText(substationName);
                sluiceId.setText("锦江变201712007");
                sluiceTask.setText(taskName);
                sluiceSendPeople.setText(worker);
                sluiceReceivePeople.setText(worker);
                sluiceStart.setText(TimeUtils.setCurrentTime3());
            }
        });
    }

    //保存表头数据
    private void savaHeadPage() {
        SluiceHeadPage sluiceHeadPage = new SluiceHeadPage();
        sluiceHeadPage.setTaskId(taskId);
        sluiceHeadPage.setSluiceDepartment(sluiceDepartment.getText().toString());
        sluiceHeadPage.setSluiceId(sluiceId.getText().toString());
        sluiceHeadPage.setSluiceTask(sluiceTask.getText().toString());
        sluiceHeadPage.setSluiceSendPeople(sluiceSendPeople.getText().toString());
        sluiceHeadPage.setSluiceReceivePeople(sluiceReceivePeople.getText().toString());
        sluiceHeadPage.setSluiceSendDate(sluiceSendDate.getText().toString());
        sluiceHeadPage.setSluiceStart(sluiceStart.getText().toString());
        sluiceHeadPage.setSluiceEnd(sluiceEnd.getText().toString());
        if (monitoringOperation.isChecked()) {
            sluiceHeadPage.setMonitoringOperation((short) 1);
        } else {
            sluiceHeadPage.setMonitoringOperation((short) 0);
        }
        if (singleOperation.isChecked()) {
            sluiceHeadPage.setSingleOperation((short) 1);
        } else {
            sluiceHeadPage.setSingleOperation((short) 0);
        }
        if (maintenanceOperation.isChecked()) {
            sluiceHeadPage.setMaintenanceOperation((short) 1);
        } else {
            sluiceHeadPage.setMaintenanceOperation((short) 0);
        }
        sluiceHeadPage.setRemark(sluiceRemark.getText().toString());
        sluiceHeadPageDao.insertOrReplace(sluiceHeadPage);
    }

    @OnClick({R.id.toolbar_right_tv, R.id.expand})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_right_tv: {//提交
                sluiceEnd.setText(TimeUtils.setCurrentTime3());//生成完成时间
                savaHeadPage();
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_login_user_login)) {
                    QueryBuilder<SluiceOperationRecord> qbsluiceOperationRecord = sluiceOperationRecordDao.queryBuilder();
                    sluiceOperationRecords = qbsluiceOperationRecord.list();//(用于断点调试)
                    Intent intent = new Intent();
                    intent.putExtra("flag", "4");
                    intent.setAction("com.whut.smartinspection.activity.FullInspectActivity");
                    sendBroadcast(intent);
                } else {
                    SystemUtils.showToast(SluiceOperationActivity.this, "正在提交数据！");
                    CountDownTimer cdt = new CountDownTimer(30000, 1000) {
                        int time = 30;
                        @Override
                        public void onTick(long millisUntilFinished) {
                            toolbarRightTv.setClickable(false);
                            toolbarRightTv.setText("提交(" + time + "s)");
                            time--;
                        }
                        @Override
                        public void onFinish() {
                            toolbarRightTv.setClickable(true);
                            toolbarRightTv.setText("提交");
                        }
                    };
                    cdt.start();
                }
            }
            break;
            case R.id.expand:
                expandingLayout.expandOrHide();
                if (count == 1) {
                    expand.setText("展开");
                    count--;
                } else {
                    expand.setText("收起");
                    count++;
                }
                break;
            default:
                break;
        }
    }

}
