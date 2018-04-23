package com.whut.smartinspection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;


/**
 * Created by 11142 on 4/1/2018.
 */

@Entity
public class OpenDoorRecord {
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;

    private String deviceName ;
    private String unlockingDate;
    private String lockingDate;

    @Generated(hash = 1241493233)
    public OpenDoorRecord(Long id, String deviceName, String unlockingDate,
            String lockingDate) {
        this.id = id;
        this.deviceName = deviceName;
        this.unlockingDate = unlockingDate;
        this.lockingDate = lockingDate;
    }
    @Generated(hash = 78612012)
    public OpenDoorRecord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDeviceName() {
        return this.deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getUnlockingDate() {
        return this.unlockingDate;
    }
    public void setUnlockingDate(String unlockingDate) {
        this.unlockingDate = unlockingDate;
    }
    public String getLockingDate() {
        return this.lockingDate;
    }
    public void setLockingDate(String lockingDate) {
        this.lockingDate = lockingDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"deviceName\":\"")
                .append(deviceName).append('\"');
        sb.append(",\"unlockingDate\":\"")
                .append(unlockingDate).append("\"");
        sb.append(",\"lockingDate\":\"")
                .append(lockingDate).append('\"');
        sb.append('}');
        return sb.toString();
    }

}
