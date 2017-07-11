package orgs.cm.pMqp.pProlst;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pMqlst.ThrdReceiveDispense;

/**
 * 获取cmd处理逻辑，并使runcmd排队。
 * */
public class ThrdRuncmdPro extends AbsThrdRuncmdPro {

	private final String strCname = ThrdRuncmdPro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void run() {
		String strFname = " run : ";
		try {
			logger.info(strCname + strFname + " Start!");
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
