package com.whut.smartinspection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lyz on 2018/3/23.
 * 缺陷登记记录
 */

@Entity
public class DefectRegistrationRecord {

    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;

    private String no;//缺陷编号
    private String findDate;//发现日期
    private String findPerson;//发现人
    private String deviceType;//设备类型
    private String defectDepartment;//消缺单位
    private String defectLevel;//缺陷性质
    private String defectContent;//缺陷内容
    private short isReportSchedule;//是否汇报调度
    private short isEntered;//是否已录入PMS
    private short isEliminateDefect;//是否消缺
    private String processingDetails;//处理详情
    private String eliminationDefectDate;//消缺时间
    private String remark;//备注

    @Generated(hash = 596464197)
    public DefectRegistrationRecord(Long id, String no, String findDate,
            String findPerson, String deviceType, String defectDepartment,
            String defectLevel, String defectContent, short isReportSchedule,
            short isEntered, short isEliminateDefect, String processingDetails,
            String eliminationDefectDate, String remark) {
        this.id = id;
        this.no = no;
        this.findDate = findDate;
        this.findPerson = findPerson;
        this.deviceType = deviceType;
        this.defectDepartment = defectDepartment;
        this.defectLevel = defectLevel;
        this.defectContent = defectContent;
        this.isReportSchedule = isReportSchedule;
        this.isEntered = isEntered;
        this.isEliminateDefect = isEliminateDefect;
        this.processingDetails = processingDetails;
        this.eliminationDefectDate = eliminationDefectDate;
        this.remark = remark;
    }

    @Generated(hash = 1881154519)
    public DefectRegistrationRecord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNo() {
        return this.no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getFindDate() {
        return this.findDate;
    }
    public void setFindDate(String findDate) {
        this.findDate = findDate;
    }
    public String getFindPerson() {
        return this.findPerson;
    }
    public void setFindPerson(String findPerson) {
        this.findPerson = findPerson;
    }
    public String getDeviceType() {
        return this.deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getDefectDepartment() {
        return this.defectDepartment;
    }
    public void setDefectDepartment(String defectDepartment) {
        this.defectDepartment = defectDepartment;
    }
    public String getDefectLevel() {
        return this.defectLevel;
    }
    public void setDefectLevel(String defectLevel) {
        this.defectLevel = defectLevel;
    }
    public String getDefectContent() {
        return this.defectContent;
    }
    public void setDefectContent(String defectContent) {
        this.defectContent = defectContent;
    }
    public short getIsReportSchedule() {
        return this.isReportSchedule;
    }
    public void setIsReportSchedule(short isReportSchedule) {
        this.isReportSchedule = isReportSchedule;
    }
    public short getIsEntered() {
        return this.isEntered;
    }
    public void setIsEntered(short isEntered) {
        this.isEntered = isEntered;
    }
    public short getIsEliminateDefect() {
        return this.isEliminateDefect;
    }
    public void setIsEliminateDefect(short isEliminateDefect) {
        this.isEliminateDefect = isEliminateDefect;
    }
    public String getProcessingDetails() {
        return this.processingDetails;
    }
    public void setProcessingDetails(String processingDetails) {
        this.processingDetails = processingDetails;
    }
    public String getEliminationDefectDate() {
        return this.eliminationDefectDate;
    }
    public void setEliminationDefectDate(String eliminationDefectDate) {
        this.eliminationDefectDate = eliminationDefectDate;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"no\":\"")
                .append(no).append('\"');
        sb.append(",\"findDate\":\"")
                .append(findDate).append("\"");
        sb.append(",\"findPerson\":\"")
                .append(findPerson).append('\"');
        sb.append(",\"deviceType\":\"")
                .append(deviceType).append("\"");
        sb.append(",\"defectDepartment\":\"")
                .append(defectDepartment).append('\"');
        sb.append(",\"defectLevel\":\"")
                .append(defectLevel).append('\"');
        sb.append(",\"defectContent\":\"")
                .append(defectContent).append('\"');
        sb.append(",\"isReportSchedule\":\"")
                .append(isReportSchedule).append('\"');
        sb.append(",\"isEntered\":\"")
                .append(isEntered).append('\"');
        sb.append(",\"isEliminateDefect\":\"")
                .append(isEliminateDefect).append('\"');
        sb.append(",\"processingDetails\":\"")
                .append(processingDetails).append('\"');
        sb.append(",\"eliminationDefectDate\":\"")
                .append(eliminationDefectDate).append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
