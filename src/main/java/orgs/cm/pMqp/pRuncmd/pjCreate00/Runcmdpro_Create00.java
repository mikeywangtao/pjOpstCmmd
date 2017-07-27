package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

/**
 * 创建 Vm 虚拟机。
 * 创建一个。
 * */
public class Runcmdpro_Create00 extends AbsRuncmdPro {

	private final String strCname = Runcmdpro_Create00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> hmpPar;
	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		
		String strInfo = "";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		try {
			
			strInfo = strCname + strFname + " Start!" ;
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			
			strInfo = strCname + strFname + " Input----" + hmpPar.toString();
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PAx+" input map par ");
			
			disSetParinfos();
			List<HashMap> altDataAnsible = disGetAnsible();
			if(altDataAnsible!=null && altDataAnsible.size()==1){
				HashMap map = altDataAnsible.get(0);
				
				objPrepare = new RunPrepare_Create00();
				objPrepare.disSetAll(hmpPar);
				super.disRunPrepare(objPrepare);
				
				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "1".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + " run cmmd 1 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
					altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRx+" run cmmd 1 ");
					
					objBefore = new RunBefore_C00_1();
					objBefore.disSetAll(hmpPar);
					super.disRunBefre(objBefore);
					objBefore = null;
					
					objCmd = new RunCmd_C00_1();
					objCmd.disSetAll(hmpPar);
					super.disRunCmd(objCmd);
					objCmd = null;
					
					objAfter = new RunAfter_C00_1();
					objAfter.disSetAll(hmpPar);
					super.dusRunAfter(objAfter);
					objAfter = null;
				}
				
				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "2".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + " run cmmd 2 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
					altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRx+" run cmmd 2 ");
					
					String strRunlopp = null;
					do {
						objBefore = new RunBefore_C00_2();
						objBefore.disSetAll(hmpPar);
						super.disRunBefre(objBefore);
						objBefore = null;
						
						objCmd = new RunCmd_C00_2();
						objCmd.disSetAll(hmpPar);
						super.disRunCmd(objCmd);
						objCmd = null;
						
						objAfter = new RunAfter_C00_2();
						objAfter.disSetAll(hmpPar);
						super.dusRunAfter(objAfter);
						objAfter = null;
						strRunlopp = hmpPar.get(ProcessAttrs.strParmapKey_Ppa_RunLoopFlg).toString();
						
						strInfo = strCname + strFname + " loop (do while) : strRunlopp ----" + strRunlopp;
						altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PLx+" loop (do while) ");
					} while("t".equals(strRunlopp));

				}

				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "3".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + " run cmmd 3 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
					altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRx+" run cmmd 3 ");
					
					objBefore = new RunBefore_C00_3();
					objBefore.disSetAll(hmpPar);
					super.disRunBefre(objBefore);
					objBefore = null;
					
					objCmd = new RunCmd_C00_3();
					objCmd.disSetAll(hmpPar);
					super.disRunCmd(objCmd);
					objCmd = null;
					
					objAfter = new RunAfter_C00_3();
					objAfter.disSetAll(hmpPar);
					super.dusRunAfter(objAfter);
					objAfter = null;
				}
				
				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "4".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + " run cmmd 4 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
					altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRx+" run cmmd 4 ");
					
