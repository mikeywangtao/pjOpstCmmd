package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.AbsDbpro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;

public class RunAfter_C00_4 extends AbsRunAfter {


	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	private final String strCname = RunAfter_C00_4.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void disSetAll(HashMap<String, Object> hmpAllp){
		this.hmpAll = hmpAllp;
	}
	@Override
	public HashMap<String, Object> disRunAfter() {
		String strFname = " disRunAfter : ";
		String strInfo = null;
		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
		ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
		
		try {
			if(hmpAll!=null && hmpAll.size()>0
					&& hmpAll.containsKey("^ansid^")
					&& hmpAll.get("^ansid^")!=null){
				logger.info(strCname + strFname + "  Start!");
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, null);
				lhpInfo.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Aft);
				strInfo = strCname + strFname + " VM创建 After Start----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
				
				//格式化返回
				String strAnsidf = hmpAll.get("^ansid^")==null? null:hmpAll.get("^ansid^").toString();
				if(strAnsidf!=null && strAnsidf.trim().length()>0){
					ResFormatpro_4 objResFormatpro = new ResFormatpro_4(
							(ArrayList<LinkedHashMap<String, String>>)hmpAll.get(ProcessAttrs.strInfoFlgKey_Resstd), strAnsidf);
					LinkedHashMap<String, String> mapRes = objResFormatpro.disFormatpro();
					if(mapRes!=null && mapRes.size()>0){
						
						mapRes.put("strVmName", mapRes.get("strName").toString());
						mapRes.put("strVmIp", mapRes.get("strProviderNetwork").toString());
						mapRes.put("ansid", hmpAll.get("^ansid^").toString());
						mapRes.put("customerids", hmpAll.get("^customerids^").toString());
						mapRes.put("uksids", hmpAll.get("^uksids^").toString());
						mapRes.put("imgids", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^imgids^").toString());
						mapRes.put("netwids", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^netwids^").toString());
						mapRes.put("flvids", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^flvids^").toString());
						mapRes.put("intImaId", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^intImaId^").toString());
						mapRes.put("intTemId", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^intTemId^").toString());
						mapRes.put("intNwId", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^intNwId^").toString());
						mapRes.put("strVmUser", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^strVmUser^").toString());
						mapRes.put("strVmPassword", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^strVmPassword^").toString());
						mapRes.put("strSshKey", ((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).get("^strSshKey^").toString());
						
						hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "999");
						strInfo = strCname + strFname + " VM创建 After ----mapRes---- " + mapRes.toString();
						altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
						hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						strInfo = strCname + strFname + " VM创建 After ----mapRes:true " + DatePro.disGetStrdate4NowObjSdf001();
						altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
						hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						
						String strReq = JSON.toJSONString(mapRes);
						Map<String, Object> mapSetImg = new HashMap<>(); 
						mapSetImg.put("msg", "ok");
						mapSetImg.put("data", strReq);
						String strSetImg = JSON.toJSONString(mapSetImg);
						strInfo = strCname + strFname + " VM创建 After RequestBody----" + strSetImg;
						altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
						hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						HttpClientUtil objHttpClientUtil = new HttpClientUtil();
						String strSetImgres = objHttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/vm/saveVmInfo", strSetImg);
						strInfo = strCname + strFname + " VM创建 After ----mapRes---- " + strSetImgres;
						altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
						hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						Map<String, Object> mapResAnsible = JSON.parseObject(strSetImgres, HashMap.class);
						strInfo = strCname + strFname + " VM创建 After respones----" + mapResAnsible;
						altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
						hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						logger.info(strInfo);
						

					} else {
						hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "000");
						strInfo = strCname + strFname + " VM创建 After ----mapRes:false " + DatePro.disGetStrdate4NowObjSdf001();
						altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
						hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
					}
				}
				
//				String strPackage = this.getClass().getPackage().getName();
//				String[] subTmp = strPackage.split("\\.");
//				if(subTmp!=null && subTmp.length>1){
//					strPackage = subTmp[subTmp.length-1];
//				}
//				if(strPackage.indexOf(".")==-1){
//					strPackage = strPackage.toLowerCase();
//					DbInfotablePro4Cmmd.disInfotablePro(strPackage);
//				}
				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
				
				strInfo = strCname + strFname + " 镜像 After End----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
//				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunNext, "next");
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
		
		return hmpAll;
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
//				DbInfotablePro4Cmmd.disInfotablePro(strPackage);
			}
			strRe = strPackage;
		} catch(Exception ex) {
			strRe = "";
			disOutputLog(strFname, ex);
		}
		return strRe;
	}
	
	private String disCreateJson(ArrayList<LinkedHashMap<String, String>> altResfp){
		String strFname = " disCreateJson : ";
		String strRe = null;
		try {
			strRe = JSON.toJSONString(altResfp);
		} catch(Exception ex) {
			strRe = null;
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

}
