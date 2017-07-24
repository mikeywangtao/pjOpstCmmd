package orgs.cm.pMqp.pShellpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.ShellFilepro;

public class StandardShellpro_Create00 extends StandardShellpro{

	private final String strCname = StandardShellpro_Create00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public StandardShellpro_Create00(HashMap<String, Object> mapParp) {
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
				&& mapPar.containsKey(ProcessAttrs.strParmapKey_Ppa_Cmdshr)
				&& mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdshr)!=null ){
				
				String strFileroot = mapPar.get("strShFileroot").toString();
				String strFilename = mapPar.get("strShFilename").toString(); 
				ArrayList<String> altShell = (ArrayList<String>)mapPar.get(ProcessAttrs.strParmapKey_Ppa_Cmdshr); 
				ShellFilepro objShellFilepro = new ShellFilepro(strFileroot, strFilename, altShell);
				booRe = objShellFilepro.disCreateshell();
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
