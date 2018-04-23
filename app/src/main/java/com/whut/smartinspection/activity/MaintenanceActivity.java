package com.whut.smartinspection.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.whut.greendao.gen.DeviceDao;
import com.whut.greendao.gen.DeviceTypeDao;
import com.whut.greendao.gen.PatrolContentDao;
import com.whut.greendao.gen.PatrolTaskDetailDao;
import com.whut.greendao.gen.PerPatrolCardDao;
import com.whut.greendao.gen.RecordDao;
import com.whut.greendao.gen.TaskItemDao;
import com.whut.greendao.gen.WholePatrolCardDao;
import com.whut.smartinspection.R;
import com.whut.smartinspection.adapters.FullWheelAdapter;
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.model.Device;
import com.whut.smartinspection.model.DeviceType;
import com.whut.smartinspection.model.PatrolContent;
import com.whut.smartinspection.model.PatrolTaskDetail;
import com.whut.smartinspection.model.PerPatrolCard;
import com.whut.smartinspection.model.Record;
import com.whut.smartinspection.model.TaskItem;
import com.whut.smartinspection.model.WholePatrolCard;
import com.whut.smartinspection.utils.ButtonUtils;
import com.whut.smartinspection.utils.SystemUtils;
import com.whut.smartinspection.utils.TimeUtils;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;
import com.wx.wheelview.widget.WheelView;
import com.wx.wheelview.widget.WheelViewDialog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyz on 2018/2/1.
 */

public class MaintenanceActivity extends SwipeBackActivity {

    @BindView(R.id.substation_name)
    EditText substationName;
    @BindView(R.id.style_device)
    EditText deviceTypeName;
    @BindView(R.id.work_time_start)
    EditText workTimeStart;
    @BindView(R.id.work_time_end)
    EditText workTimeEnd;
    @BindView(R.id.num_workCard)
    EditText numWorkCard;
    @BindView(R.id.btn_help)
    Button btnHelp;
    @BindView(R.id.wheelview)
    WheelView wheelview;
//    @BindView(R.id.worker_signature)
//    EditText workerSignature;
//    @BindView(R.id.executive_evaluation)
//    EditText executiveEvaluation;
//    @BindView(R.id.principal_signature)
//    EditText principalSignature;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;

    private List<Record> records;
    private List<Record> recordList = new ArrayList<>();
    List<PatrolTaskDetail> lpatrolNameId;//任务对应的内容
    private FullWheelAdapter wheelAdapter;
    final static String TAG = "MaintenanceActivity";
    PerPatrolCardDao patrolCardDao = SApplication.getInstance().getDaoSession().getPerPatrolCardDao();
    RecordDao recordDao = SApplication.getInstance().getDaoSession().getRecordDao();

    private final ArrayList<DeviceType> deviceTypeList = new ArrayList<>();//设备类型名称列表
    private final ArrayList<String> intervalUnitList = new ArrayList<>();//间隔名称列表
    private final ArrayList<Device> deviceNameList = new ArrayList<>();//设备名称列表
    private String deviceId;//设备名称ID
    private String deviceName;//设备名称
    private String subIdI;//变电站ID
    private int deviceTypeIddI;//item设备类型ID
    private String deviceTypeNameI;//item设备类型名称
    private TaskItem item;//传过来的任务item
    private String taskId;
    private long deviceDbId = 0L;
    private PerPatrolCard perPatrolCard = null;
    private int pDevice = 0;
    private List<Device> deviceList;
    private long pointPatrol = 0L;
    private WholePatrolCard wholePatrolCard;
    private Long wholeId = 0L;
    private Long perPatrolCardId = 0L;
    private Long recordId = 0L;
    private String patrolNameId;
    private String patrolHeadPageId;
    private List<PatrolContent> patrolContents;
    private List<PatrolContent> patrolContentList = new ArrayList<>();

