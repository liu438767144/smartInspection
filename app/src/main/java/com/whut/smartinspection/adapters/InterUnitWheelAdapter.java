package com.whut.smartinspection.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.whut.smartinspection.R;
import com.whut.smartinspection.activity.DataInputActivity;
import com.whut.smartinspection.activity.DefectFullInspectActivity;
import com.whut.smartinspection.activity.PatrolStandardActivity;
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.component.handler.EventCallable;
import com.whut.smartinspection.model.InterUnitRecord;
import com.whut.smartinspection.model.IntervalUnit;
import com.wx.wheelview.adapter.BaseWheelAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 全面巡视适配器（锦江变新巡视作业卡）
 * Created by Fortuner on 2018/1/1.
 */
public class InterUnitWheelAdapter extends BaseWheelAdapter<IntervalUnit> {
    private List<InterUnitRecord> interUnitRecords;
    private Context mContext;
    private EventCallable mcallable;
    private List<IntervalUnit> intervalUnits;

    public InterUnitWheelAdapter(Context context) {
        mContext = context;
    }
    public InterUnitWheelAdapter(Context context, List<IntervalUnit> intervalUnits, List<InterUnitRecord> interUnitRecords) {
        this.mContext = context;
        this.intervalUnits = intervalUnits;
        this.interUnitRecords = interUnitRecords;
    }

    @Override
    protected View bindView(final int position, View convertView, ViewGroup parent) {
        TaskViewHolder1 holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_inter_unit, null);
            if(!SApplication.isPad(mContext)){
                //设置一个Item的宽度和高度（232=1600/5)   1600为手机屏幕大约的高度 每个手机不一样
                ListView.LayoutParams params = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.MATCH_PARENT);
                convertView.setLayoutParams(params);
            }
            holder = new TaskViewHolder1(convertView);
            convertView.setTag(holder);
        } else {
            holder = (TaskViewHolder1) convertView.getTag();
        }
        //一个间隔名称
        final IntervalUnit item = intervalUnits.get(position);
        holder.tvInterUnit.setText(position+"、"+item.getName());
        //数据输入
        holder.dataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DataInputActivity.class);
                intent.putExtra("item",item);
                mContext.startActivity(intent);
            }
        });
        //巡视标准
        holder.patrolStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PatrolStandardActivity.class);
                intent.putExtra("item",item);
                mContext.startActivity(intent);
            }
        });
        //绑定RadioGroup点击事件
        holder.rgInterUnit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Integer pos = (Integer) group.getTag();
                if(checkedId == R.id.rb_correct){//√
                }else if(checkedId == R.id.rb_error){//×
                    //上报缺陷
                    Intent intent = new Intent(mContext, DefectFullInspectActivity.class);
                    intent.putExtra("item",item);
                    mContext.startActivity(intent);
                }
            }
        });
        holder.rgInterUnit.setTag(new Integer(position));
        return convertView;
    }

    /**
     * 关联item_inter_unit.xml里面的id
     */
    public class TaskViewHolder1 {
        @BindView(R.id.tv_inter_unit)
        public TextView tvInterUnit;//间隔名称
        @BindView(R.id.rg_inter_unit)
        public RadioGroup rgInterUnit;//选择项
        @BindView(R.id.data_input)
        public Button dataInput;//录入数据
        @BindView(R.id.patrol_standard)
        public Button patrolStandard;//巡视标准
        public TaskViewHolder1(View view){
            ButterKnife.bind(this,view);
        }
    }

}
