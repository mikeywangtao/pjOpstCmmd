package orgs.cm.pMqp.pDbpro;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDblst.DbContManage;

public class DbInfoSavepro {

	private static final String strCname = DbInfotablePro4Cmmd.class.getName();
	private static final Logger logger = LogManager.getLogger(strCname);
	
	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	
	
	private Statement objStmt = null;
	private String strBusname = null;
	private String strYear = null;
	private String strWeeknum = null;
	
	private DbInfoSavepro(){}
	
	public DbInfoSavepro(String strDbflgp){
		disGetStmt(DbproAttrs.strDbflg_Cmd);
	}
	
	private void disGetStmt(String strDbFlgp){
		objStmt = DbContManage.getDbstmt(strDbFlgp);
	}
	
	public boolean disSaveinfo(){
		String strFname = " disSaveinfo : ";
		boolean booRe = false;
		try {
			
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}
		return booRe;
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
		String strInfo = "";
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		strInfo = strCname + strFnamep + exp + "||" + lonFlg ;
		altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_EEx + " P00000");
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
			strInfo = subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg ;
			altRunc = disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_ETx + " --" + i);
		}
	}
}
