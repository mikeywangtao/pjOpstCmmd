package orgs.cm.pMqp.pDblst;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;

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
			int intNum = Integer.MIN_VALUE;
			
			Properties objPptJdbc = Propertiesmap.getPpt("jdbc.properties");
			if(objPptJdbc!=null){
				Object objJdbclst = objPptJdbc.get("jdbcLst");
				Object objPoolnum = objPptJdbc.get("poolnum");
				String strJdbclst = objJdbclst==null? null:objJdbclst.toString();
				String strPoolnum = objPoolnum==null? null:objPoolnum.toString();
				if(strJdbclst!=null && strJdbclst.trim().length()>0
						&& strPoolnum!=null && strPoolnum.trim().length()>0){
					String[] subStrJdbclst = strJdbclst.split(",");
					intNum = Integer.parseInt(strPoolnum);
					if(Integer.MIN_VALUE!=intNum && subStrJdbclst!=null){
						for(String strJdbcflg : subStrJdbclst){
							logger.info(strCname + strFname + " poolname---- " + strJdbcflg + "pool");
							ObjRepePool01<DbCont> objObjRepePool01 = new ObjRepePool01<DbCont>(intNum+"", new DbCont(strJdbcflg));
							DbContManage.disPut(strJdbcflg+"pool", objObjRepePool01);
							for(int i=0; i<intNum; i++){
								logger.info(strCname + strFname + " create num---- " + i);
								DbCont obj = new DbCont(strJdbcflg);
								DbContManage.disSet4Getpool(strJdbcflg+"pool", obj);
							}
						}
					}
				}
			}
			
//			ObjRepePool01<DbCont> objObjRepePool01 = new ObjRepePool01<DbCont>(intNum+"", new DbCont());
////			DbContManage.chmDb.put("asd", objObjRepePool01);
//			DbContManage.disPut("asd", objObjRepePool01);
//			
//			for(int i=0; i<intNum; i++){
//				DbCont obj = new DbCont();
////				DbContManage.chmDb.get("asd").setObject(obj);
//				DbContManage.disSet4Getpool("asd", obj);
//			}
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
