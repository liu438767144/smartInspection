package com.whut.smartinspection.component.db;

import com.whut.greendao.gen.DefectRegistrationRecordDao;
import com.whut.greendao.gen.DeviceDao;
import com.whut.greendao.gen.DeviceTypeDao;
import com.whut.greendao.gen.IntervalUnitDao;
import com.whut.greendao.gen.OpenDoorRecordDao;
import com.whut.greendao.gen.PatrolContentDao;
import com.whut.greendao.gen.PatrolTaskDetailDao;
import com.whut.greendao.gen.PatrolWorkCardDao;
import com.whut.greendao.gen.PerPatrolCardDao;
import com.whut.greendao.gen.RecordDao;
import com.whut.greendao.gen.SluiceHeadPageDao;
import com.whut.greendao.gen.SluiceOperationContentDao;
import com.whut.greendao.gen.SluiceOperationRecordDao;
import com.whut.greendao.gen.SubDao;
import com.whut.greendao.gen.TaskItemDao;
import com.whut.greendao.gen.WholePatrolCardDao;
import com.whut.greendao.gen.WholeSluiceCardDao;
import com.whut.smartinspection.application.SApplication;
import com.whut.smartinspection.model.DeviceType;
import com.whut.smartinspection.model.SluiceOperationRecord;
import com.whut.smartinspection.model.WholePatrolCard;
import com.whut.smartinspection.model.WholeSluiceCard;

/***
 * @author xiongbin
 * @date 2016-3-31 下午2:35:18
 * @version 1.0
 * @description
 */
public class BaseDbComponent {
    private static WholePatrolCardDao wholePatrolCardDao = SApplication.getInstance().getDaoSession().getWholePatrolCardDao();
    private static PerPatrolCardDao perPatrolCardDao = SApplication.getInstance().getDaoSession().getPerPatrolCardDao();
    private static RecordDao recordDao = SApplication.getInstance().getDaoSession().getRecordDao();
    private static DefectRegistrationRecordDao defectRegistrationRecordDao = SApplication.getInstance().getDaoSession().getDefectRegistrationRecordDao();
    private static OpenDoorRecordDao openDoorRecordDao = SApplication.getInstance().getDaoSession().getOpenDoorRecordDao();
    private static SluiceOperationRecordDao sluiceOperationRecordDao = SApplication.getInstance().getDaoSession().getSluiceOperationRecordDao();
    private static SluiceHeadPageDao sluiceHeadPageDao = SApplication.getInstance().getDaoSession().getSluiceHeadPageDao();
    private static WholeSluiceCardDao wholeSluiceCardDao = SApplication.getInstance().getDaoSession().getWholeSluiceCardDao();
    private static TaskItemDao taskItemDao = SApplication.getInstance().getDaoSession().getTaskItemDao();

    public static TaskItemDao getTaskItemDao(){
        return taskItemDao;
    }

    public static void deleteData(){
        wholePatrolCardDao.deleteAll();
        perPatrolCardDao.deleteAll();
        recordDao.deleteAll();
        defectRegistrationRecordDao.deleteAll();
        openDoorRecordDao.deleteAll();
        sluiceOperationRecordDao.deleteAll();
        sluiceHeadPageDao.deleteAll();
        wholeSluiceCardDao.deleteAll();
    }
}
