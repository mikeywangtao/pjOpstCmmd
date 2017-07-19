package orgs.cm.pMqp.pComms;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeqPro {
	private static final String strCname = SeqPro.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private static void disOutputLog(String strFnamep, Exception exp){
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}

	private static Integer intNum = 0;
	
	/** 同步方法，获取4位长度数字，不足左侧补齐。 */
	public static synchronized String getNum(){
		String strFname = " getNum : ";
		String strRe = "0000";
		try {
			synchronized (intNum) {
				intNum = intNum + 1;
				if(intNum>9999){
					intNum = 0;
				}
				strRe = String.format("%04d", intNum); 
			}
		} catch(Exception ex) {
			strRe = "0000";
			disOutputLog(strFname, ex);
		}
		return strRe;
	}
	
	public static void main(String[] args){
		for(int i=0; i<10001; i++){
			System.out.println(SeqPro.getNum());
		}
	}
}
