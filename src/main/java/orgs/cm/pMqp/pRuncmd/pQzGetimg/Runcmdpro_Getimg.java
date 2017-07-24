package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;
import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;
import orgs.cm.pMqp.pRuncmd.pjCreate00.Runcmdpro_Create00;

public class Runcmdpro_Getimg extends AbsRuncmdPro {

	private final String strCname = Runcmdpro_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private static final String strInfoflg = "imgJob";
	
	private HashMap<String, Object> hmpPar = new HashMap<>();
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		try {
			List<HashMap> altDataAnsible = disGetAnsible();
			if(altDataAnsible!=null && altDataAnsible.size()>0){
//				for(HashMap map : altDataAnsible){
					HashMap map = altDataAnsible.get(0);
					disSetParinfos(map);
					objPrepare = new RunPrepare_Getimg();
					objPrepare.disSetAll(hmpPar);
					super.disRunPrepare(objPrepare);
					
					
					objBefore = new RunBefore_Getimg();
					objBefore.disSetAll(hmpPar);
					super.disRunBefre(objBefore);
					objCmd = new RunCmd_Getimg();
					objCmd.disSetAll(hmpPar);
					super.disRunCmd(objCmd);
					objAfter = new RunAfter_Getimg();
					objAfter.disSetAll(hmpPar);
					super.dusRunAfter(objAfter);

//				}
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
	
	private void disSetParinfos(HashMap map){
		String strFname = " disSetPars : ";
		try {
			
			if(map!=null && map.size()>0){
				List<HashMap> altDataKeystone = disGetKeustone();
				
				if(altDataKeystone!=null && altDataKeystone.size()>0){
					UUID objUuid = UUID.randomUUID();
					HashMap<String, String> hmpAllInp = new HashMap<>();
					
					hmpAllInp.put("^ansid^", "1");
					hmpAllInp.put("^anscmmd^", "openstack");
					hmpAllInp.put("^ansinfo^", "ansinfo......");
					
					hmpAllInp.put("^req_type^", "qz");
					hmpAllInp.put("^req_subtype^", "getimg");
					
					hmpAllInp.put("^customerids^", "quartz");
					
					hmpAllInp.put("^pdom^", "Default");
					hmpAllInp.put("^udom^", "Default");
					hmpAllInp.put("^pname^", "admin");
					hmpAllInp.put("^uname^", "admin");
					hmpAllInp.put("^pass^", "admin");
					hmpAllInp.put("^authurl^", "http://test-controller:5000/v3");
					hmpAllInp.put("^ideapi^", "3");
					hmpAllInp.put("^imgapi^", "2");
					

					
					hmpPar.put(ProcessAttrs.strParmapKey_Inpars, hmpAllInp);
					hmpPar.put("^ansid^", "1");
					hmpPar.put("^anscmmd^", "openstack");
					hmpPar.put("^ansinfo^", "ansinfo......");
					hmpPar.put(ProcessAttrs.strInfoKey_Cpuuid, objUuid.toString().replaceAll("-", ""));
					hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "f");
					hmpPar.put(ProcessAttrs.strParmapKey_Ppa_RunShCmmd, null);
					hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFileroot, 
							"/home/anshells/" + DatePro.disGetYear4now()+DatePro.disGetWeek4now());
					hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilename, 
							"/" 
							+ DatePro.disGetStrdate4NowObjSdf001() 
							+ "_" + hmpAllInp.get("^req_type^").toString()
							+ hmpAllInp.get("^req_subtype^").toString() 
							+ ".sh");
				} else {
					hmpPar = null;
				}
			}
		} catch(Exception ex) {
			
		}
	}
	
	private List<HashMap> disGetKeustone(){
		String strFname = "";
		List<HashMap> altDataKeystone = null;
		try {
			
			Map<String, Object> mapParKeystone = new HashMap<>(); 
//			mapParKeystone.put("ksId", "1"); 
//			mapParKeystone.put("dom", "1"); 
//			mapParKeystone.put("project", "1"); 
//			mapParKeystone.put("ideenity", "1"); 
//			mapParKeystone.put("imgapiv", "1"); 
//			mapParKeystone.put("ansibleId", "1"); 
			String strParKeystone = JSON.toJSONString(mapParKeystone);
			String strKeystone = HttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/keystone/getKeystone", strParKeystone);
			
			Map<String, Object> mapResKeystone = JSON.parseObject(strKeystone, HashMap.class);
			if(mapResKeystone!=null && mapResKeystone.size()>0
					&& mapResKeystone.containsKey("msg") && mapResKeystone.get("msg")!=null
					&& mapResKeystone.containsKey("data") && mapResKeystone.get("data")!=null){
				String strMsg = mapResKeystone.get("msg")==null?null:mapResKeystone.get("msg").toString();
				if("ok".equals(strMsg)){
					String strDataKeystone = mapResKeystone.get("data").toString();
					if(strDataKeystone!=null && strDataKeystone.trim().length()>0){
						altDataKeystone = JSON.parseArray(strDataKeystone, HashMap.class);
//						Object[] subKey = altDataKeystone.get(0).keySet().toArray();
//						if(subKey!=null && subKey.length>0){
//							for(HashMap map : altDataKeystone){
//								System.out.print("Keystone ----");
//								for(Object objkey : subKey){
//									String strVal = map.get(objkey)==null? "":map.get(objkey).toString();
//									System.out.print(objkey + ":" + strVal + ", ");
//									/*
//									 * strPDom
//									 * strUDom
//									 * strProject
//									 * strUserName
//									 * strPassword
//									 * strAuthUrl
//									 * strIdeenity
//									 * strImgapiv
//									 * **intAnsibleId
//									 */
//								}
//								System.out.println("");
//							}
//						}
					}
				}
			}
		} catch(Exception ex) {
			
		}
		return altDataKeystone;
	}
	private List<HashMap> disGetAnsible(){
		String strFname = " disGetAnsible : ";
		List<HashMap> altDataAnsible = null;
		try {
			Map<String, Object> mapParAnsible = new HashMap<>(); 
//			mapParAnsible.put("id", "1"); 
//			mapParAnsible.put("ip", "1"); 
//			mapParAnsible.put("sshKey", "1"); 
			String strParAnsible = JSON.toJSONString(mapParAnsible);
			String strAnsible = HttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/ansible/getAnsible", strParAnsible);
			
			Map<String, Object> mapResAnsible = JSON.parseObject(strAnsible, HashMap.class);
			if(mapResAnsible!=null && mapResAnsible.size()>0
					&& mapResAnsible.containsKey("msg") && mapResAnsible.get("msg")!=null
					&& mapResAnsible.containsKey("data") && mapResAnsible.get("data")!=null){
				String strMsg = mapResAnsible.get("msg")==null?null:mapResAnsible.get("msg").toString();
				if("ok".equals(strMsg)){
					String strDataAnsible = mapResAnsible.get("data").toString();
					if(strDataAnsible!=null && strDataAnsible.trim().length()>0){
						altDataAnsible = JSON.parseArray(strDataAnsible, HashMap.class);
						Object[] subKey = altDataAnsible.get(0).keySet().toArray();
//						if(subKey!=null && subKey.length>0){
//							for(HashMap map : altDataAnsible){
//								System.out.print("Ansible ----");
//								for(Object objkey : subKey){
//									String strVal = map.get(objkey)==null? "":map.get(objkey).toString();
//									System.out.print(objkey + ":" + strVal + ", ");
//									/*
//									 intId
//									 strName
//									 strIp
//									 strLoginName
//									 strPassword
//									 strSshKey
//									 */
//								}
//								System.out.println("");
//							}
//						}
					}
				}
			}
			
		} catch(Exception ex) {
			
		}
		return altDataAnsible;
	}
	
	public void disSetPars(HashMap<String, Object> hmpParp){
		String strFname = " disRuncmdPro : ";
		this.hmpPar = hmpParp;
	}

}