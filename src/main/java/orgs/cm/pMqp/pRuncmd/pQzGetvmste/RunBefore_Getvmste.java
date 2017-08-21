package orgs.cm.pMqp.pRuncmd.pQzGetvmste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pShellpro.AbsShellpro;
import orgs.cm.pMqp.pShellpro.StandardShellpro_Getimg;

public class RunBefore_Getvmste extends AbsRunBefore {

	private final String strCname = Runcmdpro_Getvmste.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
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
			if(hmpAll!=null && hmpAll.size()>0){
				if(objBa==null){
					return null;
				}
				objSaveInfoPro = new SaveInfoPro(strCname, objBa);
				
				strInfo = strCname + strFname + " Getvmste Before01 Start----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS );
				
				disSetShell(); 
				
				AbsShellpro objShellpro = new StandardShellpro_Getimg(hmpAll);
				if(objShellpro.disShellpro()){
					hmpAll.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "t");
				}
				
				strInfo = strCname + strFname + " Getvmste Before01 End----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE );
			}
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);//disOutputLog(strFname, ex);
			}
		} finally{
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
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
					){
				strInfo = strCname + strFname + " RunPrepare Getnetw Start!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
				
				HashMap<String, String> mapParam = (HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars);
				ArrayList<HashMap<String, String>> altCmdsh = 
						(ArrayList<HashMap<String, String>>)((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdsh);
				ArrayList<HashMap<String, String>> altCmdpar = 
						(ArrayList<HashMap<String, String>>)((Map)hmpAll.get(ProcessAttrs.strParmapKey_Ppalst)).get(ProcessAttrs.strParmapKey_Ppa_Cmdpar);
				altShell = new ArrayList<>();
				for(int i=0; i<altCmdsh.size(); i++){
					HashMap<String, String> mapShellRow = altCmdsh.get(i);
					if(mapShellRow==null || mapShellRow.size()==0){
						continue;
					}
					String strCmdshids = mapShellRow.get("cmdi_ids");
					String strShline = mapShellRow.get("shell_line");
					if(strShline==null ||(strShline!=null && strShline.trim().length()==0)){
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
//						strShline = strShline.replaceAll(strSigParam, mapParam.get(mapParam));
						strShline = strShline.replaceAll(strSigParam.replaceAll("\\^", "\\\\^"), mapParam.get(strSigParam));
					}
					strShline = strCmdshids + "}}}" + strShline; 
					altShell.add(strShline);
				}
				strInfo = strCname + strFname + " RunPrepare Getnetw End!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
			}
			if(altShell!=null && altShell.size()>0){
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_Cmdshr, altShell);
			}
		} catch(Exception ex) {
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