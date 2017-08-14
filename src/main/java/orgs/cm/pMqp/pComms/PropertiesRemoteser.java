package orgs.cm.pMqp.pComms;

import java.util.Properties;

public class PropertiesRemoteser {

	public static synchronized String disGetval(String strKeys){
		String strFname = " disGetval : ";
		String strRe = null;
		
		try {
			if(strKeys!=null && strKeys.trim().length()>0){
				Properties objPptst = Propertiesmap.getPpt("sysTarget.properties");
				Properties objPptrs = Propertiesmap.getPpt("remoteser.properties");
				
				if(objPptst!=null && objPptrs!=null){
					String strServerTarget = null;
					if(objPptst!=null){
						if(objPptst.containsKey("serverTarget") && objPptst.get("serverTarget")!=null){
							strServerTarget = objPptst.get("serverTarget").toString();
							if(strServerTarget.trim().length()>0){
								if(objPptrs.containsKey(strServerTarget+"_"+strKeys)
										&& objPptrs.get(strServerTarget+"_"+strKeys)!=null){
									strRe = objPptrs.get(strServerTarget+"_"+strKeys).toString();
								}
							}
						}
					}
				}
			}
			
		} catch(Exception ex) {
			strRe = null;
		}
		return strRe;
	}

	
	
}
