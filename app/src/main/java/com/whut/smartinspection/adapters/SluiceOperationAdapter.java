package com.whut.smartinspection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.whut.greendao.gen.SluiceOperationRecordDao;
import com.whut.smartinspection.R;
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.model.SluiceOperationContent;
import com.whut.smartinspection.model.SluiceOperationRecord;
import com.whut.smartinspection.utils.TimeUtils;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyz on 2018/4/2.
 */

public class SluiceOperationAdapter extends BaseAdapter {

    private List<SluiceOperationContent> sluiceOperationContent;
    private List<SluiceOperationRecord> sluiceOperationRecords;
    private Context mContext;
    
    public SluiceOperationAdapter(Context mContext, List<SluiceOperationContent> sluiceOperationContent, List<SluiceOperationRecord> sluiceOperationRecords) {
        this.sluiceOperationContent = sluiceOperationContent;
        this.sluiceOperationRecords = sluiceOperationRecords;
        this.mContext = mContext;
    }

    SluiceOperationRecordDao sluiceOperationRecordDao = SApplication.getInstance().getDaoSession().getSluiceOperationRecordDao();

    @Override
    public int getCount() {
        return sluiceOperationContent.size();
    }

    @Override
    public Object getItem(int position) {
        return sluiceOperationContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sluice, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //显示任务内容
        SluiceOperationContent item = sluiceOperationContent.get(position);
        SluiceOperationRecord sluiceOperationRecord = sluiceOperationRecords.get(position);
        vh.sluiceContent.setText(item.getNo() + "." + item.getContent());

        //取消对其监听，清空所有选项，否则会出现选项串行的效果。
        vh.radioGroup.setOnCheckedChangeListener(null);
        vh.radioGroup.clearCheck();
        vh.operateTime.setText(null);
        //显示操作记录
        if("T".equals(sluiceOperationRecord.getValueChar())){
            vh.radioGroup.check(R.id.rb_true);
            vh.operateTime.setText(sluiceOperationRecord.getSluiceOperationRecordDate());
        }else if("F".equals(sluiceOperationRecord.getValueChar())){
            vh.radioGroup.check(R.id.rb_false);
            vh.operateTime.setText(sluiceOperationRecord.getSluiceOperationRecordDate());
        }else{
            vh.radioGroup.clearCheck();
        }

        final ViewHolder finalVh = vh;
        vh.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Integer position = (Integer) radioGroup.getTag();
                String time = TimeUtils.setCurrentTime3();
                SluiceOperationRecord sluiceOperationRecord = sluiceOperationRecords.get(position);
                if (finalVh.rbTrue.getId() == checkedId){
                    finalVh.operateTime.setText(time);
                    sluiceOperationRecord.setValueChar("T");
                }else if (finalVh.rbFalse.getId() == checkedId){
                    finalVh.operateTime.setText(time);
                    sluiceOperationRecord.setValueChar("F");
                }
                sluiceOperationRecord.setSluiceOperationRecordDate(time);
                sluiceOperationRecordDao.insertOrReplace(sluiceOperationRecord);

            }
        });
        vh.radioGroup.setTag(new Integer(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.sluice_content)//任务内容
        TextView sluiceContent;
        @BindView(R.id.rb_true)
        RadioButton rbTrue;
        @BindView(R.id.rb_false)
        RadioButton rbFalse;
        @BindView(R.id.rg_radioGroup)
        RadioGroup radioGroup;
        @BindView(R.id.operate_time)//操作时间
        EditText operateTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
