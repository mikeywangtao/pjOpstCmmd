package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pShellpro.AbsShellpro;
import orgs.cm.pMqp.pShellpro.StandardShellpro_Create00;

public class RunBefore_C00_2 extends AbsRunBefore {

	private final String strCname = RunBefore_C00_2.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> hmpAll;
	
	public void disSetAll(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	@Override
	public HashMap<String, Object> disRunBefore() {
		String strFname = " disRunBefore : ";
		try {
			if(hmpAll!=null && hmpAll.size()>0){
				AbsShellpro objShellpro = new StandardShellpro_Create00(hmpAll);
				if(objShellpro.disShellpro()){
					hmpAll.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "t");
				}
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
		return hmpAll;
	}
	private void disOutputLog(String strFnamep, Exception exp){
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}

}
