package orgs.cm.pMqp.pDblst;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 构建数据库链接对象池
 * */
public class DbContProcess {

	private final String strCname = DbContProcess.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disInitDbCont(){
		String strFname = " disInitDbCont : ";
		try {
			logger.info(strCname + strFname + " Start!");
			int intNum = 150;
			
			ObjRepePool01<DbCont> objObjRepePool01 = new ObjRepePool01<DbCont>(intNum+"", new DbCont());
			DbContManage.chmDb.put("asd", objObjRepePool01);
			
			for(int i=0; i<intNum; i++){
				DbCont obj = new DbCont();
				DbContManage.chmDb.get("asd").setObject(obj);
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
	}
}
