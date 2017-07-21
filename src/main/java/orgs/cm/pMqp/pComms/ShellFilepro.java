package orgs.cm.pMqp.pComms;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pShellpro.StandardShellpro_Getimg;

public class ShellFilepro {

	private final String strCname = StandardShellpro_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private ShellFilepro(){}
	
	public ShellFilepro(String strFilerootp, ArrayList<String>  altShellp){
		
	}
	
	public boolean disCreateshell(){
		String strFname = " disCreateshell : ";
		boolean booRe = false;
		try {
			
		} catch(Exception ex) {
			
		}
		return booRe;
	}
}
