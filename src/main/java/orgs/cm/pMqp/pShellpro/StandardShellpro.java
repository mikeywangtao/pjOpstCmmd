package orgs.cm.pMqp.pShellpro;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class StandardShellpro extends AbsShellpro {

	private final String strCname = StandardShellpro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	protected ArrayList<String> disGetAltshell(){
		String strFname = " disGetAltshell : ";
		ArrayList<String> altRe = null;
		try {
			
		} catch(Exception ex) {
			altRe = null;
		}
		return altRe;
	}
}
