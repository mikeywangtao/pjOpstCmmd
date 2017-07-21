package orgs.cm.pMqp.pShellpro;

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
			ShellFilepro objShellFilepro = new ShellFilepro(null, null);
			boolean booflg = objShellFilepro.disCreateshell();
		} catch(Exception ex) {
			
		}
		return false;
	}

}
