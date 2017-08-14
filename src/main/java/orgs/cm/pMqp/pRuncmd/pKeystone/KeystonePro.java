package orgs.cm.pMqp.pRuncmd.pKeystone;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.PropertiesRemoteser;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;

/**
 * Keystone操作
 * */
public class KeystonePro {
	private final String strCname = KeystonePro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private ClsBaseAttrs objBa = new ClsBaseAttrs(strCname);
	
	private String strCCname = "";
	private KeystonePro(){}
	public KeystonePro(String strCCnamep, HashMap<String, Object> hmpAll){
		strCCname = strCCnamep;
		if(hmpAll!=null && hmpAll.size()>0
				&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Infobase)){
			objBa.lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
		}
	}
	
	public List<HashMap> disGetKeustone_All(){
		String strFname = " disGetKeustone_All : ";
		List<HashMap> altDataKeystone = null;
		String strInfo = "";
		String strRemoteSer = null;
		String strReSerpoint = null;
		try {
			
			if(objBa.lhpInfobase!=null && objBa.lhpInfobase.size()>0){
				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_OKst);
				strInfo = strCname + strFname + " Start!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS); //disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			}
			
			strRemoteSer = PropertiesRemoteser.disGetval("auth");
			strReSerpoint = PropertiesRemoteser.disGetval("authpoint");
			if(strRemoteSer!=null && strRemoteSer.trim().length()>0
					&& strReSerpoint!=null && strReSerpoint.trim().length()>0){
				Map<String, Object> mapParKeystone = new HashMap<>(); 
	//			mapParKeystone.put("ksId", "1"); 
	//			mapParKeystone.put("dom", "1"); 
	//			mapParKeystone.put("project", "1"); 
	//			mapParKeystone.put("ideenity", "1"); 
	//			mapParKeystone.put("imgapiv", "1"); 
	//			mapParKeystone.put("ansibleId", "1"); 
				String strParKeystone = JSON.toJSONString(mapParKeystone);
				HttpClientUtil objHttpClientUtil = new HttpClientUtil();
				String strKeystone = objHttpClientUtil.sendHttpPostJson("http://"+strRemoteSer+":"+strReSerpoint+"/pjOpStAuth/web/keystone/getKeystone", strParKeystone);
//				String strKeystone = objHttpClientUtil.sendHttpPostJson("http://10.167.212.105:9001/pjOpStAuth/web/keystone/getKeystone", strParKeystone);
				
				Map<String, Object> mapResKeystone = JSON.parseObject(strKeystone, HashMap.class);
				if(mapResKeystone!=null && mapResKeystone.size()>0
						&& mapResKeystone.containsKey("msg") && mapResKeystone.get("msg")!=null
						&& mapResKeystone.containsKey("data") && mapResKeystone.get("data")!=null){
					String strMsg = mapResKeystone.get("msg")==null?null:mapResKeystone.get("msg").toString();
					if("ok".equals(strMsg)){
						String strDataKeystone = mapResKeystone.get("data").toString();
						if(strDataKeystone!=null && strDataKeystone.trim().length()>0){
							altDataKeystone = JSON.parseArray(strDataKeystone, HashMap.class);
						}
					}
				}
			} else {
				throw new Exception(strCname + strFname + " strRemoteSer 或 strReSerpoint ==null .... ");
			}
			strInfo = strCname + strFname + " End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			altDataKeystone = null;
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		}
		return altDataKeystone;
	}
	
//	public List<HashMap> disGetKeustone(){
//		String strFname = "";
//		List<HashMap> altDataKeystone = null;
//		String strInfo = "";
//		try {
//			
//			if(objBa.lhpInfobase!=null && objBa.lhpInfobase.size()>0){
//				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_OKst);
//				strInfo = strCname + strFname + " Start!" ;
//				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS); //disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
//			}
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
//			strInfo = strCname + strFname + " End!" ;
//			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
//		} catch(Exception ex) {
//			if(objBa!=null && objBa.objOutputLogPro!=null){
//				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
//			}
//		}
//		return altDataKeystone;
//	}
}
