package orgs.cm.pMqp.pLis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pDblst.DbContManage;

public class WebLis implements ServletContextListener {

	private final String strCname = WebLis.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	/** DbLis是否在Init。true:yes false:no */
	public static boolean booFlg = true;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		String strFname = " contextDestroyed : ";
		try{
			DbContManage.disShutdown();
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

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String strFname = " contextInitialized : ";
		try{
			logger.info(strCname + strFname + " Start!");
			

			DbLis objDbLis = new DbLis();
			objDbLis.disDbinit();
//			
			ProLis objProLis = new ProLis();
			objProLis.disProInit();
//			
			MqLis objMqLis = new MqLis();
			objMqLis.disMqInit();
				
//			QzLis objQzLis = new QzLis();
//			objQzLis.disQzInit();
			
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
		} finally{
			booFlg = false;
		}
	}

}
