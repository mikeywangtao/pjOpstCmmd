package orgs.cm.pMqp.pShellpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.ShellFilepro;

public class StandardShellpro_Getimg extends StandardShellpro{

	private final String strCname = StandardShellpro_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public StandardShellpro_Getimg(HashMap<String, Object> mapParp) {
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
				&& mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdids)!=null ){
				
				String strCmd = mapPar.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd).toString();
				String strCmdids = mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdids).toString();
				if(strCmd.trim().length()>0
						&& strCmdids.trim().length()>0){
					String[] subCmd = strCmd.split(",");
					String[] subCmdids = strCmdids.split(",");
					//验证命令有3个
					if(subCmd!=null && subCmd.length==1
							&& subCmdids!=null && subCmdids.length==1){
						for(int i=1; i<=subCmdids.length; i++){
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
