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

public class RunAfter_C00_1 extends AbsRunAfter {


	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	private final String strCname = RunAfter_C00_1.class.getName();
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
				strInfo = strCname + strFname + " 镜像 After Start----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
				
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
							strInfo = strCname + strFname + " 镜像 After RequestBody----" + strSetImg;
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
							String strSetImgres = HttpClientUtil.sendHttpPostJson("http://10.167.212.104:8080/pjOpStAuth/web/images/saveVm", strSetImg);
							Map<String, Object> mapResAnsible = JSON.parseObject(strSetImgres, HashMap.class);
							strInfo = strCname + strFname + " 镜像 After respones----" + mapResAnsible;
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						}
					}
				}


				
				String strPackage = this.getClass().getPackage().getName();
				String[] subTmp = strPackage.split("\\.");
				if(subTmp!=null && subTmp.length>1){
					strPackage = subTmp[subTmp.length-1];
				}
				if(strPackage.indexOf(".")==-1){
					strPackage = strPackage.toLowerCase();
					DbInfotablePro4Cmmd.disInfotablePro(strPackage);
				}
				
				strInfo = strCname + strFname + " 镜像 After End----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunNext, "next");
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
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
			disOutputLog(strFname, ex);
		}
		return strRe;
	}
	
	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
			, LinkedHashMap<String, String> lhpInfop
			, ArrayList<LinkedHashMap<String, String>> altRuncp){
		LinkedHashMap<String, String> lhpInfof = null;
		String strInfo = strInfop;
		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo);
		lhpInfof.put(ProcessAttrs.strInfoSubtype_Info, "info");
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
