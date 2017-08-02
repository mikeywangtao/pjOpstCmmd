package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunFinally;

public class RunFinally_Create00 extends AbsRunFinally{

	private final String strCname = RunFinally_Create00.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	
	private HashMap<String, Object> hmpAll;
	public void disSetAll(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	
	public HashMap<String, Object> disRunFinally() {
		String strFname = " disRunFinally : ";
		String strInfo = "";
		
		String strMsgs = "";
		String strCpids = "";
		String strVmIp = "";
		String strVmName = "";
		String cutmids = "";
		String strFinallydt = DatePro.disGetStrdate4NowObjSdf001();
		try {
			lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
			lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Finly);
			strInfo = strCname + strFname + " VM创建 Finally Start----" + DatePro.disGetStrdate4NowObjSdf001();
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			
			if(hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg) 
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
					&& "end".equals(hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString().trim())){
				strInfo = strCname + strFname + " VM创建 Finally Ok----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRx + " Finally Ok ");
				
				strMsgs =  hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunResLst).toString();
				strCpids = hmpAll.get("cp_ids").toString();
				strVmIp = hmpAll.get("strVmIp").toString();
				strVmName = hmpAll.get("strVmName").toString();
				cutmids = hmpAll.get("^customerids^").toString();
				HashMap<String, String> mapReq = new HashMap<>();
				mapReq.put("msgs", strMsgs);
				mapReq.put("finallydt", strFinallydt);
				mapReq.put("state", "ok");
				mapReq.put("cpids", strCpids);
				mapReq.put("vmname", strVmName);
				mapReq.put("vmip", strVmIp);
				mapReq.put("cutmids", cutmids);

				String strSetImg = "";
				strSetImg = JSON.toJSONString(mapReq);
//				strSetImg = "{'msgs':'"+strMsgs+"', 'finallydt':'"+strFinallydt+"', 'state':'ok', 'cpids':'"+strCpids+"'}";
				strInfo = strCname + strFname + " VM创建 Finally Ok request----" + strSetImg;
				altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PAx + " request ");
				HttpClientUtil objHttpClientUtil = new HttpClientUtil();
				String strSetImgres = objHttpClientUtil.sendHttpPostJson("http://10.167.212.101:8000/vm/info/", strSetImg);
				strInfo = strCname + strFname + " VM创建 Finally ----mapRes---- " + strSetImgres;
				
			} else {
				lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
				lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Finly);
				strInfo = strCname + strFname + " VM创建 Finally Ng----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRx + " Finally Ng ");
				
				cutmids = hmpAll.get("^customerids^").toString();
				strMsgs =  hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunResLst).toString();
				
				HashMap<String, String> mapReq = new HashMap<>();
				mapReq.put("msgs", strMsgs);
				mapReq.put("finallydt", strFinallydt);
				mapReq.put("state", "ng");
				mapReq.put("cpids", "");
				mapReq.put("vmname", "");
				mapReq.put("vmip", "");
				mapReq.put("cutmids", cutmids);
				
				String strSetImg = "";
				strSetImg = JSON.toJSONString(mapReq);
//				strSetImg = "{'msgs':'"+strMsgs+"', 'finallydt':'"+strFinallydt+"', 'state':'ng', 'cpids':'"+strCpids+"'}";
				strInfo = strCname + strFname + " VM创建 Finally Ng request----" + strSetImg;
				altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PAx + " request ");
				HttpClientUtil objHttpClientUtil = new HttpClientUtil();
				String strSetImgres = objHttpClientUtil.sendHttpPostJson("http://10.167.212.101:8000/vm/info/", strSetImg);
				strInfo = strCname + strFname + " VM创建 Finally ----mapRes---- " + strSetImgres;
				
			}
			strInfo = strCname + strFname + " VM创建 Finally End----" + DatePro.disGetStrdate4NowObjSdf001();
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		} finally{
			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}
		return hmpAll;
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
				if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
					int intNum = objDbInfoSavepro.disSaveRuninfo(altRunc);
					if(intNum==altRunc.size()){
						logger.info(strCname + strFname + " Finally完整存储!");
					} else {
						logger.info(strCname + strFname + " Finally存储异常!");
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
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}
}
