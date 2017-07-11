package orgs.cm.pMqp.pProlst;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Runcmd队列
 * 线程执行队列，多队列，线程置入后由队列启动
 * */
public class ThrdRunManage {
	public static ConcurrentHashMap<String, ThrdRunOne> chmthdrMang_RuncmdPro = 
			new ConcurrentHashMap<String, ThrdRunOne>();
}
