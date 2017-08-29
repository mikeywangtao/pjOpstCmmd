package orgs.cm.pMqp.pDblst;

import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 数据库链接管理
 * */
public class DbContManage {
	private static final String strCname = DbContManage.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	
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
	
	public synchronized static Statement getDbstmt(String strKeyp){
		String strFname = " getDbstmt : ";
		DbCont objDbContf = null;
		Statement objStmt = null;
		try {
			synchronized (chmDb) {
				if(strKeyp!=null && strKeyp.trim().length()>0
						&& chmDb.containsKey(strKeyp)){
					objDbContf = chmDb.get(strKeyp).getObject();
					if(objDbContf!=null){
						objStmt = objDbContf.getSmt();
					}
					chmDb.get(strKeyp).reObject(objDbContf);
				}
			}
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
		return objStmt;
	}
	
	public synchronized static void disShutdown(){
		String[] strkeys = chmDb.keySet().toArray(new String[0]);
		if(strkeys!=null && strkeys.length>0){
			for(String key : strkeys){
				ObjRepePool01<DbCont> obj = chmDb.remove(key);
				obj.disRemoreall(new DbContDestroyed());
			}
		}
	}
}
