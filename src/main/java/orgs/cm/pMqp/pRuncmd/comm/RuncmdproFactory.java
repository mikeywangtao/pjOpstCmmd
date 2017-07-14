package orgs.cm.pMqp.pRuncmd.comm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pProlst.ThrdRuncmdPro;

public class RuncmdproFactory {

	private static final String strCname = ThrdRuncmdPro.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	public synchronized static AbsRuncmdPro disGetObj(String strClspathp){
		String strFname = " disGetObj : ";
		AbsRuncmdPro objRe = null;
		try {
			if(strClspathp!=null && strClspathp.trim().length()>0){
				
			} else {
				throw new Exception("strClspathp error!");
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
