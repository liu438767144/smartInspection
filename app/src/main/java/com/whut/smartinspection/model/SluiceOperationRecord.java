package com.whut.smartinspection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Created by lyz on 2018/4/3.
 * 倒闸操作记录
 */
@Entity
public class SluiceOperationRecord {
    @Property(nameInDb = "id")
    @Id()
    private Long id;//greenDao自动生成的ID

    private String idd;//任务ID
    private String valueChar;//单选 √ ×
    private String sluiceOperationRecordDate;//操作时间
    private String sluiceOperationContentId;//每条记录对应的任务内容idd
    private Long fid;//用于标识外键关系
    private Long wholeID;//一个作业卡对于一个wholeID

    @Generated(hash = 1461896893)
    public SluiceOperationRecord(Long id, String idd, String valueChar,
                                 String sluiceOperationRecordDate, String sluiceOperationContentId,
                                 Long fid, Long wholeID) {
        this.id = id;
        this.idd = idd;
        this.valueChar = valueChar;
        this.sluiceOperationRecordDate = sluiceOperationRecordDate;
        this.sluiceOperationContentId = sluiceOperationContentId;
        this.fid = fid;
        this.wholeID = wholeID;
    }

    @Generated(hash = 347009449)
    public SluiceOperationRecord() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdd() {
        return this.idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

    public String getValueChar() {
        return this.valueChar;
    }

    public void setValueChar(String valueChar) {
        this.valueChar = valueChar;
    }

    public String getSluiceOperationRecordDate() {
        return this.sluiceOperationRecordDate;
    }

    public void setSluiceOperationRecordDate(String sluiceOperationRecordDate) {
        this.sluiceOperationRecordDate = sluiceOperationRecordDate;
    }

    public String getSluiceOperationContentId() {
        return this.sluiceOperationContentId;
    }

    public void setSluiceOperationContentId(String sluiceOperationContentId) {
        this.sluiceOperationContentId = sluiceOperationContentId;
    }

    public Long getFid() {
        return this.fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getWholeID() {
        return this.wholeID;
    }

    public void setWholeID(Long wholeID) {
        this.wholeID = wholeID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append("\"idd\":\"")
                .append(idd).append('\"');
        sb.append(",\"valueChar\":\"")
                .append(valueChar).append('\"');
        sb.append(",\"sluiceOperationRecordDate\":\"")
                .append(sluiceOperationRecordDate).append("\"");
        sb.append(",\"sluiceOperationContentId\":\"")
                .append(sluiceOperationContentId).append('\"');
        sb.append('}');
        return sb.toString();
    }

}
