package orgs.cm.pMqp.pRuncmd.pQzGetvmste;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;

/**
 * 格式化img返回信息，结果用于request。
 * */
public class ResFormatpro {
	
	private final String strCname = ResFormatpro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);

	private ResFormatpro(){};
	
	private String strAnsId = "0";
	private ArrayList<LinkedHashMap<String, String>> altRes;
	public ResFormatpro(ArrayList<LinkedHashMap<String, String>> altResp, String intAnsidp){
		this.altRes = altResp;
		this.strAnsId = intAnsidp;
	}
	
	
	
	public ArrayList<LinkedHashMap<String, String>> disFormatpro(){
		String strFname = " disFormatpro : ";
		boolean booFlg = false;
		LinkedHashMap<String, String> lmpRow = new LinkedHashMap<>();
		ArrayList<LinkedHashMap<String, String>> altRe = new ArrayList<>();
		try{
			if(altRes!=null && altRes.size()>0){
				for(LinkedHashMap<String, String> mapRow : altRes){
					if(mapRow.containsKey(ProcessAttrs.strInfoKey_Info)
							&& mapRow.get(ProcessAttrs.strInfoKey_Info)!=null){
						String strInfo = mapRow.get(ProcessAttrs.strInfoKey_Info)==null?
								null : mapRow.get(ProcessAttrs.strInfoKey_Info).toString();
						if(strInfo!=null && strInfo.trim().length()>0){
							if(strInfo.indexOf("stdout_lines")>-1){
								booFlg = true;
								continue;
							}
							if(booFlg && strInfo.indexOf("| ")>-1){
								lmpRow.clear();
								String[] subInfo = strInfo.split("\\|");
								if(subInfo!=null && subInfo.length==8
										&& !"ID".equals(subInfo[1].trim().toUpperCase())){
									//20170724 strImgId,strName
									lmpRow.put("strVmId", subInfo[1].trim());
									lmpRow.put("strVmName", subInfo[2].trim());
									lmpRow.put("strStatus", subInfo[3].trim());
									lmpRow.put("strTaskState", subInfo[4].trim());
									lmpRow.put("strPowerState", subInfo[5].trim());
									lmpRow.put("strNetworks", subInfo[6].trim());
									lmpRow.put("intAnsibleId", strAnsId);
									altRe.add((LinkedHashMap<String, String>)lmpRow.clone());
								}
							}
						}
					}
				}
			}
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
