package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;

/**
 * 格式化img返回信息，结果用于request。
 * */
public class ResFormatpro {
	
	private final String strCname = ResFormatpro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);

	private ResFormatpro(){};
	
	private SaveInfoPro objSaveInfoPro = null;
	private ClsBaseAttrs objBa = null; 
	private HashMap<String, Object> hmpAll = null;
	
	private String strAnsId = "0";
	private ArrayList<LinkedHashMap<String, String>> altRes;
	public ResFormatpro(
			ArrayList<LinkedHashMap<String, String>> altResp
			, String intAnsidp
			, HashMap<String, Object> hmpParp
			, ClsBaseAttrs objBap){
		this.altRes = altResp;
		this.strAnsId = intAnsidp;
		this.hmpAll = hmpParp;
		this.objBa = objBap;
		if(objBa!=null && hmpAll!=null){
			objSaveInfoPro = new SaveInfoPro(strCname, objBa);
		}
	}
	
	
	
	public ArrayList<LinkedHashMap<String, String>> disFormatpro(){
		String strFname = " disFormatpro : ";
		boolean booFlg = false;
		String strInfos = "";
		LinkedHashMap<String, String> lmpRow = new LinkedHashMap<>();
		ArrayList<LinkedHashMap<String, String>> altRe = new ArrayList<>();
		try{
			objBa.lhpInfobase = (LinkedHashMap<String, String>)(hmpAll.get(ProcessAttrs.strParmapKey_Infobase));
			strInfos = strCname + strFname + " Start!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfos, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS);
			
			if(altRes!=null && altRes.size()>0){
				for(LinkedHashMap<String, String> mapRow : altRes){
					if(mapRow.containsKey(ProcessAttrs.strInfoKey_Info)
							&& mapRow.get(ProcessAttrs.strInfoKey_Info)!=null){
						String strInfo = mapRow.get(ProcessAttrs.strInfoKey_Info)==null?
								null : mapRow.get(ProcessAttrs.strInfoKey_Info).toString();
						strInfos = strCname + strFname + "CmmdRunRes ----" + strInfo;
						objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfos, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PResx);
						
						if(strInfo!=null && strInfo.trim().length()>0){
							if(strInfo.indexOf("stdout_lines")>0){
								booFlg = true;
								continue;
							}
							if(booFlg && strInfo.indexOf("| ")>-1){
								lmpRow.clear();
								String[] subInfo = strInfo.split("\\|");
								if(subInfo!=null && subInfo.length==4
										&& !"ID".equals(subInfo[1].trim())){
									//20170724 strImgId,strName
									lmpRow.put("strImgId", subInfo[1].trim());
									lmpRow.put("strName", subInfo[2].trim());
									lmpRow.put("intAnsibleId", strAnsId);
									altRe.add((LinkedHashMap<String, String>)lmpRow.clone());
								}
							}
						}
					}
				}
			}
			
			strInfos = strCname + strFname + " End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfos, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			altRe = null;
			disOutputLog(strFname, ex);
		}
		return altRe;
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
