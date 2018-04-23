package com.whut.smartinspection.adapters;

import android.content.Context;
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
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.component.handler.EventCallable;
import com.whut.smartinspection.model.InterUnitRecord;
import com.whut.smartinspection.model.IntervalUnit;
import com.wx.wheelview.adapter.BaseWheelAdapter;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fortuner on 2018/1/1.
 */

public class DataInputWheelAdapter extends BaseWheelAdapter<IntervalUnit> {
//    private List<PatrolContent> patrolContent;
    private List<InterUnitRecord> interUnitRecords;
    private Context mContext;
    private EventCallable mcallable;
    private List<IntervalUnit> intervalUnits;

    public DataInputWheelAdapter(Context context) {
        mContext = context;
    }
    public DataInputWheelAdapter(Context context, List<IntervalUnit> intervalUnits, List<InterUnitRecord> interUnitRecords) {
        this.mContext = context;
        this.intervalUnits = intervalUnits;
        this.interUnitRecords = interUnitRecords;
    }

    @Override
    protected View bindView(final int position, View convertView, ViewGroup parent) {
        TaskViewHolder1 holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_data_input, null);
            if(!SApplication.isPad(mContext)){
                ListView.LayoutParams params = new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.WRAP_CONTENT);//设置宽度和高度
                convertView.setLayoutParams(params);
            }
//            convertView.setMinimumHeight(100);
            holder = new TaskViewHolder1(convertView);
            convertView.setTag(holder);
        } else {
            holder = (TaskViewHolder1) convertView.getTag();
        }
//        IntervalUnit item = intervalUnits.get(position);//一个间隔名称
//        Record record = records.get(position);//巡视结果
//        if("0".equals(item.getPatrolContentTypeNo())){
//            holder.radioGroup.setVisibility(View.VISIBLE);
//            holder.llDegree.setVisibility(View.GONE);
//            holder.radioGroup3.setVisibility(View.GONE);
//        }else if("1".equals(item.getPatrolContentTypeNo())){
//            holder.radioGroup.setVisibility(View.GONE);
//            holder.llDegree.setVisibility(View.VISIBLE);
//            holder.radioGroup3.setVisibility(View.GONE);
//        }else if("3".equals(item.getPatrolContentTypeNo())){
//            holder.radioGroup.setVisibility(View.GONE);
//            holder.llDegree.setVisibility(View.GONE);
//            holder.radioGroup3.setVisibility(View.VISIBLE);
//            if(holder.radioGroup3.getChildCount()<1) {
//                try {
//                    String unit = item.getUnit();
//                    String[] units = unit.split(",");
//                    for (int i = 0; i < units.length; i++) {
//                        RadioButton tempButton = new RadioButton(mContext);
//                        tempButton.setText(units[i]);
//                        tempButton.setTextSize(20);
//                        tempButton.setId(i);
//                        holder.radioGroup3.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//        holder.tvInterUnit.setText(position+"、"+item.getName());
//        holder.tvDegree.setText(item.getPatrolContentName());
//        if(-1== record.getValueFloat()) {
//            holder.etDegree.setText("");
//        } else{
//            holder.etDegree.setText(String.valueOf(record.getValueFloat()));
//        }
//        holder.etDegree.setSelection(holder.etDegree.getText().toString().length());//让光标在文本末
//        holder.tvUnit.setText(item.getUnit());

        //防止串行混乱
//        holder.rgInterUnit.setOnCheckedChangeListener(null);
//        holder.rgInterUnit.clearCheck();
//        if("T".equals(record.getValueChar())){
//            holder.rgInterUnit.check(R.id.rb_correct);
//        }else if("F".equals(record.getValueChar())){
//            holder.rgInterUnit.check(R.id.rb_error);
//        }else{
//            holder.rgInterUnit.clearCheck();
//        }
//        //如果已有值 需设置显示
//        holder.radioGroup3.setOnCheckedChangeListener(null);//防止串行混乱
//        holder.radioGroup3.clearCheck();
//        if("0".equals(record.getValueChar())){
//            RadioButton r0 = (RadioButton)holder.radioGroup3.getChildAt(0);
//            r0.setChecked(true);
//        }else if ("1".equals(record.getValueChar())){
//            RadioButton r1 = (RadioButton)holder.radioGroup3.getChildAt(1);
//            r1.setChecked(true);
//        }else{//无值则清空
//            holder.radioGroup3.clearCheck();
//        }
        //绑定RadioGroup点击事件
        holder.rgInterUnit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Integer pos = (Integer) group.getTag();
//                Record record = records.get(pos);
//                if(checkedId == R.id.rb_correct){//√
//                    record.setValueChar("T");
//                }else if(checkedId == R.id.rb_error){//×
//                    record.setValueChar("F");
//                }
//                record.setPatrolRecordDate(System.currentTimeMillis());
            }
        });

//        final TaskViewHolder1 finalHolder = holder;
//        holder.radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                Integer pos = (Integer) group.getTag();
//                Record record = records.get(pos);
//                int i = finalHolder.radioGroup3.getCheckedRadioButtonId();
//                String s = ""+i;
//                record.setValueChar(s);
//                record.setPatrolRecordDate(System.currentTimeMillis());
//            }
//        });
        holder.rgInterUnit.setTag(new Integer(position));
//        holder.radioGroup3.setTag(new Integer(position));
//        holder.etDegree.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    finalHolder.etDegree.addTextChangedListener(new TextWatcher(){
//                        @Override
//                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                        }
//                        @Override
//                        public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        }
//                        @Override
//                        public void afterTextChanged(Editable s) {
//                            String etNumber = finalHolder.etDegree.getText().toString();
//                            Record record = records.get(position);
//                            if(isDouble(etNumber)) {
//                                record.setValueFloat(Float.parseFloat(etNumber));
//                            }
//                        }
//                    });
//                }else {
//                    finalHolder.etDegree.clearTextChangedListeners();
//                }
//            }
//        });
        return convertView;
    }

    /**
     * 判断浮点数（double和float）
     * @param str
     * @return
     */
    private boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
    boolean isAllItemEnable=false;
    @Override
    public boolean isEnabled(int position){
        return isAllItemEnable;
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
