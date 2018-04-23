package com.whut.smartinspection.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.whut.greendao.gen.DaoSession;
import com.whut.greendao.gen.SluiceOperationRecordDao;
import com.whut.greendao.gen.WholeSluiceCardDao;


/**
 * Created by lyz on 2018/4/17.
 */

@Entity
public class WholeSluiceCard {
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long id;

    private String patrolHeadPageId;

    @ToMany(referencedJoinProperty = "fid")
    private List<SluiceOperationRecord> sluiceOperationRecords;

    private boolean flag ;//标记是否提交到服务器

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 2126595006)
    private transient WholeSluiceCardDao myDao;

    @Generated(hash = 379152065)
    public WholeSluiceCard(Long id, String patrolHeadPageId, boolean flag) {
        this.id = id;
        this.patrolHeadPageId = patrolHeadPageId;
        this.flag = flag;
    }

    @Generated(hash = 598849620)
    public WholeSluiceCard() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatrolHeadPageId() {
        return this.patrolHeadPageId;
    }

    public void setPatrolHeadPageId(String patrolHeadPageId) {
        this.patrolHeadPageId = patrolHeadPageId;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1952096562)
    public List<SluiceOperationRecord> getSluiceOperationRecords() {
        if (sluiceOperationRecords == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SluiceOperationRecordDao targetDao = daoSession
                    .getSluiceOperationRecordDao();
            List<SluiceOperationRecord> sluiceOperationRecordsNew = targetDao
                    ._queryWholeSluiceCard_SluiceOperationRecords(id);
            synchronized (this) {
                if (sluiceOperationRecords == null) {
                    sluiceOperationRecords = sluiceOperationRecordsNew;
                }
            }
        }
        return sluiceOperationRecords;
    }

    public void setSluiceOperationRecords(List<SluiceOperationRecord> sluiceOperationRecords) {
        this.sluiceOperationRecords = sluiceOperationRecords;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 743822179)
    public synchronized void resetSluiceOperationRecords() {
        sluiceOperationRecords = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1739841491)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWholeSluiceCardDao() : null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"patrolHeadPageId\":\"")
                .append(patrolHeadPageId).append('\"');
        sb.append(",\"sluiceOperationRecords\":")
                .append(sluiceOperationRecords);
        sb.append('}');
        return sb.toString();
    }

}
