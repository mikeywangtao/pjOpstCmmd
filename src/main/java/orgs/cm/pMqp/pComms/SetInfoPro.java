package orgs.cm.pMqp.pComms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 运行消息组装
 * */
public class SetInfoPro {

	private final String strCname = OutputLogPro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private String strCCname = "";
	private SetInfoPro(){}
	public SetInfoPro(String strCCnamep){
		this.strCCname = strCCnamep;
	}
	
	/**
	 * 默认输出消息组装过程
	 * */
	public ArrayList<LinkedHashMap<String, String>> disSetInfo_000(String strInfop
			, LinkedHashMap<String, String> lhpInfop
			, ArrayList<LinkedHashMap<String, String>> altRuncp
			, String strInfoTypepFlgp){
		String strFname = " disSetInfo000 : ";
		
		try {
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
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			this.logger.error(this.strCCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				this.logger.error(subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
			altRuncp.add(getErrmap(lhpInfop));
			return altRuncp;
		}
	}
	
	private LinkedHashMap<String, String> getErrmap(LinkedHashMap<String, String> lhpInfop){
		String strFname = " getErrmap : ";
		LinkedHashMap<String, String> lhpInfof = new LinkedHashMap<>();
		
		try {
			
			lhpInfof.put("cp_ids",IdsPro.disGetIds()); //DatePro.disGetStrdate4NowObjSdf001());
			lhpInfof.put("cpcls", strCname);
			lhpInfof.put("customer", "sys");
			lhpInfof.put("ansible_ids", "");
			lhpInfof.put("ansible_info", "");
			lhpInfof.put("cmd_tpye", "");
			lhpInfof.put("cmd_subtype", "");
			lhpInfof.put("cmd_request", "");
			lhpInfof.put("cmd_inputdt", "");
			lhpInfof.put("cpuuid", ProcessAttrs.strInfoKey_Cpuuid);
			lhpInfof.put("cmdrundt", DatePro.disGetStrdate4NowObjSdf001());
			lhpInfof.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_Pro);
			lhpInfof.put(ProcessAttrs.strInfoKey_Info, "");
			lhpInfof.put(ProcessAttrs.strInfoType_Info, "NgErr");
			lhpInfof.put(ProcessAttrs.strInfoFlg_Info, "err");
			lhpInfof.put(ProcessAttrs.strInfoSubflg_Info, "setinfo}}}catch}}}err");
			lhpInfof.put(ProcessAttrs.strInfoKey_Rundt, DatePro.disGetStrdate4NowObjSdf001());
		} catch(Exception ex) {
			long lonFlg = System.currentTimeMillis();
			this.logger.error(this.strCCname + strFname + ex + "||" + lonFlg);
			StackTraceElement[] subSte = ex.getStackTrace();
			for(int i=0; i<subSte.length; i++){
				this.logger.error(subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			}
		}
		return lhpInfof;
	}
}
