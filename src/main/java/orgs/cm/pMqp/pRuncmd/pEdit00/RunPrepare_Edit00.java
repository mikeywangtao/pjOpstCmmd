package orgs.cm.pMqp.pRuncmd.pEdit00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.ProcessSql_Qz;
import orgs.cm.pMqp.pDbpro.BaseDbpro;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.IBaseDbpro;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;

/**
 * create00准备
 * */
public class RunPrepare_Edit00 extends AbsRunPrepare {

	private final String strCname = RunPrepare_Edit00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
//	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
//	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	private ClsBaseAttrs objBa = null; 
	private IBaseDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	
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
		SaveInfoPro objSaveInfoPro = null;
		HashMap<String, Object> hmpCmds = new HashMap<>();
		String strInfo = "";
		
		try {

			if(hmpAll!=null && hmpAll.size()>0
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Inpars)){
				if(objBa==null){
					return null;
				}
				objSaveInfoPro = new SaveInfoPro(strCname, objBa);
				
				objBa.lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Prep);
				strInfo = strCname + strFname + " Start!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
				
				objDbpro = new BaseDbpro(DbproAttrs.strDbflg_Cmd);
				
				disSearchCmdi(ProcessSql_Qz.strQzSql_Search_Cmdi, hmpCmds);
				disSearchCmdsh(ProcessSql_Qz.strQzSql_Search_Cmdsh, hmpCmds);
				disSearchCmdpar(ProcessSql_Qz.strQzSql_Search_Cmdpar, hmpCmds);
				
				hmpAll.put(ProcessAttrs.strParmapKey_Ppalst, hmpCmds);
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "1");
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunLoopFlg, null);
				
				strInfo = strCname + strFname + " End!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
			}
		} catch(Exception ex) {
			hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, null);
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} finally{
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
		}
		return hmpAll;
	}
	

	
	/**  cmmd的ids */
	private String strCmdiIds = null;

	/**
	 * 查询cmmd的参数
	 * */
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
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
	/**
	 * 查询cmmd的shell
	 * */
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
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
	/**
	 * 查询cmmd的info信息
	 * */
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
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
//	private void disSaveInfo(String strFlgp){
//	String strFname = " disSaveInfo : ";
//	try {
//		if(strFlgp!=null && strFlgp.trim().length()>0
//				&& altRunc!=null && altRunc.size()>0){
////			for(LinkedHashMap<String, String> mapRow : altRunc){
////				System.out.println(mapRow);
////			}
//			DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
//			DbInfoSavepro objDbInfoSavepro = new DbInfoSavepro(DbproAttrs.strDbflg_Cmd, disGetBusname());
//			if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
//				int intNum = objDbInfoSavepro.disSaveRuninfo(altRunc);
//				if(intNum==altRunc.size()){
//					logger.info(strCname + strFname + " Prepare完整存储!");
//				} else {
//					logger.info(strCname + strFname + " Prepare存储异常!");
//				}
//			}
//		}
//	} catch(Exception ex) {
//		disOutputLog(strFname, ex);
//	}
//}
//	private String disGetBusname(){
//		String strFname = " disGetBusname : ";
//		String strRe = "";
//		try {
//			String strPackage = this.getClass().getPackage().getName();
//			String[] subTmp = strPackage.split("\\.");
//			if(subTmp!=null && subTmp.length>1){
//				strPackage = subTmp[subTmp.length-1];
//			}
//			if(strPackage.indexOf(".")==-1){
//				strPackage = strPackage.toLowerCase();
//			}
//			strRe = strPackage;
//		} catch(Exception ex) {
//			strRe = "";
//			disOutputLog(strFname, ex);
//		}
//		return strRe;
//	}
//	
//	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
//			, LinkedHashMap<String, String> lhpInfop
//			, ArrayList<LinkedHashMap<String, String>> altRuncp
//			, String strInfoTypepFlgp){
//		String strTypef = "";
//		String strFlgf = "";
//		String strSubflgf = "";
//		if(strInfoTypepFlgp!=null && strInfoTypepFlgp.trim().length()>0){
//			String[] subTypeFlg = strInfoTypepFlgp.split("}}}", -1);
//			if(subTypeFlg!=null && subTypeFlg.length>=2){
//				strTypef = subTypeFlg[0];
//				strFlgf = subTypeFlg[1];
//				strSubflgf = subTypeFlg[2];
//			}
//		}
//		LinkedHashMap<String, String> lhpInfof = null;
//		String strInfo = strInfop;
//		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
//		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo.replaceAll("'", "\""));
//		lhpInfof.put(ProcessAttrs.strInfoType_Info, strTypef);
//		lhpInfof.put(ProcessAttrs.strInfoFlg_Info, strFlgf);
//		lhpInfof.put(ProcessAttrs.strInfoSubflg_Info, strSubflgf);
//		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
//		altRuncp.add(lhpInfof);
//		return altRuncp;
//	}
//	
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