//					Thread.sleep(5000);
					objBefore = new RunBefore_C00_4();
					objBefore.disSetAll(hmpPar);
					super.disRunBefre(objBefore);
					objBefore = null;
					
					objCmd = new RunCmd_C00_4();
					objCmd.disSetAll(hmpPar);
					super.disRunCmd(objCmd);
					objCmd = null;
					
					objAfter = new RunAfter_C00_4();
					objAfter.disSetAll(hmpPar);
					super.dusRunAfter(objAfter);
					objAfter = null;
				}
			} else {
				throw new Exception("hmpPar Error ! is null or is empty!");
			}
			strInfo = strCname + strFname + " End!" ;
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRE);
			
			disSaveInfo();
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
	}
	
	private void disSaveInfo(){
		String strFname = " disSaveInfo : ";
		try {
			if(altRunc!=null && altRunc.size()>0){
				for(LinkedHashMap<String, String> mapRow : altRunc){
					System.out.println(mapRow);
				}
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
	}
	
	private void disSetParinfos(){
		String strFname = " disSetPars : ";
		try {
			
			List<HashMap> altDataKeystone = disGetKeustone();
			
			if(altDataKeystone!=null && altDataKeystone.size()>0){
				UUID objUuid = UUID.randomUUID();
				HashMap<String, String> hmpAllInp = new HashMap<>();
				hmpAllInp.put("^ansid^", "1");
				hmpAllInp.put("^anscmmd^", "openstack");
				
				hmpAllInp.put("^req_type^", "CREATE");
				hmpAllInp.put("^req_subtype^", "CREATE00");
				
				hmpAllInp.put("^customerids^", "20170725000");
				hmpAllInp.put("^uksids^", "");
				
				hmpAllInp.put("^pdom^", "Default");
				hmpAllInp.put("^udom^", "Default");
				hmpAllInp.put("^pname^", "admin");
				hmpAllInp.put("^uname^", "admin");
				hmpAllInp.put("^pass^", "admin");
				hmpAllInp.put("^authurl^", "http://test-controller:5000/v3");
				hmpAllInp.put("^ideapi^", "3");
				hmpAllInp.put("^imgapi^", "2");
				
				hmpAllInp.put("^shell_allpath^", null);
				hmpAllInp.put("^intImaId^", "1");
				hmpAllInp.put("^intTemId^", "2");
				hmpAllInp.put("^intNwId^", "3");
				hmpAllInp.put("^strVmUser^", "88");
				hmpAllInp.put("^strVmPassword^", "77");
				hmpAllInp.put("^strSshKey^", "66");
				hmpAllInp.put("^imgids^", "20022a68-bc87-462d-ba6c-af6570ba839e");
				hmpAllInp.put("^devname^", "dev-"+objUuid.toString().replaceAll("-", ""));
				hmpAllInp.put("^devids^", null);
				hmpAllInp.put("^flvids^", "0");
				hmpAllInp.put("^netwids^", "aedbece2-0b64-4879-94e5-461439cd6930");
				hmpAllInp.put("^vmname^", "vm-"+objUuid.toString().replaceAll("-", ""));
				hmpAllInp.put("^vmids^", null);
				
//					HashMap<String, Object> hmpAllInp = null;
//					hmpAllInp = hmpPar;
				hmpPar = new HashMap<>();
				
				hmpPar.put(ProcessAttrs.strParmapKey_Inpars, hmpAllInp);
				hmpPar.put("^ansid^", hmpAllInp.get("^ansid^"));
				hmpPar.put("^anscmmd^", hmpAllInp.get("^anscmmd^"));
				hmpPar.put("^req_type^", hmpAllInp.get("^req_type^"));
				hmpPar.put("^req_subtype^", hmpAllInp.get("^req_subtype^"));
				hmpPar.put("^customerids^", hmpAllInp.get("^customerids^"));
				hmpPar.put("^uksids^", hmpAllInp.get("^uksids^"));
				hmpPar.put("^devname^", "dev-"+objUuid.toString().replaceAll("-", ""));
				hmpPar.put("^devids^", null);
				hmpPar.put("^vmids^", null);
				
				hmpPar.put(ProcessAttrs.strInfoKey_Cpuuid, objUuid.toString().replaceAll("-", ""));
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilecflg, "f");
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_RunShCmmd, null);
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFileroot, 
						"/home/anshells/" + DatePro.disGetYear4now()+DatePro.disGetWeek4now());
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_ShFilename, 
						"/" 
						+ DatePro.disGetStrdate4NowObjSdf001() 
						+ "_" + hmpAllInp.get("^req_type^").toString()
						+ hmpAllInp.get("^req_subtype^").toString() );
//							+ ".sh");
			} else {
				hmpPar = null;
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
			HttpClientUtil objHttpClientUtil = new HttpClientUtil();
			String strKeystone = objHttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/keystone/getKeystone", strParKeystone);
			
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
		String strAnsids = null;
		try {
			if(hmpPar!=null
					&& hmpPar.containsKey("^ansid^") 
					&& hmpPar.get("^ansid^")!=null){
				strAnsids = hmpPar.get("^ansid^").toString();
			}
			if(strAnsids!=null && strAnsids.trim().length()>0){
				Map<String, Object> mapParAnsible = new HashMap<>(); 
				mapParAnsible.put("intId", strAnsids); 
//				mapParAnsible.put("ip", "1"); 
//				mapParAnsible.put("sshKey", "1"); 
				String strParAnsible = JSON.toJSONString(mapParAnsible);
				HttpClientUtil objHttpClientUtil = new HttpClientUtil();
				String strAnsible = objHttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/ansible/getAnsible", strParAnsible);
				
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
//							if(subKey!=null && subKey.length>0){
//								for(HashMap map : altDataAnsible){
//									System.out.print("Ansible ----");
//									for(Object objkey : subKey){
//										String strVal = map.get(objkey)==null? "":map.get(objkey).toString();
//										System.out.print(objkey + ":" + strVal + ", ");
//										/*
//										 intId
//										 strName
//										 strIp
//										 strLoginName
//										 strPassword
//										 strSshKey
//										 */
//									}
//									System.out.println("");
//								}
//							}
						}
					}
				}
			}
		} catch(Exception ex) {
			
		}
		return altDataAnsible;
	}
	
	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
			, LinkedHashMap<String, String> lhpInfop
			, ArrayList<LinkedHashMap<String, String>> altRuncp
			, String strInfoTypepFlgp){
		String strTypef = "";
		String strFlgf = "";
		String strSubflgf = "";
		if(strInfoTypepFlgp!=null && strInfoTypepFlgp.trim().length()>0){
			String[] subTypeFlg = strInfoTypepFlgp.split("}}}", -1);
			if(subTypeFlg!=null && subTypeFlg.length>=2){
				strTypef = subTypeFlg[0];
				strFlgf = subTypeFlg[1];
				strSubflgf = subTypeFlg[2];
			}
		}
		LinkedHashMap<String, String> lhpInfof = null;
		String strInfo = strInfop;
		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo);
		lhpInfof.put(ProcessAttrs.strInfoType_Info, strTypef);
		lhpInfof.put(ProcessAttrs.strInfoFlg_Info, strFlgf);
		lhpInfof.put(ProcessAttrs.strInfoSubflg_Info, strSubflgf);
		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
		altRuncp.add(lhpInfof);
		return altRuncp;
	}
	
	private void disOutputLog(String strFnamep, Exception exp){
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}
	
	public void disSetPars(HashMap<String, Object> hmpParp){
		String strFname = " disRuncmdPro : ";
		this.hmpPar = hmpParp;
	}

}
