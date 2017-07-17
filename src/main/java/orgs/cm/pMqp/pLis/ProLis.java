package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * RunCmd的队列
 * */
public class ProLis {
	private final String strCname = ProLis.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disProInit(){
		String strFname = " disProInit : ";
		try{
			logger.info(strCname + strFname + " Start!");
			RuncmdProces objRuncmdProces = new RuncmdProces();
			objRuncmdProces.disInitRuncmd();

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


//public class ProLis implements ServletContextListener {
//
//	private final String strCname = ProLis.class.getName();
//	private final Logger logger = LogManager.getLogger(strCname);
//	/** Prolst是否在Init。true:yes false:no */
//	public static boolean booFlg = true;
//	
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		String strFname = " contextDestroyed : ";
//		try{
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
//			while(DbLis.booFlg){
//				Thread.sleep(1000);
//			}
//			RuncmdProces objRuncmdProces = new RuncmdProces();
//			objRuncmdProces.disInitRuncmd();
//		} catch(Exception ex) {
//			long lonFlg = System.currentTimeMillis();
//			logger.error(strCname + strFname + ex + "||" + lonFlg);
//			StackTraceElement[] subSte = ex.getStackTrace();
//			for(int i=0; i<subSte.length; i++){
//				logger.error(
//						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
//			}
//		} finally{
//			booFlg = false;
//		}
//	}
//}
