package orgs.cm.pMqp.pShellpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.ShellFilepro;

public class StandardShellpro_C00_1 extends StandardShellpro{

	private final String strCname = StandardShellpro_C00_1.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public StandardShellpro_C00_1(HashMap<String, Object> mapParp) {
		mapPar = mapParp;
	}

	@Override
	public boolean disShellpro() {
		String strFname = "";
		boolean booRe = false;
		try {
			//hmpAllp.put("^strShFileroot^", "/home/anshells");
			if(mapPar!=null 
					&& mapPar.containsKey(ProcessAttrs.strParmapKey_Inpars)
					&& mapPar.get(ProcessAttrs.strParmapKey_Inpars)!=null
					&& mapPar.containsKey("strShFileroot")
					&& mapPar.get("strShFileroot")!=null
					&& mapPar.containsKey("strShFilename")
					&& mapPar.get("strShFilename")!=null
					&& mapPar.containsKey(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)
					&& mapPar.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)!=null 
					&& mapPar.containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdshr)
					&& mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdshr)!=null
					&& mapPar.containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdids)
					&& mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdids)!=null 
					&& mapPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg)
					&& mapPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null ){
					
					String strCmd = mapPar.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd).toString();
					String strCmdids = mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdids).toString();
					if(strCmd.trim().length()>0
							&& strCmdids.trim().length()>0){
						String[] subCmd = strCmd.split(",");
						String[] subCmdids = strCmdids.split(",");
						//验证命令有3个
						if(subCmd!=null && subCmd.length==3
								&& subCmdids!=null && subCmdids.length==3){
							for(int i=1; i<=subCmdids.length; i++){
								String strNowRunflg = mapPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString();
								if(strNowRunflg!=null && strNowRunflg.trim().length()>0
										&& (i+"").equals(strNowRunflg.trim())){
									strCmdids = subCmdids[i-1];
									String strFileroot = mapPar.get("strShFileroot").toString();
									String strFilename = mapPar.get("strShFilename").toString(); 
									ArrayList<String> altShell = (ArrayList<String>)mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdshr); 
									ShellFilepro objShellFilepro = new ShellFilepro(strFileroot, strFilename, altShell, strCmdids, null);
									booRe = objShellFilepro.disCreateshell();
								}
							}
						}
					}
				}
		} catch(Exception ex) {
			booRe = false;
		}
		return booRe;
	}
	
	public HashMap<String , String> disGetRes(){
		String strFname = " disGetRes : ";
		try {
			
		} catch(Exception ex) {
			
		}
		return mapRes;
	}

}
