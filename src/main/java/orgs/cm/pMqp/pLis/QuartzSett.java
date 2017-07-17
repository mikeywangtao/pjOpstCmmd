package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;
import orgs.cm.pMqp.pQz.QuartzJob;
import orgs.cm.pMqp.pQz.QuartzManager;
/**
 * Qz定时任务设置
 * */
public class QuartzSett {

	private final String strCname = QuartzSett.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disSettProcess(){
		String strFname = " disSettProcess : ";
		try{
			logger.info(strCname + strFname + " Start!");
			String[] subStroJbLst = null;
			String stroJbLst = null;
			Object objServerTarget = Propertiesmap.getPptAttr("quartzs.properties", "jobLst");
			stroJbLst = objServerTarget==null? null:objServerTarget.toString();
			if(stroJbLst!=null && stroJbLst.trim().length()>0){
				subStroJbLst = stroJbLst.split(",");
				if(subStroJbLst!=null && subStroJbLst.length>0){
					for(String strJobname : subStroJbLst){
						String strJobdt = null;
						Object objJobdt = Propertiesmap.getPptAttr("quartzs.properties", strJobname);
						strJobdt = objJobdt==null? null:objJobdt.toString();
						if(strJobdt!=null && strJobdt.trim().length()>0){
							QuartzJob objQuartzJob = new QuartzJob();
							QuartzManager.addJob(strJobname, objQuartzJob, strJobdt);//
//							QuartzManager.addJob(strJobname, objQuartzJob, "0 */1 * * * ?");//0 0 1 * * ? 每天1点触发
						}
					}
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
	}
}
