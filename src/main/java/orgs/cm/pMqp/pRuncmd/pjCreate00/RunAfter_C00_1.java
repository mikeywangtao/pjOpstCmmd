package orgs.cm.pMqp.pRuncmd.pjCreate00;

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
import orgs.cm.pMqp.pDbpro.AbsDbpro;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pHttpc.HttpClientUtil;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunAfter;

/**
 * 创建vm命令1，命令运行后的处理
 * */
public class RunAfter_C00_1 extends AbsRunAfter {


	private AbsDbpro objDbpro = null;
	private HashMap<String, Object> hmpAll;
	private final String strCname = RunAfter_C00_1.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private ClsBaseAttrs objBa = null; 
//	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
//	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	

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
				objBa.lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Aft);
				strInfo = strCname + strFname + " VM创建 After01 Start----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
//				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, altRunc);
				
				if(hmpAll.containsKey(ProcessAttrs.strInfoFlgKey_Resstd)
						&& hmpAll.get(ProcessAttrs.strInfoFlgKey_Resstd)!=null
						&& hmpAll.containsKey(ProcessAttrs.strInfoFlgKey_Reserr)
						&& hmpAll.get(ProcessAttrs.strInfoFlgKey_Reserr)==null){
					ResFormatpro_1 objResFormatpro_1 = new ResFormatpro_1(hmpAll, (ArrayList<LinkedHashMap<String, String>>)hmpAll.get(ProcessAttrs.strInfoFlgKey_Resstd), objBa);
					objResFormatpro_1.disFormatpro();
					hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, "2");
					strInfo = strCname + strFname + " VM创建 After01 ----strParmapKey_Ppa_NowRunflg OK 2" + DatePro.disGetStrdate4NowObjSdf001();
					objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_Elx + " strParmapKey_Ppa_NowRunflg OK 2 " );
					hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, objBa.altRunc);
				}
				if(hmpAll.containsKey(ProcessAttrs.strInfoFlgKey_Reserr)
						&& hmpAll.get(ProcessAttrs.strInfoFlgKey_Reserr)!=null){
					ResFormatpro_1 objResFormatpro_1 = new ResFormatpro_1(hmpAll, (ArrayList<LinkedHashMap<String, String>>)hmpAll.get(ProcessAttrs.strInfoFlgKey_Reserr), objBa);
					objResFormatpro_1.disFormatpro();
					hmpAll.put(ProcessAttrs.strParmapKey_Ppa_NowRunflg, null);
					strInfo = strCname + strFname + " VM创建 After01 ----strParmapKey_Ppa_NowRunflg Error" + DatePro.disGetStrdate4NowObjSdf001();
					objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_Elx + " strParmapKey_Ppa_NowRunflg Error " );
					hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, objBa.altRunc);
				}
			
//				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
				
				String strRes = ((String)hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunResLst));
				strRes = strRes +  "," + strCname + strFname+ " Run01after ok ----end " ;
				hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunResLst, strRes);				strInfo = strCname + strFname + " VM创建 After01 End----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
				hmpAll.put(ProcessAttrs.strParmapKey_Aftlst, objBa.altRunc);
			}
		} catch(Exception ex) {
			String strRes = ((String)hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunResLst));
			strRes = strRes +  "," + strCname + strFname+ " Run01after Ex " + ex ;
			hmpAll.put(ProcessAttrs.strParmapKey_Ppa_RunResLst, strRes);
			((ArrayList<String>)hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunResLst)).add(strCname + strFname + ex);
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);//disOutputLog(strFname, ex);
		} finally{
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
//			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}
		
		return hmpAll;
	}

//	private void disSaveInfo(String strFlgp){
//		String strFname = " disSaveInfo : ";
//		try {
//			if(strFlgp!=null && strFlgp.trim().length()>0
//					&& altRunc!=null && altRunc.size()>0){
////				for(LinkedHashMap<String, String> mapRow : altRunc){
////					System.out.println(mapRow);
////				}
//				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
//				DbInfoSavepro objDbInfoSavepro = new DbInfoSavepro(DbproAttrs.strDbflg_Cmd, disGetBusname());
//				if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
//					int intNum = objDbInfoSavepro.disSaveRuninfo(altRunc);
//					if(intNum==altRunc.size()){
//						logger.info(strCname + strFname + " After完整存储!");
//					} else {
//						logger.info(strCname + strFname + " After存储异常!");
//					}
//				}
//			}
//		} catch(Exception ex) {
//			disOutputLog(strFname, ex);
//		}
//	}
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
//	
//	private String disCreateJson(ArrayList<LinkedHashMap<String, String>> altResfp){
//		String strFname = " disCreateJson : ";
//		String strRe = null;
//		try {
//			strRe = JSON.toJSONString(altResfp);
//		} catch(Exception ex) {
//			strRe = null;
//			disOutputLog(strFname, ex);
//		}
//		return strRe;
//	}
//	
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
//				strTypef = subTypeFlg[0].trim();
//				strFlgf = subTypeFlg[1].trim();
//				strSubflgf = subTypeFlg[2].trim();
//			}
//		}
//		LinkedHashMap<String, String> lhpInfof = null;
//		String strInfo = strInfop;
//		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
//		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo.replaceAll("'", "\""));
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
