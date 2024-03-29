package com.whut.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.whut.smartinspection.model.WholeSluiceCard;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WHOLE_SLUICE_CARD".
*/
public class WholeSluiceCardDao extends AbstractDao<WholeSluiceCard, Long> {

    public static final String TABLENAME = "WHOLE_SLUICE_CARD";

    /**
     * Properties of entity WholeSluiceCard.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PatrolHeadPageId = new Property(1, String.class, "patrolHeadPageId", false, "PATROL_HEAD_PAGE_ID");
        public final static Property Flag = new Property(2, boolean.class, "flag", false, "FLAG");
    }

    private DaoSession daoSession;


    public WholeSluiceCardDao(DaoConfig config) {
        super(config);
    }
    
    public WholeSluiceCardDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WHOLE_SLUICE_CARD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PATROL_HEAD_PAGE_ID\" TEXT," + // 1: patrolHeadPageId
                "\"FLAG\" INTEGER NOT NULL );"); // 2: flag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WHOLE_SLUICE_CARD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WholeSluiceCard entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String patrolHeadPageId = entity.getPatrolHeadPageId();
        if (patrolHeadPageId != null) {
            stmt.bindString(2, patrolHeadPageId);
        }
        stmt.bindLong(3, entity.getFlag() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WholeSluiceCard entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String patrolHeadPageId = entity.getPatrolHeadPageId();
        if (patrolHeadPageId != null) {
            stmt.bindString(2, patrolHeadPageId);
        }
        stmt.bindLong(3, entity.getFlag() ? 1L: 0L);
    }

    @Override
    protected final void attachEntity(WholeSluiceCard entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public WholeSluiceCard readEntity(Cursor cursor, int offset) {
        WholeSluiceCard entity = new WholeSluiceCard( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // patrolHeadPageId
            cursor.getShort(offset + 2) != 0 // flag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WholeSluiceCard entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPatrolHeadPageId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFlag(cursor.getShort(offset + 2) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(WholeSluiceCard entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(WholeSluiceCard entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(WholeSluiceCard entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
