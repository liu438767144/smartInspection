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
    private static SubDao subDao = SApplication.getInstance().getDaoSession().getSubDao();
    private static DeviceTypeDao deviceTypeDao = SApplication.getInstance().getDaoSession().getDeviceTypeDao();
    private static IntervalUnitDao intervalUnitDao = SApplication.getInstance().getDaoSession().getIntervalUnitDao();
    private static DeviceDao deviceDao = SApplication.getInstance().getDaoSession().getDeviceDao();
    private static PatrolContentDao patrolContentDao = SApplication.getInstance().getDaoSession().getPatrolContentDao();
    private static PatrolWorkCardDao patrolWorkCardDao = SApplication.getInstance().getDaoSession().getPatrolWorkCardDao();
    private static TaskItemDao taskItemDao = SApplication.getInstance().getDaoSession().getTaskItemDao();
    private static PatrolTaskDetailDao patrolTaskDetailDao = SApplication.getInstance().getDaoSession().getPatrolTaskDetailDao();
    private static SluiceOperationContentDao sluiceOperationContentDao = SApplication.getInstance().getDaoSession().getSluiceOperationContentDao();

    public static void deleteData(){
        subDao.deleteAll();
        taskItemDao.deleteAll();
        deviceTypeDao.deleteAll();
        intervalUnitDao.deleteAll();
        deviceDao.deleteAll();
        patrolContentDao.deleteAll();
        patrolTaskDetailDao.deleteAll();
        patrolWorkCardDao.deleteAll();
        wholePatrolCardDao.deleteAll();
        perPatrolCardDao.deleteAll();
        recordDao.deleteAll();
        defectRegistrationRecordDao.deleteAll();
        openDoorRecordDao.deleteAll();
        sluiceOperationRecordDao.deleteAll();
        sluiceOperationContentDao.deleteAll();
        sluiceHeadPageDao.deleteAll();
        wholeSluiceCardDao.deleteAll();
    }
}
