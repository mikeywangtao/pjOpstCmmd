package orgs.cm.pMqp.pMqlst;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pProlst.ThrdRunManage;
import orgs.cm.pMqp.pProlst.ThrdRuncmdPro;

//import com.creditharmony.core.thd.pools.ThreadPool01;

public class ThrdReceiveDispense extends Thread {

	private final String strCname = ThrdReceiveDispense.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
//	private Properties pptMDB = null;
	private String strMsg = null;
	private String strFlg = null;
	private String strServerTarget = null;
//	private String strProflg = null;
	public void setStrServerTarget(String strServerTarget){
		this.strServerTarget = strServerTarget;
	}
	public void setStrFlg(String strFlg){
		this.strFlg = strFlg;
	}
	public void setStrMsg(String strMsg){
		this.strMsg = strMsg;
	}
//	public void setPptMDB(Properties pptMDB){
//		this.pptMDB = pptMDB;
//	}
	
	@Override
	public void run() {
		String strFname = " run : ";
		String strProflg = null;

		try {
			logger.info(strCname + strFname + " Start!");
			if(this.strMsg!=null && this.strMsg.trim().length()>0){
				HashMap<String, Object> mapJson = null;
				try {
					mapJson = JSON.parseObject(strMsg, HashMap.class);
				} catch(Exception e) {
					long lonFlg = System.currentTimeMillis();
					logger.error(strCname + strFname + e + "||" + lonFlg);
					StackTraceElement[] subSte = e.getStackTrace();
					for(int i=0; i<subSte.length; i++){
						logger.error(
								subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
					}
				}
				
//				export OS_PROJECT_DOMAIN_NAME=Default 
//				export OS_USER_DOMAIN_NAME=Default 
//				export OS_PROJECT_NAME=admin 
//				export OS_USERNAME=admin 
//				export OS_PASSWORD=admin 
//				export OS_AUTH_URL=http://test-controller:5000/v3 
//				export OS_IDENTITY_API_VERSION=3 
//				export OS_IMAGE_API_VERSION=2 
						
				/**
				 * sysflg:cms \ auth
				 * serverTarget:test \ pdt
				 * proflg:CREATE \ EDIT \ SSRD \ IFN \ OPRO
				 * subProflg:CREATE:CREATE00 \ EDIT:EDIT00 \ SSRD:START STOP RESTART DESTROY \ IFN:IMG FLV NETW \ OPRO:
				 * usr.name
				 * usr.login_name
				 * ks.OS_PROJECT_DOMAIN_NAME
				 * ks.OS_USER_DOMAIN_NAME
				 * ks.OS_PROJECT_NAME
				 * ks.OS_USERNAME
				 * ks.OS_PASSWORD
				 * ks.OS_AUTH_URL
				 * ks.OS_IDENTITY_API_VERSION
				 * ks.OS_IMAGE_API_VERSION
				 * */
				/*
				 * /queue/cms_test
				 {"proflg":"cms_test"}
				 */
				if(mapJson!=null && mapJson.size()>0
						&& mapJson.containsKey("^req_type^")){
					Object objProflg = mapJson.get("^req_type^");
					strProflg = objProflg==null? null:objProflg.toString();
					
					if(strProflg!=null && strProflg.trim().length()>0){
						if("CREATE".equals(strProflg.trim())){
							strProflg = "CREATE";
						}
						if("EDIT".equals(strProflg.trim())){
							strProflg = "EDIT";
						}
						if("START".equals(strProflg.trim())
								|| "STOP".equals(strProflg.trim())
								|| "DEST".equals(strProflg.trim())
								|| "RES".equals(strProflg.trim())){
							strProflg = "SSRD";
						}
						if(ThrdRunManage.chmthdrMang_RuncmdPro.containsKey(strProflg)){
							ThrdRuncmdPro objThrdRuncmdPro = new ThrdRuncmdPro();
							objThrdRuncmdPro.setMsg(mapJson);
							ThrdRunManage.chmthdrMang_RuncmdPro.get(strProflg.toUpperCase()).putThread2Mlt((Runnable)objThrdRuncmdPro);
							
						} else {
							logger.error(strCname + strFname + " proflg Error!" + strMsg);
							logger.info(strCname + strFname + " proflg Error!" + strMsg);
						}
					} else {
						logger.error(strCname + strFname + " proflg null!" + strMsg);
						logger.info(strCname + strFname + " proflg null!" + strMsg);
					}
				} else {
					logger.error(strCname + strFname + " mapJson Error!" + strMsg);
					logger.info(strCname + strFname + " mapJson Error!" + strMsg);
				}
			} else {
				logger.error(strCname + strFname + " Msg Error!" + strMsg);
				logger.info(strCname + strFname + " Msg Error!" + strMsg);
			}

		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			logger.error(strCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				logger.error(
						subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
	}


}
