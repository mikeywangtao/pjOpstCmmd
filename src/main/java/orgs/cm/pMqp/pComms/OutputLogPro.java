package orgs.cm.pMqp.pComms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * logger 日志数据对象
 * */
public class OutputLogPro {

	private final String strCname = OutputLogPro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private String strCCname = "";
	private OutputLogPro(){}
	public OutputLogPro(String strCCnamep){
		this.strCCname = strCCnamep;
	}
	
	public void disErrOutputLog(Logger logger, 
			ArrayList<LinkedHashMap<String, String>> altRunc, 
			LinkedHashMap<String, String> lhpInfobase, 
			String strFnamep, 
			Exception exp){
		String strFname = " disOutputLog : ";
		String strInfo = "";
		SetInfoPro objSetInfoPro = new SetInfoPro(strCname);
		
		try {
			
			long lonFlg = System.currentTimeMillis();
			logger.error(strCCname + strFnamep + exp + "||" + lonFlg);
			strInfo = strCCname + strFnamep + exp + "||" + lonFlg ;
			altRunc = objSetInfoPro.disSetInfo_000(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_EEx + " P00000");
			StackTraceElement[] subSte = exp.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
				strInfo = subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg ;
				altRunc = objSetInfoPro.disSetInfo_000(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_ETx + " --" + i);
			}
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			this.logger.error(this.strCCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				this.logger.error(subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
	}
}
