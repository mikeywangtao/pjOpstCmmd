package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pShellpro.AbsShellpro;
import orgs.cm.pMqp.pShellpro.StandardShellpro_C00;

/**
 * 创建vm命令1,创建shell文件。
 * */
public class RunBefore_C00_1 extends AbsRunBefore {

	private final String strCname = RunBefore_C00_1.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> hmpAll;
	
	public void disSetAll(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	@Override
	public HashMap<String, Object> disRunBefore() {
		String strFname = " disRunBefore : ";
		try {
			if(hmpAll!=null && hmpAll.size()>0
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null){
				disSetShell();
				
				String strNowRunflg = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString();
				if(strNowRunflg!=null && strNowRunflg.trim().length()>0){
					hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowPostfix, "_"+strNowRunflg+".sh");
					AbsShellpro objShellpro = new StandardShellpro_C00(hmpAll);
					if(objShellpro.disShellpro()){
						hmpAll.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "t");
					}
				}
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
		return hmpAll;
	}
	
	private void disSetShell(){
		String strFname = " disSetShell : ";
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
							System.out.println("strShline ----" + strShline);;
							System.out.println("strSigParam ----" + strSigParam);;
							System.out.println("mapParam.get(strSigParam) ----" + mapParam.get(strSigParam));;
							strShline = strShline.replaceAll(strSigParam.replaceAll("\\^", "\\\\^"), mapParam.get(strSigParam));
						}
						strShline = strCmdshids + "}}}" + strShline; 
						altShell.add(strShline);
					}
				}
			}
			if(altShell!=null && altShell.size()>0){
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_Cmdshr, altShell);
			}
		} catch(Exception ex) {
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
