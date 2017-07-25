package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;

/**
 * 格式化img返回信息，结果用于request。
 * */
public class ResFormatpro_2 {
	
	private final String strCname = ResFormatpro_2.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);

	private ResFormatpro_2(){};
	
	private String strBaseFlg = null;
	private ArrayList<LinkedHashMap<String, String>> altRes;
	public ResFormatpro_2(ArrayList<LinkedHashMap<String, String>> altResp, String strBaseFlgp){
		this.altRes = altResp;
		this.strBaseFlg = strBaseFlgp;
	}
		
	public String disGetFlg(){
		String strFname = " disGetFlg : ";
		boolean booFlg = false;
		String strRe = null;
		try{
			if(strBaseFlg!=null && strBaseFlg.trim().length()>0
					&& altRes!=null && altRes.size()>0){
				for(LinkedHashMap<String, String> mapRow : altRes){
					if(mapRow!=null && mapRow.size()>0){
						String strInfo = mapRow.get(ProcessAttrs.strInfoKey_Info)==null?
								null : mapRow.get(ProcessAttrs.strInfoKey_Info).toString();
						if(strInfo!=null && strInfo.trim().length()>0){
							if(strInfo.indexOf("stdout_lines")>0){
								booFlg = true;
								continue;
							}
							if(booFlg && strInfo.indexOf("| ")>-1){
								String[] subInfo = strInfo.split("\\|");
								if(subInfo!=null && subInfo.length==9
										&& !"ID".equals(subInfo[1].trim())
										&& strBaseFlg.trim().equals(subInfo[3].trim())){
									strRe = subInfo[2].trim()+"}}}"+subInfo[1].trim();
									break;
								}
							}
						}
					}
				}
			}
		} catch(Exception ex) {
			strRe = null;
			disOutputLog(strFname, ex);
		}
		return strRe;
	}
	public ArrayList<LinkedHashMap<String, String>> disFormatpro(){
		String strFname = " disFormatpro : ";
		boolean booFlg = false;
		LinkedHashMap<String, String> lmpRow = new LinkedHashMap<>();
		ArrayList<LinkedHashMap<String, String>> altRe = new ArrayList<>();
		try{
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
