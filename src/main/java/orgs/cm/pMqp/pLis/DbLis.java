package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;
import orgs.cm.pMqp.pDblst.DbContProcess;

public class DbLis {
	
	private final String strCname = DbLis.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disDbinit(){
		String strFname = " disDbinit : ";
		try{
			logger.info(strCname + strFname + " Start!");
			String strPath = this.getClass().getResource("").getPath();
			if(strPath!=null && strPath.trim().length()>0
					&& strPath.indexOf("WEB-INF")>0){
				int intOdx = strPath.indexOf("WEB-INF");
				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/jdbc.properties";
				Propertiesmap.setPpt(strPath);
				
				DbContProcess objDbContProcess = new DbContProcess();
				objDbContProcess.disInitDbCont();
			}
				
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() 
						+ subSte[i].getMethodName() 
						+ ":" + subSte[i].getLineNumber() 
						+ "||" + lonFlg );
			}
		} 
	}
}

//public class DbLis implements ServletContextListener {
//
//	private final String strCname = DbLis.class.getName();
//	private final Logger logger = LogManager.getLogger(strCname);
//	
//	/** DbLis是否在Init。true:yes false:no */
//	public static boolean booFlg = true;
//	
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		String strFname = " contextDestroyed : ";
//		try{
//
//		} catch(Exception ex) {
//			long lonFlg = System.currentTimeMillis();
//			logger.error(strCname + strFname + ex + "||" + lonFlg);
//			StackTraceElement[] subSte = ex.getStackTrace();
//			for(int i=0; i<subSte.length; i++){
//				logger.error(
//						subSte[i].getClassName() 
//						+ subSte[i].getMethodName() 
//						+ ":" + subSte[i].getLineNumber() 
//						+ "||" + lonFlg );
//			}
//		}
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		String strFname = " contextInitialized : ";
//		try{
//			logger.info(strCname + strFname + " Start!");
//			String strPath = this.getClass().getResource("").getPath();
//			if(strPath!=null && strPath.trim().length()>0
//					&& strPath.indexOf("WEB-INF")>0){
//				int intOdx = strPath.indexOf("WEB-INF");
//				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/jdbc.properties";
//				Propertiesmap.setPpt(strPath);
//				
//				DbContProcess objDbContProcess = new DbContProcess();
//				objDbContProcess.disInitDbCont();
//			}
//				
//		} catch(Exception ex) {
//			long lonFlg = System.currentTimeMillis();
//			logger.error(strCname + strFname + ex + "||" + lonFlg);
//			StackTraceElement[] subSte = ex.getStackTrace();
//			for(int i=0; i<subSte.length; i++){
//				logger.error(
//						subSte[i].getClassName() 
//						+ subSte[i].getMethodName() 
//						+ ":" + subSte[i].getLineNumber() 
//						+ "||" + lonFlg );
//			}
//		} finally{
//			booFlg = false;
//		}
//	}
//}
