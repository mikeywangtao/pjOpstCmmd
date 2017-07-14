package orgs.cm.pMqp.pDblst;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库链接管理
 * */
public class DbContManage {
	/** 数据库链接对象池 */
	private static ConcurrentHashMap<String, ObjRepePool01<DbCont>> chmDb = 
			new ConcurrentHashMap<String, ObjRepePool01<DbCont>>();
	
	public static void disPut(String strkey, ObjRepePool01<DbCont> objPool){
		if(strkey!=null && strkey.trim().length()>0
				&& objPool!=null){
			chmDb.put(strkey, objPool);
		}
	}
	
	public synchronized static void disSet4Getpool(String strKey, DbCont obj){
		if(strKey!=null && strKey.trim().length()>0
				&& obj!=null){
			chmDb.get(strKey).setObject(obj);
		}
	}
	
	public synchronized static void disShutdown(){
		String[] strkeys = chmDb.keySet().toArray(new String[0]);
		if(strkeys!=null && strkeys.length>0){
			for(String key : strkeys){
				chmDb.remove(key);
			}
		}
	}
}
