package orgs.cm.pMqp.pRuncmd.pQzGetnetw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.IdsPro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunBefore;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunPrepare;
import orgs.cm.pMqp.pRuncmd.comm.AbsRuncmdPro;
import orgs.cm.pMqp.pRuncmd.pAnsible.AnsiblePro;
import orgs.cm.pMqp.pRuncmd.pKeystone.KeystonePro;

public class Runcmdpro_Getnetw extends AbsRuncmdPro implements Runnable {

	private final String strCname = Runcmdpro_Getnetw.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private static final String strInfoflg = "netwJob";
	
	private ClsBaseAttrs objBa = null;
	private HashMap<String, Object> hmpPar = new HashMap<>();
	
	public void run(){
		disRuncmdPro();
	}
	
	public void disRuncmdPro(){
		String strFname = " disRuncmdPro : ";
		AbsRunPrepare objPrepare = null;
		AbsRunBefore objBefore = null;
		AbsRunCmd objCmd = null;
		AbsRunAfter objAfter = null;
		
		SaveInfoPro objSaveInfoPro = null;
		List<HashMap> altDataAnsible = null;
		List<HashMap> altDataKeystone = null;
		
		try {
			disSetParinfos();
			disSetInfobase();
			
			AnsiblePro objAnsiblePro = new AnsiblePro(strCname, hmpPar);
			altDataAnsible = objAnsiblePro.disGetAnsible_All();
			KeystonePro objKeystonePro = new KeystonePro(strCname, hmpPar);
			altDataKeystone = objKeystonePro.disGetKeustone_All();

			List<HashMap> lstAnsKsall = disSetAnsKs(altDataAnsible, altDataKeystone);
			if(lstAnsKsall!=null && lstAnsKsall.size()>0){
				for(HashMap mapRow : lstAnsKsall){
					if(mapRow!=null && mapRow.size()>0){
						if(disSetInpars(mapRow)){
							objBa = new ClsBaseAttrs(strCname);
							objBa.lhpInfobase = (LinkedHashMap<String, String>)hmpPar.get(ProcessAttrs.strParmapKey_Infobase);
							objSaveInfoPro = new SaveInfoPro(strCname, objBa);
							
							objPrepare = new RunPrepare_Getnetw();
							objPrepare.disSetHmpall(hmpPar);
							objPrepare.disSetClsBaseAttrs(objBa);
							super.disRunPrepare(objPrepare);
							
							objBefore = new RunBefore_Getnetw();
							objBefore.disSetHmpall(hmpPar);
							objBefore.disSetClsBaseAttrs(objBa);
							super.disRunBefre(objBefore);
							
							objCmd = new RunCmd_Getnetw();
							objCmd.disSetHmpall(hmpPar);
							objCmd.disSetClsBaseAttrs(objBa);
							super.disRunCmd(objCmd);
							
							objAfter = new RunAfter_Getnetw();
							objAfter.disSetHmpall(hmpPar);
							objAfter.disSetClsBaseAttrs(objBa);
							super.dusRunAfter(objAfter);
						}
					}
				}
			}
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} finally {
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
		}
	}
	
