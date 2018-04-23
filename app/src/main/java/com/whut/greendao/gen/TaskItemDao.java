package com.whut.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.whut.smartinspection.model.TaskItem;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TASK_ITEM".
*/
public class TaskItemDao extends AbstractDao<TaskItem, Long> {

    public static final String TABLENAME = "TASK_ITEM";

    /**
     * Properties of entity TaskItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "id");
        public final static Property Idd = new Property(1, String.class, "idd", false, "IDD");
        public final static Property Worker = new Property(2, String.class, "worker", false, "WORKER");
        public final static Property StartDate = new Property(3, java.util.Date.class, "startDate", false, "START_DATE");
        public final static Property EndDate = new Property(4, java.util.Date.class, "endDate", false, "END_DATE");
        public final static Property Status = new Property(5, int.class, "status", false, "STATUS");
        public final static Property TaskType = new Property(6, String.class, "taskType", false, "TASK_TYPE");
        public final static Property TaskTypeName = new Property(7, String.class, "taskTypeName", false, "TASK_TYPE_NAME");
        public final static Property TaskIcon = new Property(8, int.class, "taskIcon", false, "TASK_ICON");
        public final static Property Common = new Property(9, String.class, "common", false, "COMMON");
    }


    public TaskItemDao(DaoConfig config) {
        super(config);
    }
    
    public TaskItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TASK_ITEM\" (" + //
                "\"id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"IDD\" TEXT," + // 1: idd
                "\"WORKER\" TEXT," + // 2: worker
                "\"START_DATE\" INTEGER," + // 3: startDate
                "\"END_DATE\" INTEGER," + // 4: endDate
                "\"STATUS\" INTEGER NOT NULL ," + // 5: status
                "\"TASK_TYPE\" TEXT," + // 6: taskType
                "\"TASK_TYPE_NAME\" TEXT," + // 7: taskTypeName
                "\"TASK_ICON\" INTEGER NOT NULL ," + // 8: taskIcon
                "\"COMMON\" TEXT);"); // 9: common
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TASK_ITEM\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TaskItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String idd = entity.getIdd();
        if (idd != null) {
            stmt.bindString(2, idd);
        }
 
        String worker = entity.getWorker();
        if (worker != null) {
            stmt.bindString(3, worker);
        }
 
        java.util.Date startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindLong(4, startDate.getTime());
        }
 
        java.util.Date endDate = entity.getEndDate();
        if (endDate != null) {
            stmt.bindLong(5, endDate.getTime());
        }
        stmt.bindLong(6, entity.getStatus());
 
        String taskType = entity.getTaskType();
        if (taskType != null) {
            stmt.bindString(7, taskType);
        }
 
        String taskTypeName = entity.getTaskTypeName();
        if (taskTypeName != null) {
            stmt.bindString(8, taskTypeName);
        }
        stmt.bindLong(9, entity.getTaskIcon());
 
        String common = entity.getCommon();
        if (common != null) {
            stmt.bindString(10, common);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TaskItem entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String idd = entity.getIdd();
        if (idd != null) {
            stmt.bindString(2, idd);
        }
 
        String worker = entity.getWorker();
        if (worker != null) {
            stmt.bindString(3, worker);
        }
 
        java.util.Date startDate = entity.getStartDate();
        if (startDate != null) {
            stmt.bindLong(4, startDate.getTime());
        }
 
        java.util.Date endDate = entity.getEndDate();
        if (endDate != null) {
            stmt.bindLong(5, endDate.getTime());
        }
        stmt.bindLong(6, entity.getStatus());
 
        String taskType = entity.getTaskType();
        if (taskType != null) {
            stmt.bindString(7, taskType);
        }
 
        String taskTypeName = entity.getTaskTypeName();
        if (taskTypeName != null) {
            stmt.bindString(8, taskTypeName);
        }
        stmt.bindLong(9, entity.getTaskIcon());
 
        String common = entity.getCommon();
        if (common != null) {
            stmt.bindString(10, common);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TaskItem readEntity(Cursor cursor, int offset) {
        TaskItem entity = new TaskItem( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // idd
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // worker
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // startDate
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)), // endDate
            cursor.getInt(offset + 5), // status
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // taskType
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // taskTypeName
            cursor.getInt(offset + 8), // taskIcon
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // common
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TaskItem entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdd(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setWorker(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStartDate(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setEndDate(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
        entity.setStatus(cursor.getInt(offset + 5));
        entity.setTaskType(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTaskTypeName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTaskIcon(cursor.getInt(offset + 8));
        entity.setCommon(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TaskItem entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TaskItem entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TaskItem entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}