    private List<Record> resultList;
    private Map<String, Map<String, List<Record>>> perDeviceType = new HashMap<>();//key-->patrolHeadPageID
    private Map<String, Long> mapId;
    private Map<String, Map<String, Long>> mapPatrolNameId = new HashMap<>();
    DeviceTypeDao deviceTypedao = SApplication.getInstance().getDaoSession().getDeviceTypeDao();
    DeviceType deviceType;
    DeviceDao deviceDao = SApplication.getInstance().getDaoSession().getDeviceDao();
    PatrolContentDao patrolContentDao = SApplication.getInstance().getDaoSession().getPatrolContentDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        ButtonUtils.setDIFF(30 * 60 * 1000);//设置提交按钮三十秒内不能点击
        ButtonUtils.setLastClickTime(0);
        ButterKnife.bind(this);
        CustomToolBar.goBack(MaintenanceActivity.this);//返回按钮监听
        item = (TaskItem) getIntent().getSerializableExtra("item");
        taskId = item.getIdd();
        //查询任务对应的细节
        PatrolTaskDetailDao patrolTaskDetailDao = SApplication.getInstance().getDaoSession().getPatrolTaskDetailDao();
        QueryBuilder<PatrolTaskDetail> qbPatrolTDetail = patrolTaskDetailDao.queryBuilder();
        lpatrolNameId = qbPatrolTDetail.where(PatrolTaskDetailDao.Properties.TaskId.eq(taskId)).list();
        patrolNameId = lpatrolNameId.size() > 0 ? lpatrolNameId.get(0).getPatrolNameId() : null;
        insertData1(lpatrolNameId);//先初始化数据库
        initData1();
        initView();
        changeInitedStatue();
    }

    private void changeInitedStatue() {
        item.setStatus(1);//改为一表示初始化过了
        TaskItemDao taskItemDao = SApplication.getInstance().getDaoSession().getTaskItemDao();
        taskItemDao.insertOrReplace(item);
    }

    private void insertData1(List<PatrolTaskDetail> patrolTaskDetails) {
        for (PatrolTaskDetail patrolTaskDetail : patrolTaskDetails) {
            patrolNameId = patrolTaskDetail.getPatrolNameId();
            patrolHeadPageId = patrolTaskDetail.getPatrolHeadPageId();
            //提交时按设备类型提交的
            WholePatrolCard wholePatrolCard = new WholePatrolCard(wholeId, patrolHeadPageId, false);
            WholePatrolCardDao wholePatrolCardDao = SApplication.getInstance().getDaoSession().getWholePatrolCardDao();
            wholePatrolCardDao.insertOrReplace(wholePatrolCard);
            //作业卡片内容
            QueryBuilder<PatrolContent> qbPC = patrolContentDao.queryBuilder();
            patrolContents = qbPC.where(PatrolContentDao.Properties.PatrolNameId.eq(patrolNameId)).list();

            //初始化设备类型列表
            deviceTypeIddI = patrolContents.get(0).getDeviceTypeId();//获得设备类型ID
            QueryBuilder<DeviceType> qbDeviceTpe = deviceTypedao.queryBuilder();
            deviceType = qbDeviceTpe.where(DeviceTypeDao.Properties.Idd.eq(deviceTypeIddI)).unique();
            if (deviceType != null)
                deviceTypeList.add(deviceType);

            if (item.getStatus() == 0) {//未初始化
                QueryBuilder<Device> qbDevice = deviceDao.queryBuilder();
                deviceList = qbDevice.where(DeviceDao.Properties.DeviceTypeId.eq(deviceTypeIddI)).list();

                recordDao = SApplication.getInstance().getDaoSession().getRecordDao();
                for (int i = 0; i < deviceList.size(); i++) {
                    //插入设备
                    String devieceId = deviceList.get(i).getIdd();
                    for (int j = 0; j < patrolContents.size(); j++) {
                        Record record = new Record();
                        record.setValueChar(" ");
                        record.setValueFloat(-1);
                        record.setId(pointPatrol++);
                        record.setDeviceId(devieceId);
                        record.setPatrolContentId(patrolContents.get(j).getIdd());
                        //加入作业卡id用于提交
                        record.setWholeID(wholeId);
                        recordDao.insertOrReplace(record);
                    }
                }
                wholeId++;
            }

        }
    }

    private void initData1() {
        //转到第一种设备类型
        patrolNameId = lpatrolNameId.get(0).getPatrolNameId();
        //巡视作业卡片内容
        QueryBuilder<PatrolContent> qbPatrolContent = patrolContentDao.queryBuilder();
        patrolContents = qbPatrolContent.where(PatrolContentDao.Properties.PatrolNameId.eq(patrolNameId)).list();
        //初始化设备
        String deviceTypeId = deviceTypeList.get(0).getIdd();
        QueryBuilder<Device> qbDevice = deviceDao.queryBuilder();
        qbDevice = deviceDao.queryBuilder();
        deviceList = qbDevice.where(DeviceDao.Properties.DeviceTypeId.eq(Integer.valueOf(deviceTypeId))).list();
        //获取巡视结果容器
        String deviceId = deviceList.get(0).getIdd();

        QueryBuilder<Record> qbRecord = recordDao.queryBuilder();
        records = qbRecord.where(RecordDao.Properties.DeviceId.eq(deviceId)).list();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                substationName.setText("锦江变电站500KV");
                deviceTypeName.setText(deviceTypeList.get(0).getName());
                workTimeStart.setText(TimeUtils.setCurrentTime2());
                numWorkCard.setText("锦江变维护201808001");
            }
        });
    }

    private void changeDeviceType1(int i) {
        //巡视作业卡片内容
        QueryBuilder<PatrolContent> qbPatrolContent = patrolContentDao.queryBuilder();
        patrolContents = qbPatrolContent.where(PatrolContentDao.Properties.PatrolNameId.eq(patrolNameId)).list();
        deviceTypeIddI = patrolContents.get(0).getDeviceTypeId();//获得设备类型ID

        deviceType = deviceTypeList.get(i);
        //改变设备列表
        QueryBuilder<Device> qbDevice = deviceDao.queryBuilder();
        deviceList = qbDevice.where(DeviceDao.Properties.DeviceTypeId.eq(deviceTypeIddI)).list();
        //改变记录列表
        String deviceId = deviceList.get(0).getIdd();
        QueryBuilder<Record> qbRecord = recordDao.queryBuilder();
        records = qbRecord.where(RecordDao.Properties.DeviceId.eq(deviceId)).list();
        patrolContentList.clear();
        for (PatrolContent patrolContent : patrolContents) {
            patrolContentList.add(patrolContent);
        }
        recordList.clear();
        for (Record record : records) {
            recordList.add(record);
        }
        wheelAdapter.notifyDataSetChanged();

    }

    private void initView() {
        for (PatrolContent patrolContent : patrolContents) {
            patrolContentList.add(patrolContent);
        }
        for (Record record : records) {
            recordList.add(record);
        }
        wheelAdapter = new FullWheelAdapter(this, patrolContentList, recordList);
        final WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(wheelAdapter); // 文本数据源
        wheelView.setSkin(WheelView.Skin.None); // common 皮肤
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        wheelView.setMinimumHeight(100);
        style.selectedTextColor = R.color.bg_toolbar;
        style.textColor = Color.GRAY;
//        style.selectedTextZoom = 5L;
        if (SApplication.isPad(MaintenanceActivity.this)) {//平板
            style.selectedTextSize = 30;
            style.textSize = 27;
        } else {//手机
            style.selectedTextSize = 18;
            style.textSize = 15;
        }
        wheelView.setWheelSize(7);
        wheelView.setStyle(style);
        wheelView.setWheelClickable(true);
        wheelView.setSelection(1);

        wheelView.setWheelData(patrolContentList);  // 数据集合
    }

    @OnClick({R.id.style_device, R.id.btn_help, R.id.toolbar_right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.style_device:
                showDialog(view, deviceTypeList);
                break;
            case R.id.btn_help:
                Intent intent = new Intent(MaintenanceActivity.this, WorkingHelpActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_right_tv://提交任务 发送
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_login_user_login)) {
                    workTimeEnd.setText(TimeUtils.setCurrentTime2());
                    QueryBuilder<Record> qbRecord = recordDao.queryBuilder();
                    records = qbRecord.list();
                    intent = new Intent();
                    intent.putExtra("flag", "1");
                    intent.setAction("com.whut.smartinspection.activity.FullInspectActivity");
                    sendBroadcast(intent);
                } else {
                    SystemUtils.showToast(MaintenanceActivity.this, "正在提交数据！");
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
                break;
            default:
        }
    }

    public void showDialog(View view, final List<DeviceType> parent) {
        WheelViewDialog dialog = new WheelViewDialog(this);
        List<String> list = new ArrayList<>();
        for (DeviceType deviceType : parent) {
            list.add(deviceType.getName());
        }
        dialog.setTitle("选择设备类型").setItems(list).setButtonText("确定").setDialogStyle(Color
                .parseColor("#6699ff")).setCount(5).setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
            @Override
            public void onItemClick(int position, String s) {
                deviceTypeName.setText(parent.get(position).getName());
                pDevice = position;
                patrolNameId = lpatrolNameId.get(position).getPatrolNameId();
                changeDeviceType1(position);
            }
        }).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
