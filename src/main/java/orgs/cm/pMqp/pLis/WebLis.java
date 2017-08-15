package orgs.cm.pMqp.pLis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebLis implements ServletContextListener {

	private final String strCname = WebLis.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	/** DbLis是否在Init。true:yes false:no */
	public static boolean booFlg = true;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		String strFname = " contextDestroyed : ";
		try{
//			logger.info(strCname + strFname + "dflkjga;");
//			System.out.println("");
//			if(ThreadPoolManage.chmThdPool_Msg!=null
//					&& ThreadPoolManage.chmThdPool_Msg.size()>0){
//				ThreadPoolManage.chmThdPool_Msg = null;
//			}
//			
//			if(ThreadRunManage.chmthdrMang_DataSave!=null
//					&& ThreadRunManage.chmthdrMang_DataSave.size()>0) {
//				String[] subKey = ThreadRunManage.chmthdrMang_DataSave.keySet().toArray(new String[0]);
//				if(subKey!=null && subKey.length>0){
//					for(String key : subKey){
//						if(key!=null && key.trim().length()>0){
//							ThreadRunManage.chmthdrMang_DataSave.get(key).shutdownVltThd();
//						}
//					}
//				}
//			}
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
