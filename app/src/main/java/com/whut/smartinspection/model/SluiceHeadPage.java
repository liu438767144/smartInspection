package com.whut.smartinspection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lyz on 2018/4/17.
 * 倒闸操作表头
 */

@Entity
public class SluiceHeadPage {
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;

    private String taskId;//倒闸操作任务ID
    private String sluiceDepartment;//单位
    private String sluiceId;//编号
    private String sluiceTask;//操作任务
    private String sluiceSendPeople;//发令人
    private String sluiceReceivePeople;//受令人
    private String sluiceSendDate;//发令时间
    private String sluiceStart;//开始时间
    private String sluiceEnd;//结束时间
    private short monitoringOperation;//监护下操作
    private short singleOperation;//单人操作
    private short maintenanceOperation;//检修人员操作
    private String remark;//备注

    @Generated(hash = 351150011)
    public SluiceHeadPage(Long id, String taskId, String sluiceDepartment,
                          String sluiceId, String sluiceTask, String sluiceSendPeople,
                          String sluiceReceivePeople, String sluiceSendDate, String sluiceStart,
                          String sluiceEnd, short monitoringOperation, short singleOperation,
                          short maintenanceOperation, String remark) {
        this.id = id;
        this.taskId = taskId;
        this.sluiceDepartment = sluiceDepartment;
        this.sluiceId = sluiceId;
        this.sluiceTask = sluiceTask;
        this.sluiceSendPeople = sluiceSendPeople;
        this.sluiceReceivePeople = sluiceReceivePeople;
        this.sluiceSendDate = sluiceSendDate;
        this.sluiceStart = sluiceStart;
        this.sluiceEnd = sluiceEnd;
        this.monitoringOperation = monitoringOperation;
        this.singleOperation = singleOperation;
        this.maintenanceOperation = maintenanceOperation;
        this.remark = remark;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSluiceDepartment() {
        return this.sluiceDepartment;
    }

    public void setSluiceDepartment(String sluiceDepartment) {
        this.sluiceDepartment = sluiceDepartment;
    }

    public String getSluiceId() {
        return this.sluiceId;
    }

    public void setSluiceId(String sluiceId) {
        this.sluiceId = sluiceId;
    }

    public String getSluiceTask() {
        return this.sluiceTask;
    }

    public void setSluiceTask(String sluiceTask) {
        this.sluiceTask = sluiceTask;
    }

    public String getSluiceSendPeople() {
        return this.sluiceSendPeople;
    }

    public void setSluiceSendPeople(String sluiceSendPeople) {
        this.sluiceSendPeople = sluiceSendPeople;
    }

    public String getSluiceReceivePeople() {
        return this.sluiceReceivePeople;
    }

    public void setSluiceReceivePeople(String sluiceReceivePeople) {
        this.sluiceReceivePeople = sluiceReceivePeople;
    }

    public String getSluiceSendDate() {
        return this.sluiceSendDate;
    }

    public void setSluiceSendDate(String sluiceSendDate) {
        this.sluiceSendDate = sluiceSendDate;
    }

    public String getSluiceStart() {
        return this.sluiceStart;
    }

    public void setSluiceStart(String sluiceStart) {
        this.sluiceStart = sluiceStart;
    }

    public String getSluiceEnd() {
        return this.sluiceEnd;
    }

    public void setSluiceEnd(String sluiceEnd) {
        this.sluiceEnd = sluiceEnd;
    }

    public short getMonitoringOperation() {
        return this.monitoringOperation;
    }

    public void setMonitoringOperation(short monitoringOperation) {
        this.monitoringOperation = monitoringOperation;
    }

    public short getSingleOperation() {
        return this.singleOperation;
    }

    public void setSingleOperation(short singleOperation) {
        this.singleOperation = singleOperation;
    }

    public short getMaintenanceOperation() {
        return this.maintenanceOperation;
    }

    public void setMaintenanceOperation(short maintenanceOperation) {
        this.maintenanceOperation = maintenanceOperation;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Generated(hash = 1153898740)
    public SluiceHeadPage() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append("\"taskId\":\"")
                .append(taskId).append('\"');
        sb.append(",\"sluiceDepartment\":\"")
                .append(sluiceDepartment).append('\"');
        sb.append(",\"sluiceId\":\"")
                .append(sluiceId).append("\"");
        sb.append(",\"sluiceTask\":\"")
                .append(sluiceTask).append('\"');
        sb.append(",\"sluiceSendPeople\":\"")
                .append(sluiceSendPeople).append("\"");
        sb.append(",\"sluiceReceivePeople\":\"")
                .append(sluiceReceivePeople).append('\"');
        sb.append(",\"sluiceSendDate\":\"")
                .append(sluiceSendDate).append('\"');
        sb.append(",\"sluiceStart\":\"")
                .append(sluiceStart).append('\"');
        sb.append(",\"sluiceEnd\":\"")
                .append(sluiceEnd).append('\"');
        sb.append(",\"monitoringOperation\":\"")
                .append(monitoringOperation).append('\"');
        sb.append(",\"singleOperation\":\"")
                .append(singleOperation).append('\"');
        sb.append(",\"maintenanceOperation\":\"")
                .append(maintenanceOperation).append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
