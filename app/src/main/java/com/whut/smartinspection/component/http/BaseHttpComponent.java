package com.whut.smartinspection.component.http;

import com.zhy.http.okhttp.cookie.store.CookieStore;

public class BaseHttpComponent {
	public static final String URL = "http://115.159.108.163:8009/smartIsland/";

	public final String contentType = "application/json;charset=UTF-8";

	public static final String USER_AGENT = "fm_android";

	//阿里云服务器IP
	public static final String IP = "http://116.62.59.24:8080/";
	//本地测试IP
//	public static final String IP = "http://10.139.17.223:8080/";
	//锦江变电站服务器IP
//	public static final String IP = "http://192.168.1.117:8080/";

	//获取变电站名称列表
	public static final String URL_SUBSTATION =IP + "BDZXJService/Android/Substation/all";
	//任务提交
	public static final String URL_CO = IP + "BDZXJService_war/Substation/commitTask";
	//设备类型
	public static final String URL_DEVICE_TYPE =IP + "BDZXJService/Android/DeviceType/all";
	//间隔
	public static final String URL_INTERVALUNIT =IP + "BDZXJService/Android/IntervalUnit/all";
	//设备名称
	public static final String URL_DEVICE =IP +  "BDZXJService/Android/Device";
	//巡视项目
	public static final String URL_PATROLCONTENT =IP + "BDZXJService/Android/PatrolContent";
	//倒闸项目
	public static final String URL_SLUICEOPERATION_CONTENT =IP + "BDZXJService/Android/SluiceOperationContent";
	//任务查询
	public static final String URL_TASK =IP + "BDZXJService/Android/Task";
	//巡视作业卡
	public static final String URL_PATROL_NAME =IP + "BDZXJService/Android/PatrolName";

	public static final String URL_HeadPage =IP + "BDZXJService/Android/HeadPage";

	public static final String URL_LOGIN =IP + "BDZXJService/Android/Login";
	//提交巡视记录
	public static final String URL_PatrolRecord =IP + "BDZXJService/Android/PatrolRecord";
	//提交缺陷记录
	public static final String URL_DefectRecord =IP + "BDZXJService/Android/DefectRecord";
	//提交倒闸操作记录
	public static final String URL_SluiceOperationRecord =IP + "BDZXJService/Android/SluiceOperationRecord";
	//通用任务列表
	public static final String URL_COMMON_TASK_LIST=IP + "BDZXJService/Android/Task";
	//获得详细任务内容
	public static final String URL_PatrolTask=IP + "BDZXJService/Android/PatrolTask";
	//开门时间记录
	public static final String URL_OpenDoorRecord = IP + "BDZXJService/Android/BluetoothUnlocking";

	
}
