package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;

/**
 * MQ启动监听
 * */
public class MqLis {
	private final String strCname = MqLis.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disMqInit(){
		String strFname = " disMqInit : ";
		try{
			logger.info(strCname + strFname + " Start!");
			String strPath = this.getClass().getResource("").getPath();
			if(strPath!=null && strPath.trim().length()>0
					&& strPath.indexOf("WEB-INF")>0){
				int intOdx = strPath.indexOf("WEB-INF");
				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/sysTarget.properties";
				Propertiesmap.setPpt(strPath);
				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/activeMq.properties";
				Propertiesmap.setPpt(strPath);
				
				ReceiveMsgProces objReceiveMsgProces = new ReceiveMsgProces();
				objReceiveMsgProces.disInitReceive();
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


//public class MqLis implements ServletContextListener {
//
//	private final String strCname = MqLis.class.getName();
//	private final Logger logger = LogManager.getLogger(strCname);
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
//			while(ProLis.booFlg){
//				Thread.sleep(1000);
//			}
//			String strPath = this.getClass().getResource("").getPath();
//			if(strPath!=null && strPath.trim().length()>0
//					&& strPath.indexOf("WEB-INF")>0){
//				int intOdx = strPath.indexOf("WEB-INF");
//				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/sysTarget.properties";
//				Propertiesmap.setPpt(strPath);
//				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/activeMq.properties";
//				Propertiesmap.setPpt(strPath);
//				
//				ReceiveMsgProces objReceiveMsgProces = new ReceiveMsgProces();
//				objReceiveMsgProces.disInitReceive();
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
//		}
//	}
//}