	private boolean disSetInpars(HashMap mapRowAnsksp){
		String strFname = " disSetInpars : ";
		boolean booRe = false;
		try {
			if(hmpPar!=null && hmpPar.size()>0
					&& mapRowAnsksp!=null && mapRowAnsksp.size()>0){
				HashMap<String, Object> hmpAllInp = new HashMap<>();
				
				hmpAllInp.put("^pdom^", mapRowAnsksp.get("strPDom").toString()); 
				hmpAllInp.put("^udom^", mapRowAnsksp.get("strUDom").toString()); 
				hmpAllInp.put("^pname^", mapRowAnsksp.get("strProject").toString()); 
				hmpAllInp.put("^uname^", mapRowAnsksp.get("strUserName").toString()); 
				hmpAllInp.put("^pass^", mapRowAnsksp.get("strKsPassword").toString()); 
				hmpAllInp.put("^authurl^", mapRowAnsksp.get("strAuthUrl").toString()); 
				hmpAllInp.put("^imgapi^", mapRowAnsksp.get("strImgapiv").toString()); 
				hmpAllInp.put("^ideapi^", mapRowAnsksp.get("strIdeenity").toString()); 
				hmpAllInp.put("^anscmmd^", mapRowAnsksp.get("strName").toString()); 
				hmpPar.put("^anscmmd^", mapRowAnsksp.get("strName").toString()); 
				hmpPar.put("^ansid^", mapRowAnsksp.get("intId").toString()); //hmpAllInp.get("^ansid^"));
				
				hmpPar.put(ProcessAttrs.strParmapKey_Inpars, hmpAllInp);
				
				booRe = true;
			}
		} catch(Exception ex) {
			booRe = false;
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
		return booRe;
	}
	
	private List<HashMap> disSetAnsKs(List<HashMap> altDataAnsiblep, List<HashMap> altDataKeystonep){
		String strFname = " disSetAnsKs : ";
		List<HashMap> lstRe = null;
		try {
			if(altDataAnsiblep!=null && altDataAnsiblep.size()>0
					&& altDataKeystonep!=null && altDataKeystonep.size()>0){
				lstRe = new ArrayList<>();
				for(HashMap mapKsRow : altDataKeystonep){
					if(mapKsRow!=null && mapKsRow.size()>0
							&& mapKsRow.containsKey("intAnsibleId") && mapKsRow.get("intAnsibleId")!=null
							&& mapKsRow.containsKey("strPassword") && mapKsRow.get("strPassword")!=null){
						String strKsAnsIds = mapKsRow.get("intAnsibleId").toString();
						String strKspass = mapKsRow.get("strPassword").toString();
						if(strKsAnsIds.trim().length()>0 && strKspass.trim().length()>0){
							for(HashMap mapAnsRow : altDataAnsiblep){
								if(mapAnsRow!=null && mapAnsRow.size()>0
										&& mapAnsRow.containsKey("intId") && mapAnsRow.get("intId")!=null){
									String strAnsIds = mapAnsRow.get("intId").toString();
									if(strAnsIds.trim().length()>0
											&& strKsAnsIds.trim().equals(strAnsIds.trim())){
										HashMap mapAll = new HashMap<>();
										mapAll = mapKsRow;
										mapAll.putAll(mapAnsRow);
										mapAll.put("strKsPassword", strKspass);
										lstRe.add(mapAll);
									}
								}
							}
						}
					}
				}
			}
		} catch(Exception ex) {
			lstRe = null;
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} 
		return lstRe;
	}
	/**
	 * 运行日志基本信息构建
	 * */
	private void disSetInfobase(){
		String strFname = " disSetInfobase : ";
		
		try {
			UUID objUuid = UUID.randomUUID();
			LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<>();
			lhpInfobase.put("cp_ids",IdsPro.disGetIds()); //DatePro.disGetStrdate4NowObjSdf001());
			lhpInfobase.put("cpcls", strCname);
			lhpInfobase.put("customer", "QzRun"); //hmpPar.get("^customerids^").toString());
			lhpInfobase.put("ansible_ids", "QzRun"); //hmpPar.get("^ansid^").toString());
			lhpInfobase.put("ansible_info", "QzRun"); //hmpPar.get("^anscmmd^").toString());
			lhpInfobase.put("cmd_tpye", "QzRun"); //hmpPar.get("^req_type^").toString());
			lhpInfobase.put("cmd_subtype", "QzRun"); //hmpPar.get("^req_subtype^").toString());
			lhpInfobase.put("cmd_request", "QzRun"); //hmpPar.get(ProcessAttrs.strParmapKey_Inpars).toString());
			lhpInfobase.put("cmd_inputdt", DatePro.disGetStrdate4NowObjSdf001());
			lhpInfobase.put("cpuuid", objUuid.toString().replaceAll("-", "")); //hmpPar.get(ProcessAttrs.strInfoKey_Cpuuid).toString());
			lhpInfobase.put("cmdrundt", DatePro.disGetStrdate4NowObjSdf001());
			lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Pro);
			
			hmpPar.put(ProcessAttrs.strParmapKey_Infobase, lhpInfobase);
			hmpPar.put("cp_ids",lhpInfobase.get("cp_ids"));
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	/**
	 * 传入参数处理
	 * */
	private void disSetParinfos(){
		String strFname = " disSetPars : ";
		List<HashMap> altDataKeystone = new ArrayList<>();
		try {
//			List<HashMap> altDataKeystone = disGetKeustone();	
//			KeystonePro objKeystonePro = new KeystonePro(this.strCname, hmpPar);
//			altDataKeystone = objKeystonePro.disGetKeustone();
			
			if(altDataKeystone!=null){

//				UUID objUuid = UUID.randomUUID();
//				HashMap<String, String> hmpAllInp = new HashMap<>();
//				hmpAllInp.put("^ansid^", "1");
				
				UUID objUuid = UUID.randomUUID();
				HashMap<String, Object> hmpAllInp = null;
//				hmpAllInp = hmpPar;
//				hmpAllInp.put("^devname^", "dev-"+objUuid.toString().replaceAll("-", ""));
//				hmpAllInp.put("^devids^", null);
//				hmpAllInp.put("^vmids^", null);
//				hmpAllInp.put("^shell_allpath^", "");
//				if(!hmpAllInp.containsKey("^vmname^")
//						|| hmpAllInp.get("^vmname^")==null
//						|| (hmpAllInp.get("^vmname^")!=null 
//							&& hmpAllInp.get("^vmname^").toString().trim().length()==0)
//						|| (hmpAllInp.get("^vmname^")!=null 
//							&& hmpAllInp.get("^vmname^").toString().trim().length()>0) 
//							&& "None".equals(hmpAllInp.get("^vmname^").toString())){
//					hmpAllInp.put("^vmname^", "vm-"+objUuid.toString().replaceAll("-", ""));
//				}
//				hmpPar = new HashMap<>();
				hmpAllInp = new HashMap<>();
				
				hmpPar.put(ProcessAttrs.strParmapKey_Inpars, hmpAllInp);
				hmpPar.put("^ansid^", ""); //hmpAllInp.get("^ansid^"));
				hmpPar.put("^anscmmd^", ""); //hmpAllInp.get("^anscmmd^"));
				hmpPar.put("^req_type^", ""); //hmpAllInp.get("^req_type^"));
				hmpPar.put("^req_subtype^", ""); //hmpAllInp.get("^req_subtype^"));
				hmpPar.put("^customerids^", ""); //hmpAllInp.get("^customerids^"));
				hmpPar.put("^uksids^", ""); //hmpAllInp.get("^uksids^"));
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
						+ "Getnetw_"+DatePro.disGetStrdate4NowObjSdf001() 
//						+ "_" + hmpAllInp.get("^req_type^").toString()
//						+ hmpAllInp.get("^req_subtype^").toString() );
							+ ".sh");
				hmpPar.put(ProcessAttrs.strParmapKey_Ppa_RunResLst, "");
				
			} else {
				hmpPar = null;
			}
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
	}
	
//	private List<HashMap> disGetKeustone(){
//		String strFname = "";
//		List<HashMap> altDataKeystone = null;
//		try {
//			
//			Map<String, Object> mapParKeystone = new HashMap<>(); 
////			mapParKeystone.put("ksId", "1"); 
////			mapParKeystone.put("dom", "1"); 
////			mapParKeystone.put("project", "1"); 
////			mapParKeystone.put("ideenity", "1"); 
////			mapParKeystone.put("imgapiv", "1"); 
////			mapParKeystone.put("ansibleId", "1"); 
//			String strParKeystone = JSON.toJSONString(mapParKeystone);
//			HttpClientUtil objHttpClientUtil = new HttpClientUtil();
//			String strKeystone = objHttpClientUtil.sendHttpPostJson("http://10.167.212.105:9001/pjOpStAuth/web/keystone/getKeystone", strParKeystone);
//			
//			Map<String, Object> mapResKeystone = JSON.parseObject(strKeystone, HashMap.class);
//			if(mapResKeystone!=null && mapResKeystone.size()>0
//					&& mapResKeystone.containsKey("msg") && mapResKeystone.get("msg")!=null
//					&& mapResKeystone.containsKey("data") && mapResKeystone.get("data")!=null){
//				String strMsg = mapResKeystone.get("msg")==null?null:mapResKeystone.get("msg").toString();
//				if("ok".equals(strMsg)){
//					String strDataKeystone = mapResKeystone.get("data").toString();
//					if(strDataKeystone!=null && strDataKeystone.trim().length()>0){
//						altDataKeystone = JSON.parseArray(strDataKeystone, HashMap.class);
////						Object[] subKey = altDataKeystone.get(0).keySet().toArray();
////						if(subKey!=null && subKey.length>0){
////							for(HashMap map : altDataKeystone){
////								System.out.print("Keystone ----");
////								for(Object objkey : subKey){
////									String strVal = map.get(objkey)==null? "":map.get(objkey).toString();
////									System.out.print(objkey + ":" + strVal + ", ");
////									/*
////									 * strPDom
////									 * strUDom
////									 * strProject
////									 * strUserName
////									 * strPassword
////									 * strAuthUrl
////									 * strIdeenity
////									 * strImgapiv
////									 * **intAnsibleId
////									 */
////								}
////								System.out.println("");
////							}
////						}
//					}
//				}
//			}
//		} catch(Exception ex) {
//			
//		}
//		return altDataKeystone;
//	}
//	private List<HashMap> disGetAnsible(){
//		String strFname = " disGetAnsible : ";
//		List<HashMap> altDataAnsible = null;
//		try {
//			Map<String, Object> mapParAnsible = new HashMap<>(); 
////			mapParAnsible.put("id", "1"); 
////			mapParAnsible.put("ip", "1"); 
////			mapParAnsible.put("sshKey", "1"); 
//			String strParAnsible = JSON.toJSONString(mapParAnsible);
//			HttpClientUtil objHttpClientUtil = new HttpClientUtil();
//			String strAnsible = objHttpClientUtil.sendHttpPostJson("http://10.167.212.105:9001/pjOpStAuth/web/ansible/getAnsible", strParAnsible);
//			
//			Map<String, Object> mapResAnsible = JSON.parseObject(strAnsible, HashMap.class);
//			if(mapResAnsible!=null && mapResAnsible.size()>0
//					&& mapResAnsible.containsKey("msg") && mapResAnsible.get("msg")!=null
//					&& mapResAnsible.containsKey("data") && mapResAnsible.get("data")!=null){
//				String strMsg = mapResAnsible.get("msg")==null?null:mapResAnsible.get("msg").toString();
//				if("ok".equals(strMsg)){
//					String strDataAnsible = mapResAnsible.get("data").toString();
//					if(strDataAnsible!=null && strDataAnsible.trim().length()>0){
//						altDataAnsible = JSON.parseArray(strDataAnsible, HashMap.class);
//						Object[] subKey = altDataAnsible.get(0).keySet().toArray();
////						if(subKey!=null && subKey.length>0){
////							for(HashMap map : altDataAnsible){
////								System.out.print("Ansible ----");
////								for(Object objkey : subKey){
////									String strVal = map.get(objkey)==null? "":map.get(objkey).toString();
////									System.out.print(objkey + ":" + strVal + ", ");
////									/*
////									 intId
////									 strName
////									 strIp
////									 strLoginName
////									 strPassword
////									 strSshKey
////									 */
////								}
////								System.out.println("");
////							}
////						}
//					}
//				}
//			}
//			
//		} catch(Exception ex) {
//			
//		}
//		return altDataAnsible;
//	}
	
	public void disSetPars(HashMap<String, Object> hmpParp){
		String strFname = " disRuncmdPro : ";
		this.hmpPar = hmpParp;
	}

}