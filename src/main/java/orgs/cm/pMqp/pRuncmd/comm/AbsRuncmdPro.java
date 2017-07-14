package orgs.cm.pMqp.pRuncmd.comm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pRuncmd.pCreate00.Runcmdpro_Create00;

public abstract class AbsRuncmdPro implements IRuncmdPro{

	private final String strCname = AbsRuncmdPro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public abstract void disRuncmdPro();

	protected void disRunPrepare(AbsRunPrepare objRunPreparep) {
		String strFname = " disRunPrePare : ";
		try {
			logger.info(strCname + strFname + " Start!");
			AbsRunPrepare objRunPrepare = objRunPreparep;
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
	protected void disRunBefre(AbsRunBefore objRunBeforep) {
		String strFname = " disRunBefre : ";
		try {
			logger.info(strCname + strFname + " Start!");
			AbsRunBefore objRunBefore = objRunBeforep;
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
	protected void disRunCmd(AbsRunCmd objRunCmdp) {
		String strFname = " disRunCmd : ";
		try {
			logger.info(strCname + strFname + " Start!");
			AbsRunCmd objRunCmd = objRunCmdp;
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
	protected void dusRunAfter(AbsRunAfter objRunAfterp) {
		String strFname = " dusRunAfter : ";
		try {
			logger.info(strCname + strFname + " Start!");
			AbsRunAfter objRunAfter = objRunAfterp;
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
