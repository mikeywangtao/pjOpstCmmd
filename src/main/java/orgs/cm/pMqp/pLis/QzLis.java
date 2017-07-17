package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;

public class QzLis {
	private final String strCname = ProLis.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disQzInit(){
		String strFname = " disQzInit : ";
		try{
			logger.info(strCname + strFname + " Start!");
			String strPath = this.getClass().getResource("").getPath();
			if(strPath!=null && strPath.trim().length()>0
					&& strPath.indexOf("WEB-INF")>0){
				int intOdx = strPath.indexOf("WEB-INF");
				strPath = strPath.substring(0, intOdx) + "WEB-INF/classes/quartzs.properties";
				Propertiesmap.setPpt(strPath);
				
				QuartzSett objQuartzSett = new QuartzSett();
				objQuartzSett.disSettProcess();
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
