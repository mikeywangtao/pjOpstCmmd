package orgs.cm.pMqp.pLis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pProlst.ThrdRunManage;
import orgs.cm.pMqp.pProlst.ThrdRunOne;

/**
 * lis调用
 * 1. 构建runcmd队列
 * */
public class RuncmdProces {

	private final String strCname = RuncmdProces.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disInitRuncmd(){
		String strFname = " disInitRuncmd : ";
		try {
			logger.info(strCname + strFname + " Start!");
			
			ThrdRunManage.chmthdrMang_RuncmdPro.put("CREATE", new ThrdRunOne(20));
			ThrdRunManage.chmthdrMang_RuncmdPro.put("EDIT", new ThrdRunOne(20));
			ThrdRunManage.chmthdrMang_RuncmdPro.put("SSRD", new ThrdRunOne(5));//START STOP RESTART DESTROY
			ThrdRunManage.chmthdrMang_RuncmdPro.put("IFN", new ThrdRunOne(4));//IMAGE FLV NETWORK
			ThrdRunManage.chmthdrMang_RuncmdPro.put("OPRO", new ThrdRunOne(3));//OTHER PROCESS
		} catch(Exception ex){
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
