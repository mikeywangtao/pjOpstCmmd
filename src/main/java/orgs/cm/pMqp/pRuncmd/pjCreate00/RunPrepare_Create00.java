package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.ProcessSql_Qz;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.IBaseDbpro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;

public class RunPrepare_Create00 extends AbsRunPrepare {

	private final String strCname = RunPrepare_Create00.class.getName();
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
			if(hmpAll!=null && hmpAll.size()>0){
				objDbpro = new BaseDbpro(DbproAttrs.strDbflg_Cmd);
				
				disSearchCmdi(ProcessSql_Qz.strQzSql_Search_Cmdi, hmpCmds);
				disSearchCmdsh(ProcessSql_Qz.strQzSql_Search_Cmdsh, hmpCmds);
				disSearchCmdpar(ProcessSql_Qz.strQzSql_Search_Cmdpar, hmpCmds);
				
				hmpAll.put(ProcessAttrs.strParmapKey_Ppalst, hmpCmds);
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "1");
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunLoopFlg, null);
//				disSetShell();
			}
		} catch(Exception ex) {
			hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, null);
			disOutputLog(strFname, ex);
		}
		return hmpAll;
	}
	
	private String strCmdiIds = null;

	
	private void disSearchCmdpar(String strSqlTempp, HashMap<String, Object> hmpCmdsp){
		String strFname = " disSearchCmdsh : ";
		String strSqlf = strSqlTempp;
		try {
			if(strSqlf!=null && strSqlf.trim().length()>0
					&& hmpCmdsp !=null){
				strSqlf = strSqlf.replaceAll("\\^cmdi_ids\\^", strCmdiIds);
				hmpCmdsp.put(ProcessAttrs.strParmapKey_Ppa_Cmdpar, objDbpro.disSearch(strSqlf));
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
	}
	
	private void disSearchCmdsh(String strSqlTempp, HashMap<String, Object> hmpCmdsp){
		String strFname = " disSearchCmdsh : ";
		String strSqlf = strSqlTempp;
		try {
			if(strSqlf!=null && strSqlf.trim().length()>0
					&& strCmdiIds!=null && strCmdiIds.trim().length()>0
					&& hmpCmdsp !=null
					){
				strSqlf = strSqlf.replaceAll("\\^cmdi_ids\\^", strCmdiIds);
				hmpCmdsp.put(ProcessAttrs.strParmapKey_Ppa_Cmdsh, objDbpro.disSearch(strSqlf));
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
	}
	
	private void disSearchCmdi(String strSqlTempp, HashMap<String, Object> hmpCmdsp){
		String strFname = " disSearchCmdi : ";
		String strSqlf = strSqlTempp;
		try {
			if(strSqlf!=null && strSqlf.trim().length()>0
					&& hmpCmdsp !=null
					&& hmpAll.containsKey("^req_type^")
					&& hmpAll.get("^req_type^")!=null
					&& hmpAll.containsKey("^req_subtype^")
					&& hmpAll.get("^req_subtype^")!=null
					){
				String strType = hmpAll.get("^req_type^").toString();
				String strSubtype = hmpAll.get("^req_subtype^").toString();
				if(strType!=null && strType.trim().length()>0
						&& strSubtype!=null && strSubtype.trim().length()>0){
					strSqlf = strSqlf.replaceAll("\\^req_type\\^", strType);
					strSqlf = strSqlf.replaceAll("\\^req_subtype\\^", strSubtype);
					ArrayList<LinkedHashMap<String, Object>> altCmdi = objDbpro.disSearch(strSqlf);
					if(altCmdi!=null && altCmdi.size()>0){
						String strCmd = "";
						strCmdiIds = "";
						for(LinkedHashMap<String, Object> mapRow : altCmdi){
							strCmd = strCmd + (mapRow.get("cmmd")==null? "":mapRow.get("cmmd").toString()+",");
							strCmdiIds = strCmdiIds + (mapRow.get("cmdi_ids")==null? "":"'"+mapRow.get("cmdi_ids").toString()+"',");
						}
						if(strCmdiIds!=null 
								&& strCmd!=null && strCmd.trim().length()>0){
							strCmdiIds = strCmdiIds.substring(0, strCmdiIds.length()-1);
							hmpCmdsp.put(ProcessAttrs.strParmapKey_Ppa_Cmdi, altCmdi);
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunShCmmd, strCmd);
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_Cmdids, strCmdiIds.replaceAll("'", ""));
						}
					}
				}
			}
		} catch(Exception ex) {
			strCmdiIds = null;
			disOutputLog(strFname, ex);
		}
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
