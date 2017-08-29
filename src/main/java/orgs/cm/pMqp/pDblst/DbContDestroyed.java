package orgs.cm.pMqp.pDblst;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbContDestroyed implements IDbContDestroyed {
	private final String strCname = DbContDestroyed.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disDestroyed(Object objp){
		String strFname = " disDestroyed_DbCint : ";
		try {
			if(objp!=null){
				DbCont objf = (DbCont)objp;
				objf.disCloseself();
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
