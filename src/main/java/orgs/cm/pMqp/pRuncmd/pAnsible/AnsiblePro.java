package orgs.cm.pMqp.pRuncmd.pAnsible;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;

/**
 * Ansible操作
 * */
public class AnsiblePro {
	private final String strCname = AnsiblePro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private ClsBaseAttrs objBa = new ClsBaseAttrs(strCname);
	private String strCCname = "";
	private HashMap<String, Object> hmpPar = new HashMap<>();
	
	private AnsiblePro(){}
	public AnsiblePro(String strCCnamep, HashMap<String, Object> hmpParp) {
		this.strCCname = strCCnamep;
		this.hmpPar = hmpParp;
		if(hmpParp!=null && hmpParp.size()>0
				&& hmpParp.containsKey(ProcessAttrs.strParmapKey_Infobase)){
			objBa.lhpInfobase = (LinkedHashMap<String, String>)(hmpParp.get(ProcessAttrs.strParmapKey_Infobase));
		}
	}
	
	public void setHmpPar(HashMap<String, Object> hmpParp){
		this.hmpPar = hmpParp;
	}
	public HashMap<String, Object> getHmpPar(){
		return hmpPar;
	}
	
	public List<HashMap> disGetAnsible(){
		String strFname = " disGetAnsible : ";
		String strInfo = "";
		List<HashMap> altDataAnsible = null;
		String strAnsids = null;
		try {
			if(objBa.lhpInfobase!=null && objBa.lhpInfobase.size()>0){
				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_OAnsi);
				strInfo = strCname + strFname + " Start!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS); //disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			}
			
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
				String strAnsible = objHttpClientUtil.sendHttpPostJson("http://10.167.212.105:9001/pjOpStAuth/web/ansible/getAnsible", strParAnsible);
				
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
			strInfo = strCname + strFname + " End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE); 
		} catch(Exception ex) {
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
		}
		return altDataAnsible;
	}
}
