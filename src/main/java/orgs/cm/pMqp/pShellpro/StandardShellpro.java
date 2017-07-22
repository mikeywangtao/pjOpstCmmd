package orgs.cm.pMqp.pShellpro;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class StandardShellpro extends AbsShellpro {

	private final String strCname = StandardShellpro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
//	protected ArrayList<String> disGetAltshell(){
//		String strFname = " disGetAltshell : ";
//		ArrayList<String> altRe = null;
//		try {
//			if(mapPar!=null){
//				altRe = new ArrayList<>();
//				altRe.add("#!/bin/bash ");
//				altRe.add("export OS_PROJECT_DOMAIN_NAME=^pdom^ "); 
//				altRe.add("export OS_USER_DOMAIN_NAME=^udom^ ");
//				altRe.add("export OS_PROJECT_NAME=^pname^ ");
//				altRe.add("export OS_USERNAME=^uname^ ");
//				altRe.add("export OS_PASSWORD=^pass^ ");
//				altRe.add("export OS_AUTH_URL=^authurl^ ");
//				altRe.add("export OS_IDENTITY_API_VERSION=^ideapi^ ");
//				altRe.add("export OS_IMAGE_API_VERSION=^imgapi^ ");
//			}
//		} catch(Exception ex) {
//			altRe = null;
//		}
//		return altRe;
//	}
}
