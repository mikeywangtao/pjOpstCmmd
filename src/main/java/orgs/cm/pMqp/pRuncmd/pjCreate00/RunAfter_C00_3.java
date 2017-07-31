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

public class RunAfter_C00_3 extends AbsRunAfter {


	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	private final String strCname = RunAfter_C00_3.class.getName();
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
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
				
				//格式化返回
				String strAnsidf = hmpAll.get("^ansid^")==null? null:hmpAll.get("^ansid^").toString();
				if(strAnsidf!=null && strAnsidf.trim().length()>0){
					ResFormatpro_3 objResFormatpro = new ResFormatpro_3(
							(ArrayList<LinkedHashMap<String, String>>)hmpAll.get(ProcessAttrs.strInfoFlgKey_Resstd), strAnsidf);
					String strFlg = objResFormatpro.disResCheck();
					if(strFlg!=null && strFlg.trim().length()>0){
						String[] subFlg = strFlg.split("}}}");
						if(subFlg!=null && subFlg.length==2){
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "4");
							hmpAll.put("^vmids^", subFlg[1]);
							((HashMap<String, String>)hmpAll.get(ProcessAttrs.strParmapKey_Inpars)).put("^vmids^", subFlg[1]);
							strInfo = strCname + strFname + " VM创建 After ----booResCheck:true" + DatePro.disGetStrdate4NowObjSdf001();
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
							hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						} else {
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, null);
							strInfo = strCname + strFname + " VM创建 After ----disResCheck return error" + DatePro.disGetStrdate4NowObjSdf001();
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc, null);
							hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						}
					} else {
						hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, null);
						strInfo = strCname + strFname + " VM创建 After ----disResCheck return null" + DatePro.disGetStrdate4NowObjSdf001();
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
