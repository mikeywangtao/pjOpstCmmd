package orgs.cm.pMqp.pDblst;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库链接管理
 * */
public class DbContManage {
	/** 数据库链接对象池 */
	public static ConcurrentHashMap<String, ObjRepePool01<DbCont>> chmDb = 
			new ConcurrentHashMap<String, ObjRepePool01<DbCont>>();
}
