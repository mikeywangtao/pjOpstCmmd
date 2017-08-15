package orgs.cm.pMqp.pRuncmd.pQzGetnetw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.ProcessSql_Qz;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.IBaseDbpro;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;

public class RunPrepare_Getnetw extends AbsRunPrepare {

	private final String strCname = RunPrepare_Getnetw.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	
	private IBaseDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	
	private ClsBaseAttrs objBa = null; 
	private String strCmdiIds = null;
	
//	public void disSetAll(HashMap<String, Object> hmpAllp){
//		hmpAll = hmpAllp;
//	}
	public void disSetHmpall(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	public void disSetClsBaseAttrs(ClsBaseAttrs objBap){
		objBa = objBap;
//		objBa.disClear_lhpInfobase();
		objBa.disClear_altRunc();
	}
	@Override
	public HashMap<String, Object> disRunPrepare() {
		String strFname = " disRunPrepare : ";
		String strInfo = "";
		SaveInfoPro objSaveInfoPro = null;
		HashMap<String, Object> hmpCmds = new HashMap<>();
		
		try {
			if(hmpAll!=null && hmpAll.size()>0){
				objDbpro = new BaseDbpro(DbproAttrs.strDbflg_Cmd);
				if(objBa==null){
					return null;
				}
				objSaveInfoPro = new SaveInfoPro(strCname, objBa);
				
				strInfo = strCname + strFname + " RunPrepare Getimg Start!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
				
				disSearchCmdi(ProcessSql_Qz.strQzSql_Search_Cmdi, hmpCmds);
				disSearchCmdsh(ProcessSql_Qz.strQzSql_Search_Cmdsh, hmpCmds);
				disSearchCmdpar(ProcessSql_Qz.strQzSql_Search_Cmdpar, hmpCmds);
				
				hmpAll.put(ProcessAttrs.strParmapKey_Ppalst, hmpCmds);
				
//				disSetShell();
				
				strInfo = strCname + strFname + " RunPrepare Getimg End!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
			}
		} catch(Exception ex) {
//			disOutputLog(strFname, ex);
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} finally {
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
		}
		return hmpAll;
	}
	
//	private void disSetShell(){
//		String strFname = " disSetShell : ";
//		String strInfo = "";
//		ArrayList<String> altShell = null;
//		try {
//			if(hmpAll!=null
//					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Inpars)
//					&& hmpAll.get(ProcessAttrs.strParmapKey_Inpars)!=null
//					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppalst)
//					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)!=null
//					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdsh)
//					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdsh)!=null
//					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdpar)
//					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdpar)!=null
//					){
//				strInfo = strCname + strFname + " RunPrepare Getimg Start!" ;
//				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
//				
//				HashMap<String, String> mapParam = (HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars);
//				ArrayList<HashMap<String, String>> altCmdsh = 
//						(ArrayList<HashMap<String, String>>)((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdsh);
//				ArrayList<HashMap<String, String>> altCmdpar = 
//						(ArrayList<HashMap<String, String>>)((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdpar);
//				altShell = new ArrayList<>();
//				for(int i=0; i<altCmdsh.size(); i++){
//					HashMap<String, String> mapShellRow = altCmdsh.get(i);
//					if(mapShellRow==null || mapShellRow.size()==0){
//						continue;
//					}
//					String strCmdshids = mapShellRow.get("cmdi_ids");
//					String strShline = mapShellRow.get("shell_line");
//					if(strShline==null ||(strShline!=null && strShline.trim().length()==0)){
//						continue;
//					}
//					for(int j=0; j<altCmdpar.size(); j++){
//						HashMap<String, String> mapCmsparam = altCmdpar.get(j);
//						if(mapCmsparam==null || mapCmsparam.size()==0){
//							continue;
//						}
//						String strSigParam = mapCmsparam.get("par_flg");
//						if(strSigParam==null ||(strSigParam!=null && strSigParam.trim().length()==0)){
//							continue;
//						}
////						strShline = strShline.replaceAll(strSigParam, mapParam.get(mapParam));
//						strShline = strShline.replaceAll(strSigParam.replaceAll("\\^", "\\\\^"), mapParam.get(strSigParam));
//					}
//					strShline = strCmdshids + "}}}" + strShline; 
//					altShell.add(strShline);
//				}
//				strInfo = strCname + strFname + " RunPrepare Getimg End!" ;
//				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
//			}
//			if(altShell!=null && altShell.size()>0){
//				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_Cmdshr, altShell);
//			}
//		} catch(Exception ex) {
////			disOutputLog(strFname, ex);
//			if(objBa!=null && objBa.objOutputLogPro!=null){
//				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
//			}
//		}
//	}
	
	private void disSearchCmdpar(String strSqlTempp, HashMap<String, Object> hmpCmdsp){
		String strFname = " disSearchCmdsh : ";
		String strInfo = "";
		String strSqlf = strSqlTempp;
		try {
			strInfo = strCname + strFname + " RunPrepare Getimg Start!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
			if(strSqlf!=null && strSqlf.trim().length()>0
					&& hmpCmdsp !=null){
				strSqlf = strSqlf.replaceAll("\\^cmdi_ids\\^", strCmdiIds);
				hmpCmdsp.put(ProcessAttrs.strParmapKey_Ppa_Cmdpar, objDbpro.disSearch(strSqlf));
			}
			strInfo = strCname + strFname + " RunPrepare Getimg End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
//			disOutputLog(strFname, ex);
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
	private void disSearchCmdsh(String strSqlTempp, HashMap<String, Object> hmpCmdsp){
		String strFname = " disSearchCmdsh : ";
		String strInfo = "";
		String strSqlf = strSqlTempp;
		try {
			strInfo = strCname + strFname + " RunPrepare Getimg Start!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
			if(strSqlf!=null && strSqlf.trim().length()>0
					&& strCmdiIds!=null && strCmdiIds.trim().length()>0
					&& hmpCmdsp !=null
					){
				strSqlf = strSqlf.replaceAll("\\^cmdi_ids\\^", strCmdiIds);
				hmpCmdsp.put(ProcessAttrs.strParmapKey_Ppa_Cmdsh, objDbpro.disSearch(strSqlf));
			}
			strInfo = strCname + strFname + " RunPrepare Getimg End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
//			disOutputLog(strFname, ex);
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
	private void disSearchCmdi(String strSqlTempp, HashMap<String, Object> hmpCmdsp){
		String strFname = " disSearchCmdi : ";
		String strInfo = "";
		String strSqlf = strSqlTempp;
		try {
			strInfo = strCname + strFname + " RunPrepare Getimg Start!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
			if(strSqlf!=null && strSqlf.trim().length()>0
					&& hmpCmdsp !=null){
				//定时任务采用固定值查询
				strSqlf = strSqlf.replaceAll("\\^req_type\\^", "qz");
				strSqlf = strSqlf.replaceAll("\\^req_subtype\\^", "getnetw");
				ArrayList<LinkedHashMap<String, Object>> altCmdi = objDbpro.disSearch(strSqlf);
				if(altCmdi!=null && altCmdi.size()==1){
					String strCmd = null;
					strCmdiIds = null;
					for(LinkedHashMap<String, Object> mapRow : altCmdi){
						strCmd = altCmdi.get(0).get("cmmd")==null? null:altCmdi.get(0).get("cmmd").toString()+",";
						strCmdiIds = altCmdi.get(0).get("cmdi_ids")==null? null:"'"+altCmdi.get(0).get("cmdi_ids").toString()+"','";
					}
					if(strCmdiIds!=null 
							&& strCmd!=null && strCmd.trim().length()>0){
						strCmdiIds = strCmdiIds.substring(0, strCmdiIds.length()-2);
						hmpCmdsp.put(ProcessAttrs.strParmapKey_Ppa_Cmdi, altCmdi);
						hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunShCmmd, strCmd);
						hmpAll.put(ProcessAttrs.strParmapKey_Ppa_Cmdids, strCmdiIds.replaceAll("'", ""));
					}
				}
			}
			strInfo = strCname + strFname + " RunPrepare Getimg End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			strCmdiIds = null;
//			disOutputLog(strFname, ex);
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
//	private void disOutputLog(String strFnamep, Exception exp){
//		long lonFlg = System.currentTimeMillis();
//		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
//		StackTraceElement[] subSte = exp.getStackTrace();
//		for(int i=0; i<subSte.length; i++){
//			logger.error(
//					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
//		}
//	}
}
