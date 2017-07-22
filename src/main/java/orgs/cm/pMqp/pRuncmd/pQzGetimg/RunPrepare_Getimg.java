package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.IBaseDbpro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;

public class RunPrepare_Getimg extends AbsRunPrepare {

	private final String strCname = RunPrepare_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	
	private IBaseDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	
	public void disSetAll(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	@Override
	public HashMap<String, Object> disRunPrepare() {
		String strFname = " disRunPrepare : ";
		
		HashMap<String, Object> hmpCmds = new HashMap<>();
		
		try {
			objDbpro = new BaseDbpro(DbproAttrs.strDbflg_Cmd);
			
			String strSearchCmd = "";
			hmpCmds.put(ProcessAttrs.strParmapKey_Ppa_Cmdi, objDbpro.disSearch(strSearchCmd));
			String strSearchCmdsh = "";
			hmpCmds.put(ProcessAttrs.strParmapKey_Ppa_Cmdsh, objDbpro.disSearch(strSearchCmdsh));
			String strSearchCmdpar = "";
			hmpCmds.put(ProcessAttrs.strParmapKey_Ppa_Cmdpar, objDbpro.disSearch(strSearchCmdpar));
			
			hmpAll.put(ProcessAttrs.strParmapKey_Ppalst, hmpCmds);
			
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
