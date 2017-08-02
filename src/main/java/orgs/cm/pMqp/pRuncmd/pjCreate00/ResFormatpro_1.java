package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;

/**
 * 格式化img返回信息，结果用于request。
 * */
public class ResFormatpro_1 {
	
	private final String strCname = ResFormatpro_1.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private HashMap<String, Object> hmpAll = null;
	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	

	private ResFormatpro_1(){};
	
	private ArrayList<LinkedHashMap<String, String>> altRes;
	public ResFormatpro_1(HashMap<String, Object> hmpParp, ArrayList<LinkedHashMap<String, String>> altResp){
		this.altRes = altResp;
		this.hmpAll = hmpParp;
	}
	
	public ArrayList<LinkedHashMap<String, String>> disFormatpro(){
		String strFname = " disFormatpro : ";
		boolean booFlg = false;
		LinkedHashMap<String, String> lmpRow = new LinkedHashMap<>();
		ArrayList<LinkedHashMap<String, String>> altRe = new ArrayList<>();
		String strInfos = "";
		try{
			lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
			strInfos = strCname + strFname + " Start!" ;
			altRunc = disSetInfo(strInfos, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			
			if(altRes!=null && altRes.size()>0){
				for(LinkedHashMap<String, String> mapRow : altRes){
					if(mapRow.containsKey(ProcessAttrs.strInfoKey_Info)
							&& mapRow.get(ProcessAttrs.strInfoKey_Info)!=null){
						String strInfo = mapRow.get(ProcessAttrs.strInfoKey_Info)==null?
								null : mapRow.get(ProcessAttrs.strInfoKey_Info).toString();
						strInfos = strCname + strFname + "CmmdRunRes ----" + strInfo;
						altRunc = disSetInfo(strInfos, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PResx);
//						if(strInfo!=null && strInfo.trim().length()>0){
//							if(strInfo.indexOf("stdout_lines")>0){
//								booFlg = true;
//								continue;
//							}
//							if(booFlg && strInfo.indexOf("| ")>-1){
//								lmpRow.clear();
//								String[] subInfo = strInfo.split("\\|");
//								if(subInfo!=null && subInfo.length==4
//										&& !"ID".equals(subInfo[1].trim())){
//									//20170724 strImgId,strName
//									lmpRow.put("strImgId", subInfo[1].trim());
//									lmpRow.put("strName", subInfo[2].trim());
//									lmpRow.put("intAnsibleId", strAnsId);
//									altRe.add((LinkedHashMap<String, String>)lmpRow.clone());
//								}
//							}
//						}
					}
				}
			}
			strInfos = strCname + strFname + " End!" ;
			altRunc = disSetInfo(strInfos, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			altRe = null;
			disOutputLog(strFname, ex);
		} finally {
			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}
		return altRe;
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
						logger.info(strCname + strFname + " Resf01完整存储!");
					} else {
						logger.info(strCname + strFname + " Resf01存储异常!");
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
