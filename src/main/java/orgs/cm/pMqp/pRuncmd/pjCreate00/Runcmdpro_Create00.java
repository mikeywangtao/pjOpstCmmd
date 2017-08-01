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
import orgs.cm.pMqp.pComms.IdsPro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunFinally;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;
import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;

/**
 * 创建 Vm 虚拟机。
 * 创建一个。
 * */
public class Runcmdpro_Create00 extends AbsRuncmdPro implements Runnable {

	private final String strCname = Runcmdpro_Create00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> hmpPar = new HashMap<>();
	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	
	public void run(){
		disRuncmdPro();
	}
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		
		String strInfo = "";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		try {
			disSetParinfos();
			disSetInfobase();
			
			strInfo = strCname + strFname + " Start!" ;
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			strInfo = strCname + strFname + "000 Input----" + hmpPar.toString();
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PAx+" input map par ");
			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Cp);

			List<HashMap> altDataAnsible = disGetAnsible();
			if(altDataAnsible!=null && altDataAnsible.size()==1){
				HashMap map = altDataAnsible.get(0);
				
				objPrepare = new RunPrepare_Create00();
				objPrepare.disSetAll(hmpPar);
				super.disRunPrepare(objPrepare);
				
				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "1".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + 
							ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
							" run cmmd 1 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
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
					strInfo = strCname + strFname + 
							ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
							" run cmmd 2 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
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
						
						strInfo = strCname + strFname + 
								ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
								" loop (do while) : strRunlopp ----" + strRunlopp;
						altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PLx+" loop (do while) ");
					} while("t".equals(strRunlopp));

				}

				if(hmpPar.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
						&& hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
						&& "3".equals(hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
					strInfo = strCname + strFname + 
							ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
							" run cmmd 3 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
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
					strInfo = strCname + strFname + 
							ProcessAttrs.strParmapKey_Ppa_NowRunflg + ":" + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg) +
							" run cmmd 4 " + hmpPar.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg);
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
			strInfo = strCname + strFname + "999 End!" ;
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRE);
			
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		} finally {
			AbsRunFinally objRunFinally = new RunFinally_Create00();
			objRunFinally.disSetAll(hmpPar);
			objRunFinally.disRunFinally();
			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}
	}

	

	/**
	 * 运行日志基本信息构建
	 * */
	private void disSetInfobase(){
		String strFname = " disSetInfobase : ";
		
		try {
			lhpInfobase.put("cp_ids",IdsPro.disGetIds()); //DatePro.disGetStrdate4NowObjSdf001());
			lhpInfobase.put("cpcls", strCname);
			lhpInfobase.put("customer", hmpPar.get("^customerids^").toString());
			lhpInfobase.put("ansible_ids", hmpPar.get("^ansid^").toString());
			lhpInfobase.put("ansible_info", hmpPar.get("^anscmmd^").toString());
			lhpInfobase.put("cmd_tpye", hmpPar.get("^req_type^").toString());
			lhpInfobase.put("cmd_subtype", hmpPar.get("^req_subtype^").toString());
			lhpInfobase.put("cmd_request", hmpPar.get(ProcessAttrs.strParmapKey_Inpars).toString());
			lhpInfobase.put("cmd_inputdt", DatePro.disGetStrdate4NowObjSdf001());
			lhpInfobase.put("cpuuid", hmpPar.get(ProcessAttrs.strInfoKey_Cpuuid).toString());
			lhpInfobase.put("cmdrundt", DatePro.disGetStrdate4NowObjSdf001());
			lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Pro);
			
			hmpPar.put(ProcessAttrs.strParmapKey_Infobase, lhpInfobase);
			hmpPar.put("cp_ids",lhpInfobase.get("cp_ids"));
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
	}
	
	/**
	 * 传入参数处理
	 * */
	private void disSetParinfos(){
		String strFname = " disSetPars : ";
		try {
			
			List<HashMap> altDataKeystone = disGetKeustone();
			
			if(altDataKeystone!=null && altDataKeystone.size()>0){
/*
{^pname^=admin, 
^ansid^=1, 
msgId=2017073116040150856914, 
^intImaId^=12, 
^strSshKey^=1, 
^uname^=admin, 
^strVmUser^=1, 
^shell_allpath^=, 
^intNwId^=1, 
^netwids^=aedbece2-0b64-4879-94e5-461439cd6930, 
usr_name=username, 
^authurl^=http://test-controller:5000/v3, 
^flvids^=0, 
^imgapi^=2, 
^uksids^=1, 
sysflg=cms, 
^vmids^=, 
^ideapi^=3, 
^intTemId^=0, 
^pdom^=Default, 
^pass^=admin, 
^customerids^=1, 
^imgids^=20022a68-bc87-462d-ba6c-af6570ba839e, 
serverTarget=test, 
login_name=wode, 
^udom^=Default, 
^anscmmd^=openstack, 
^vmname^=vv, 
^req_type^=CREATE, 
^devids^=, 
^req_subtype^=CREATE00, 
^strVmPassword^=1}
 */
//				UUID objUuid = UUID.randomUUID();
//				HashMap<String, String> hmpAllInp = new HashMap<>();
//				hmpAllInp.put("^ansid^", "1");
				
				UUID objUuid = UUID.randomUUID();
				HashMap<String, Object> hmpAllInp = null;
				hmpAllInp = hmpPar;
				hmpAllInp.put("^devname^", "dev-"+objUuid.toString().replaceAll("-", ""));
				hmpAllInp.put("^devids^", null);
				hmpAllInp.put("^vmids^", null);
				hmpAllInp.put("^shell_allpath^", "");
				if(!hmpAllInp.containsKey("^vmname^")
						|| hmpAllInp.get("^vmname^")==null
						|| (hmpAllInp.get("^vmname^")!=null 
							&& hmpAllInp.get("^vmname^").toString().trim().length()==0)
						|| (hmpAllInp.get("^vmname^")!=null 
							&& hmpAllInp.get("^vmname^").toString().trim().length()>0) 
							&& "None".equals(hmpAllInp.get("^vmname^").toString())){
					hmpAllInp.put("^vmname^", "vm-"+objUuid.toString().replaceAll("-", ""));
				}
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
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_RunResLst, new ArrayList<String>());
				
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
	
	
	private void disSaveInfo(String strFlgp){
		String strFname = " disSaveInfo : ";
		try {
			if(strFlgp!=null && strFlgp.trim().length()>0
					&& altRunc!=null && altRunc.size()>0){
//				for(LinkedHashMap<String, String> mapRow : altRunc){
//					System.out.println(mapRow);
//				}
				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
				DbInfoSavepro objDbInfoSavepro = new DbInfoSavepro(DbproAttrs.strDbflg_Cmd, disGetBusname());
				if(DbInfoSaveAttrs.strSaveFlg_Cp.equals(strFlgp.trim())){
					int intNum = objDbInfoSavepro.disSaveCpinfo(altRunc);
					if(intNum==1){
						logger.info(strCname + strFname + " Cp完整存储!");
					} else {
						logger.info(strCname + strFname + " Cp存储异常!");
					}
				}
				if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
					int intNum = objDbInfoSavepro.disSaveRuninfo(altRunc);
					if(intNum==altRunc.size()){
						logger.info(strCname + strFname + " Run完整存储!");
					} else {
						logger.info(strCname + strFname + " Run存储异常!");
					}
				}

			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
	}
	private String disGetBusname(){
		String strFname = " disGetBusname : ";
		String strRe = "";
		try {
			String strPackage = this.getClass().getPackage().getName();
			String[] subTmp = strPackage.split("\\.");
			if(subTmp!=null && subTmp.length>1){
				strPackage = subTmp[subTmp.length-1];
			}
			if(strPackage.indexOf(".")==-1){
				strPackage = strPackage.toLowerCase();
			}
			strRe = strPackage;
		} catch(Exception ex) {
			strRe = "";
			disOutputLog(strFname, ex);
		}
		return strRe;
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
				strTypef = subTypeFlg[0].trim();
				strFlgf = subTypeFlg[1].trim();
				strSubflgf = subTypeFlg[2].trim();
			}
		}
		LinkedHashMap<String, String> lhpInfof = null;
		String strInfo = strInfop;
		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo.replaceAll("'", "\""));
		lhpInfof.put(ProcessAttrs.strInfoType_Info, strTypef);
		lhpInfof.put(ProcessAttrs.strInfoFlg_Info, strFlgf);
		lhpInfof.put(ProcessAttrs.strInfoSubflg_Info, strSubflgf);
		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
		altRuncp.add(lhpInfof);
		return altRuncp;
	}
	private void disOutputLog(String strFnamep, Exception exp){
		String strInfo = "";
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		strInfo = strCname + strFnamep + exp + "||" + lonFlg ;
		altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_EEx + " P00000");
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			strInfo = subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg ;
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_ETx + " --" + i);
		}
	}
	public void disSetPars(HashMap<String, Object> hmpParp){
		String strFname = " disRuncmdPro : ";
		this.hmpPar = hmpParp;
	}

}
