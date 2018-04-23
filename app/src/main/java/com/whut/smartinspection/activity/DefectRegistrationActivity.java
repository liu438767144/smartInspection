package com.whut.smartinspection.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.whut.greendao.gen.DefectRegistrationRecordDao;
import com.whut.greendao.gen.RecordDao;
import com.whut.smartinspection.R;
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.model.DefectRegistrationRecord;
import com.whut.smartinspection.model.Record;
import com.whut.smartinspection.utils.ButtonUtils;
import com.whut.smartinspection.utils.SystemUtils;
import com.whut.smartinspection.utils.TimeUtils;
import com.whut.smartinspection.widgets.CustomToolBar;
import com.whut.smartlibrary.base.SwipeBackActivity;
import org.greenrobot.greendao.query.QueryBuilder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 缺陷登记
 */
public class DefectRegistrationActivity extends SwipeBackActivity {

    @BindView(R.id.et_defect_num)
    EditText defectNum;
    @BindView(R.id.et_find_date)
    EditText findDate;
    @BindView(R.id.find_person)
    Spinner findPerson;
    @BindView(R.id.device_type)
    Spinner deviceType;
    @BindView(R.id.defect_department)
    Spinner defectDepartment;
    @BindView(R.id.defect_level)
    Spinner defectLevel;
    @BindView(R.id.et_defect_content)
    EditText defectContent;
    @BindView(R.id.isReport_schedule)
    Spinner isReportSchedule;
    @BindView(R.id.isEntered)
    Spinner isEntered;
    @BindView(R.id.isEliminate_defect)
    Spinner isEliminateDefect;
    @BindView(R.id.et_processing_details)
    EditText processingDetails;
    @BindView(R.id.et_elimination_defect_date)
    EditText eliminationDefectDate;
    @BindView(R.id.et_remark)
    EditText remark;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.btn_reset)
    Button btnReset;
    @BindView(R.id.btn_save)
    Button btnSave;


    private List<DefectRegistrationRecord> defectRegistrationRecords;

    // 定义显示时间控件
    private Calendar calendar; //通过Calendar获取系统时间
    private int mYear;
    private int mMonth;
    private int mDay;

    DefectRegistrationRecordDao defectRegistrationRecordDao = SApplication.getInstance().getDaoSession()
            .getDefectRegistrationRecordDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_registration);
        ButtonUtils.setDIFF(30 * 60 * 1000);//设置提交按钮三十秒内不能点击
        ButtonUtils.setLastClickTime(0);
        ButterKnife.bind(this);
        CustomToolBar.goBack(DefectRegistrationActivity.this);//返回按钮监听

        initdata();//初始化数据

        //消缺日期选择
        eliminationDefectDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        eliminationDefectDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus)
                    showDatePickDlg();
            }
        });
    }

    private void initdata() {
        findDate.setText(TimeUtils.setCurrentTime2());
    }

    private void resetData(){
        defectNum.setText(null);
        findDate.setText(TimeUtils.setCurrentTime());
        findPerson.setSelection(0);
        deviceType.setSelection(0);
        defectDepartment.setSelection(0);
        defectLevel.setSelection(0);
        defectContent.setText(null);
        isReportSchedule.setSelection(0);
        isEntered.setSelection(0);
        isEliminateDefect.setSelection(0);
        processingDetails.setText(null);
        eliminationDefectDate.setText(null);
        remark.setText(null);
    }

    //保存数据
    private void saveRecords() {

        DefectRegistrationRecord defectRegistrationRecord = new DefectRegistrationRecord();

        defectRegistrationRecord.setNo(defectNum.getText().toString());
        defectRegistrationRecord.setFindDate(findDate.getText().toString());
        defectRegistrationRecord.setFindPerson(findPerson.getSelectedItem().toString());
        defectRegistrationRecord.setDeviceType(deviceType.getSelectedItem().toString());
        defectRegistrationRecord.setDefectDepartment(defectDepartment.getSelectedItem().toString());
        defectRegistrationRecord.setDefectLevel(defectLevel.getSelectedItem().toString());
        defectRegistrationRecord.setDefectContent(defectContent.getText().toString());
        defectRegistrationRecord.setProcessingDetails(processingDetails.getText().toString());
        if ("是".equals(isReportSchedule.getSelectedItem().toString())) {
            defectRegistrationRecord.setIsReportSchedule((short) 1);
        } else {
            defectRegistrationRecord.setIsReportSchedule((short) 0);
        }
        if ("是".equals(isEntered.getSelectedItem().toString())) {
            defectRegistrationRecord.setIsEntered((short) 1);
        } else {
            defectRegistrationRecord.setIsEntered((short) 0);
        }
        if ("是".equals(isEliminateDefect.getSelectedItem().toString())) {
            defectRegistrationRecord.setIsEliminateDefect((short) 1);
        } else {
            defectRegistrationRecord.setIsEliminateDefect((short) 0);
        }
        defectRegistrationRecord.setEliminationDefectDate(eliminationDefectDate.getText().toString());
        defectRegistrationRecord.setRemark(remark.getText().toString());

        defectRegistrationRecordDao.insertOrReplace(defectRegistrationRecord);
    }

    //日期选择器
    private void showDatePickDlg() {
        calendar = Calendar.getInstance();
        new DatePickerDialog(DefectRegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                mYear = year;
                mMonth = month;
                mDay = day;
                // 更新EditText控件日期，小于10时在前面加0
                eliminationDefectDate.setText(new StringBuilder()
                        .append(mYear)
                        .append("-")
                        .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                        .append("-")
                        .append((mDay < 10) ? "0" + mDay : mDay));
            }
        }, calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick({R.id.btn_reset, R.id.btn_save, R.id.toolbar_right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset://重置
                resetData();
                break;
            case R.id.btn_save:{//保存
                saveRecords();
                Toast.makeText(DefectRegistrationActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.toolbar_right_tv://提交
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("请确认数据以保存！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (!ButtonUtils.isFastDoubleClick(R.id.btn_login_user_login)) {
                                    QueryBuilder<DefectRegistrationRecord> qbRecord = defectRegistrationRecordDao.queryBuilder();
                                    defectRegistrationRecords = qbRecord.list();
                                    Log.d("--defectRegistration--",defectRegistrationRecords.toString());
                                    Intent intent = new Intent();
                                    intent.putExtra("flag", "2");
                                    intent.setAction("com.whut.smartinspection.activity.FullInspectActivity");
                                    sendBroadcast(intent);
                                } else {
                                    SystemUtils.showToast(DefectRegistrationActivity.this, "正在提交数据！");
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
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create().show();
                break;
            default:
                break;
        }
    }
}
