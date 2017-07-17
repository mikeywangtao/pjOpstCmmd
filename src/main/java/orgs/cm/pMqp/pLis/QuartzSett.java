package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.Propertiesmap;
import orgs.cm.pMqp.pQz.QuartzBaseJob;
import orgs.cm.pMqp.pQz.QuartzManager;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.QuartzJob_Getimg;
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
			String[] subStrJbLst = null;
			String strJbLst = null;
			Object objServerTarget = Propertiesmap.getPptAttr("quartzs.properties", "jobLst");
			strJbLst = objServerTarget==null? null:objServerTarget.toString();
			if(strJbLst!=null && strJbLst.trim().length()>0){
				subStrJbLst = strJbLst.split(",");
				if(subStrJbLst!=null && subStrJbLst.length>0){
					for(String strJobname : subStrJbLst){
						String strJobdt = null;
						Object objJobdt = Propertiesmap.getPptAttr("quartzs.properties", strJobname);
						strJobdt = objJobdt==null? null:objJobdt.toString();
						if(strJobdt!=null && strJobdt.trim().length()>0){
//							QuartzJob objQuartzJob = new QuartzJob();
//							QuartzManager.addJob(strJobname, objQuartzJob, strJobdt);//
//							QuartzManager.addJob(strJobname, objQuartzJob, "0 */1 * * * ?");//0 0 1 * * ? 每天1点触发
							QuartzBaseJob objJob = disCreatejob(strJobname);
							if(strJobname!=null && strJobname.trim().length()>0
									&& objJob!=null){
								QuartzManager.addJob(strJobname, objJob, strJobdt);
							} else {
								throw new Exception("strJobname OR objJob 一个或多个为空异常！");
							}
						} else {
							throw new Exception("strJobdt 一个或多个为空异常！");
						}
					}
				} else {
					throw new Exception("quartzs.ppt 中 subStroJbLst为空异常！");
				}
			} else {
				throw new Exception("quartzs.ppt 中 strJbLst为空异常！");
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
	
	private QuartzBaseJob disCreatejob(String strJobname){
		String strFname = " disCreatejob : ";
		QuartzBaseJob objRe = null;
		try {
			if(strJobname.indexOf("imgJob")>-1){
				objRe = new QuartzJob_Getimg();
			}
			if(strJobname.indexOf("flvJob")>-1){
//				objJob = new QuartzJob_Getimg();
			}
			if(strJobname.indexOf("netwJob")>-1){
//				objJob = new QuartzJob_Getimg();
			}
		} catch(Exception ex) {
			objRe = null;
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
		return objRe;
	}
}
