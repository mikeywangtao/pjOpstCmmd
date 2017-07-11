package orgs.cm.pMqp.pMqlst;

import java.util.concurrent.ConcurrentHashMap;
/**
 * 线程集合，置入线程后马上启动线程
 * */
public class ThreadReceiveMsgManage {
	/**
	 * 由MQ中获取Msg的线程集合
	 * new ThrdReceiveMsg()
	 * */
	public static ConcurrentHashMap<String, AbsThrdReceiveMsg> chmReceive = 
			new ConcurrentHashMap<String, AbsThrdReceiveMsg>();
	
//	/**
//	 * 存储用于创建Receive对象的标记
//	 * 来源于com.creditharmony.core.logs.LogsSysFlg中系统标记
//	 * */
//	public static ConcurrentHashMap<String, String> chmReceiveFlg = 
//			new ConcurrentHashMap<String, String>();
}