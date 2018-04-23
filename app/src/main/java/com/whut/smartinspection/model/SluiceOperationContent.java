package com.whut.smartinspection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lyz on 2018/4/3.
 * 倒闸操作任务内容
 */
@Entity
public class SluiceOperationContent {
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;

    private String idd;//倒闸操作任务ID
    private int no;//顺序编号
    private String content;//操作项目
    private String date;//添加时间
    private String patrolNameId;//倒闸操作作业卡ID

    @Generated(hash = 1486027729)
    public SluiceOperationContent(Long id, String idd, int no, String content,
            String date, String patrolNameId) {
        this.id = id;
        this.idd = idd;
        this.no = no;
        this.content = content;
        this.date = date;
        this.patrolNameId = patrolNameId;
    }
    @Generated(hash = 922931642)
    public SluiceOperationContent() {
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
    public int getNo() {
        return this.no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getPatrolNameId() {
        return this.patrolNameId;
    }
    public void setPatrolNameId(String patrolNameId) {
        this.patrolNameId = patrolNameId;
    }

}
