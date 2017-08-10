package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pShellpro.AbsShellpro;
import orgs.cm.pMqp.pShellpro.StandardShellpro_C00;

public class RunBefore_C00_3 extends AbsRunBefore {

	private final String strCname = RunBefore_C00_3.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
//	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
//	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	private ClsBaseAttrs objBa = null; 
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
	public HashMap<String, Object> disRunBefore() {
		String strFname = " disRunBefore : ";
		String strInfo = "";
		SaveInfoPro objSaveInfoPro = null;
		
		try {
			if(hmpAll!=null && hmpAll.size()>0
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
					){
				if(objBa==null){
					return null;
				}
				objSaveInfoPro = new SaveInfoPro(strCname, objBa);
				
				objBa.lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Bef);
				strInfo = strCname + strFname + " 创建VM Before03 Start----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS );

				disSetShell();
				
				String strNowRunflg = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString();
				if(strNowRunflg!=null && strNowRunflg.trim().length()>0){
					hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowPostfix, "_"+strNowRunflg+".sh");
					AbsShellpro objShellpro = new StandardShellpro_C00(hmpAll);
					if(objShellpro.disShellpro()){
						hmpAll.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "t");
					}
				}
				strInfo = strCname + strFname + " 创建VM Before03 End----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
			}
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} finally{
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
//			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}
		return hmpAll;
	}
	
	private void disSetShell(){
		String strFname = " disSetShell : ";
		String strInfo = "";
		ArrayList<String> altShell = null;
		try {
			if(hmpAll!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Inpars)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Inpars)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppalst)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)!=null
					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdsh)
					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdsh)!=null
					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdpar)
					&& ((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdpar)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdids)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_Cmdids)!=null 
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
					){
				strInfo = strCname + strFname + " 创建VM Before03 构建shell命令 Start----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
				
				String strCmdids = null;
				String strNowRunflg = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString();
				String[] subCmmd = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd).toString().split(",");
				String[] subCmdids = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_Cmdids).toString().split(",");
				if(subCmmd!=null && subCmmd.length==4 //命令数变化需要修改
						&& subCmdids!=null && subCmdids.length==4 //命令数变化需要修改
						&& strNowRunflg!=null && strNowRunflg.trim().length()>0){
					if(strNowRunflg!=null && strNowRunflg.trim().length()>0){
						strCmdids = subCmdids[Integer.parseInt(strNowRunflg)-1];
					}
					HashMap<String, String> mapParam = (HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars);
					ArrayList<HashMap<String, String>> altCmdsh = 
							(ArrayList<HashMap<String, String>>)((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdsh);
					ArrayList<HashMap<String, String>> altCmdpar = 
							(ArrayList<HashMap<String, String>>)((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdpar);
					altShell = new ArrayList<>();
					for(int i=0; i<altCmdsh.size(); i++){
						HashMap<String, String> mapShellRow = altCmdsh.get(i);
						if(mapShellRow==null || mapShellRow.size()==0
								|| strNowRunflg.equals(mapShellRow.get("runorder"))){
							continue;
						}
						String strCmdshids = mapShellRow.get("cmdi_ids");
						String strShline = mapShellRow.get("shell_line");
						if(strShline==null ||(strShline!=null && strShline.trim().length()==0)){
							continue;
						}
						if(!mapShellRow.containsKey("cmdi_ids")
								|| (mapShellRow.containsKey("cmdi_ids") && mapShellRow.get("cmdi_ids")==null)
								|| (mapShellRow.containsKey("cmdi_ids") && mapShellRow.get("cmdi_ids")!=null && !strCmdids.equals(mapShellRow.get("cmdi_ids").toString()))){
							continue;
						}
						for(int j=0; j<altCmdpar.size(); j++){
							HashMap<String, String> mapCmsparam = altCmdpar.get(j);
							if(mapCmsparam==null || mapCmsparam.size()==0){
								continue;
							}
							String strSigParam = mapCmsparam.get("par_flg");
							if(strSigParam==null ||(strSigParam!=null && strSigParam.trim().length()==0)){
								continue;
							}
							strShline = strShline.replaceAll(strSigParam.replaceAll("\\^", "\\\\^"), mapParam.get(strSigParam));
						}
						strShline = strCmdshids + "}}}" + strShline; 
						altShell.add(strShline);
					}
				}
			}
			if(altShell!=null && altShell.size()>0){
				for(String strSCmd : altShell){
					strInfo = strCname + strFname + " 创建VM Before03 shell命令行 ----" + strSCmd;
					objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PAx + " shell命令行 ");
				}
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_Cmdshr, altShell);
			}
			strInfo = strCname + strFname + " 创建VM Before03 构建shell命令 End----" + DatePro.disGetStrdate4NowObjSdf001();
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
	
//	private void disSaveInfo(String strFlgp){
//		String strFname = " disSaveInfo : ";
//		try {
//			if(strFlgp!=null && strFlgp.trim().length()>0
//					&& altRunc!=null && altRunc.size()>0){
////				for(LinkedHashMap<String, String> mapRow : altRunc){
////					System.out.println(mapRow);
////				}
//				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
//				DbInfoSavepro objDbInfoSavepro = new DbInfoSavepro(DbproAttrs.strDbflg_Cmd, disGetBusname());
//				if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
//					int intNum = objDbInfoSavepro.disSaveRuninfo(altRunc);
//					if(intNum==altRunc.size()){
//						logger.info(strCname + strFname + " Runcmd完整存储!");
//					} else {
//						logger.info(strCname + strFname + " Runcmd存储异常!");
//					}
//				}
//			}
//		} catch(Exception ex) {
//			disOutputLog(strFname, ex);
//		}
//	}
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
//				strTypef = subTypeFlg[0].trim();
//				strFlgf = subTypeFlg[1].trim();
//				strSubflgf = subTypeFlg[2].trim();
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
