package orgs.cm.pMqp.pRuncmd.pQzGetnetw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pComms.PropertiesRemoteser;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;

public class RunAfter_Getnetw extends AbsRunAfter {

//	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	private final String strCname = RunAfter_Getnetw.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private ClsBaseAttrs objBa = null;
	
//	public void disSetAll(HashMap<String, Object> hmpAllp){
//		this.hmpAll = hmpAllp;
//	}
	public void disSetHmpall(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	public void disSetClsBaseAttrs(ClsBaseAttrs objBap){
		objBa = objBap;
//		objBa.disClear_lhpInfobase();
		objBa.disClear_altRunc();
	}
	@Override
	public HashMap<String, Object> disRunAfter() {
		String strFname = " disRunAfter : ";
		String strInfo = null;
		SaveInfoPro objSaveInfoPro = null;
		String strRemoteSer = null;
		String strReSerpoint = null;
		
//		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
//		ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
		
		try {
			if(objBa==null || hmpAll==null){
				return null;
			}
			objSaveInfoPro = new SaveInfoPro(strCname, objBa);
			
			if(hmpAll.size()>0
					&& hmpAll.containsKey("^ansid^")
					&& hmpAll.get("^ansid^")!=null){
				logger.info(strCname + strFname + "  Start!");
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, null);
				objBa.lhpInfobase.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Aft);
				strInfo = strCname + strFname + " 网络 After Start----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, objBa.altRunc);
				
				//获取远端server地址
				strRemoteSer = PropertiesRemoteser.disGetval("auth");
				strReSerpoint = PropertiesRemoteser.disGetval("authpoint");
				if(strRemoteSer!=null && strRemoteSer.trim().length()>0
						&& strReSerpoint!=null && strReSerpoint.trim().length()>0){
					//格式化返回
					String strAnsidf = hmpAll.get("^ansid^")==null? null:hmpAll.get("^ansid^").toString();
					if(strAnsidf!=null && strAnsidf.trim().length()>0){
						ResFormatpro objResFormatpro = new ResFormatpro(
								(ArrayList<LinkedHashMap<String, String>>)hmpAll.get(ProcessAttrs.strInfoFlgKey_Resstd), strAnsidf);
						ArrayList<LinkedHashMap<String, String>> altResf = objResFormatpro.disFormatpro();
						if(altResf!=null){
							hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstdf, altResf);
							//httpclient 访问
							String strReq = disCreateJson(altResf);
							if(strReq!=null && strReq.trim().length()>0){
								Map<String, Object> mapSetImg = new HashMap<>(); 
								mapSetImg.put("msg", "ok");
								mapSetImg.put("data", strReq);
								String strSetImg = JSON.toJSONString(mapSetImg);
								strInfo = strCname + strFname + " 网络 After RequestBody----" + strSetImg;
								objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
								hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, objBa.altRunc);
								HttpClientUtil objHttpClientUtil = new HttpClientUtil();
								logger.info(strInfo);
								String strSetImgres = objHttpClientUtil.sendHttpPostJson("http://"+strRemoteSer+":"+strReSerpoint+"/pjOpStAuth/web/network/saveNetwork", strSetImg);
//								String strSetImgres = objHttpClientUtil.sendHttpPostJson("http://10.167.212.105:9001/pjOpStAuth/web/images/saveImages", strSetImg);
								Map<String, Object> mapResAnsible = JSON.parseObject(strSetImgres, HashMap.class);
								strInfo = strCname + strFname + " 网络 After respones----" + mapResAnsible;
								logger.info(strInfo);
								objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
								hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, objBa.altRunc);
							}
						}
					}
				} else {
					throw new Exception(strCname + strFname + " strRemoteSer 或 strReSerpoint ==null .... ");
				}
				
				strInfo = strCname + strFname + " 网络 After End----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, objBa.altRunc);
			}
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);//disOutputLog(strFname, ex);
			}
		} finally{
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
		}
		
		return hmpAll;
	}
	
	private String disCreateJson(ArrayList<LinkedHashMap<String, String>> altResfp){
		String strFname = " disCreateJson : ";
		String strRe = null;
		try {
			strRe = JSON.toJSONString(altResfp);
		} catch(Exception ex) {
			strRe = null;
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);//disOutputLog(strFname, ex);
		}
		return strRe;
	}
	
//	private String disGetBusname(){
//		String strFname = " disGetBusname : ";
//		String strRe = "";
//		try {
//			String strPackage = this.getClass().getPackage().getName();
//			String[] subTmp = strPackage.split("\\.");
//			if(subTmp!=null && subTmp.length>1){
//				strPackage = subTmp[subTmp.length-1];
//			}
//			if(strPackage.indexOf(".")==-1){
//				strPackage = strPackage.toLowerCase();
////				DbInfotablePro4Cmmd.disInfotablePro(strPackage);
//			}
//			strRe = strPackage;
//		} catch(Exception ex) {
//			strRe = "";
//			disOutputLog(strFname, ex);
//		}
//		return strRe;
//	}
	
//	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
//			, LinkedHashMap<String, String> lhpInfop
//			, ArrayList<LinkedHashMap<String, String>> altRuncp
//			, String strInfoTypepFlgp){
//		String strTypef = "";
//		String strFlgf = "";
//		String strSubflgf = "";
//		if(strInfoTypepFlgp!=null && strInfoTypepFlgp.trim().length()>0){
//			String[] subTypeFlg = strInfoTypepFlgp.split("}}}", -1);
//			if(subTypeFlg!=null && subTypeFlg.length>=2){
//				strTypef = subTypeFlg[0];
//				strFlgf = subTypeFlg[1];
//				strSubflgf = subTypeFlg[2];
//			}
//		}
//		LinkedHashMap<String, String> lhpInfof = null;
//		String strInfo = strInfop;
//		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
//		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo);
//		lhpInfof.put(ProcessAttrs.strInfoType_Info, strTypef);
//		lhpInfof.put(ProcessAttrs.strInfoFlg_Info, strFlgf);
//		lhpInfof.put(ProcessAttrs.strInfoSubflg_Info, strSubflgf);
//		lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
//		altRuncp.add(lhpInfof);
//		return altRuncp;
//	}
//	
//	private void disOutputLog(String strFnamep, Exception exp){
//		long lonFlg = System.currentTimeMillis();
//		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
//		StackTraceElement[] subSte = exp.getStackTrace();
//		for(int i=0; i<subSte.length; i++){
//			logger.error(
//					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
//		}
//	}
}
