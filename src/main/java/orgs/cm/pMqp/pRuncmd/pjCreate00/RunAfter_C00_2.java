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

public class RunAfter_C00_2 extends AbsRunAfter {


	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	private final String strCname = RunAfter_C00_2.class.getName();
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
			if(hmpAll!=null && hmpAll.size()>0) {
				logger.info(strCname + strFname + "  Start!");
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, null);
				lhpInfo.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Aft);
				strInfo = strCname + strFname + " VM创建 After ----strParmapKey_Ppa_NowRunflg:2" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
				
				if(hmpAll.containsKey(ProcessAttrs.strInfoFlgKey_Resstd)
						&& hmpAll.get(ProcessAttrs.strInfoFlgKey_Resstd)!=null
						&& hmpAll.containsKey(ProcessAttrs.strInfoFlgKey_Reserr)
						&& hmpAll.get(ProcessAttrs.strInfoFlgKey_Reserr)==null
						&& hmpAll.containsKey("^devname^")
						&& hmpAll.get("^devname^")!=null){
					String strDevname = hmpAll.get("^devname^").toString();
					if(strDevname!=null && strDevname.trim().length()>0){
						ArrayList<LinkedHashMap<String, String>> altStd = 
								(ArrayList<LinkedHashMap<String, String>>)hmpAll.get(ProcessAttrs.strInfoFlgKey_Resstd);
						ResFormatpro_2 objResFormatpro_2 = new ResFormatpro_2(altStd, strDevname);
						String strFlg = objResFormatpro_2.disGetFlg();
//						if("available".equals(strFlg.trim())){
						if("available".equals(strFlg)){
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunLoopFlg, "f");//循环runcmd
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "3");
							strInfo = strCname + strFname + " VM创建 After ----strParmapKey_Ppa_NowRunflg:f" + DatePro.disGetStrdate4NowObjSdf001();
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						} else {
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunLoopFlg, "t");//不循环runcmd
							hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "2");
							strInfo = strCname + strFname + " VM创建 After ----strParmapKey_Ppa_NowRunflg:t" + DatePro.disGetStrdate4NowObjSdf001();
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
						}
					}
				}
				if(hmpAll.containsKey(ProcessAttrs.strInfoFlgKey_Reserr)
						&& hmpAll.get(ProcessAttrs.strInfoFlgKey_Reserr)!=null){
					hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, null);
					strInfo = strCname + strFname + " VM创建 After ----strInfoFlgKey_Reserr:Error" + DatePro.disGetStrdate4NowObjSdf001();
					altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
					hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
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
				
				strInfo = strCname + strFname + " VM创建 After End----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
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
