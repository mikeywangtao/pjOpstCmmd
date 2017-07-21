package orgs.cm.pMqp.pShellpro;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ShellFilepro;

public class StandardShellpro_Getimg extends StandardShellpro{

	private final String strCname = StandardShellpro_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public StandardShellpro_Getimg(HashMap<String, String> mapParp) {
		mapPar = mapParp;
	}

	@Override
	public boolean disShellpro() {
		String strFname = "";
		try {
			String strFileroot = "/home/anshells";
			ArrayList<String> altShell = super.disGetAltshell();
			ShellFilepro objShellFilepro = new ShellFilepro(strFileroot, altShell);
			boolean booflg = objShellFilepro.disCreateshell();
		} catch(Exception ex) {
			
		}
		return false;
	}
	
	public HashMap<String , String> disGetRes(){
		String strFname = " disGetRes : ";
		try {
			
		} catch(Exception ex) {
			
		}
		return mapRes;
	}

}
