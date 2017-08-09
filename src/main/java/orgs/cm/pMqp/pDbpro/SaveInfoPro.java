package orgs.cm.pMqp.pDbpro;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.ProcessAttrs;

/**
 * 过程信息存储
 * */
public class SaveInfoPro {
	private final String strCname = SaveInfoPro.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private String strCCname = "";
	private ClsBaseAttrs objBa = null;
	
	private SaveInfoPro(){}
	public SaveInfoPro(String strCCnamep, ClsBaseAttrs objp) {
		objBa = objp;
		this.strCCname = strCCnamep;
	}
	
	public void disSaveInfo_Run(String strFlgp){
		String strFname = " disSaveInfo_Run : ";
		String strInfo = "";
		try {
			if(objBa.lhpInfobase!=null && objBa.lhpInfobase.size()>0){
				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_OKst);
				strInfo = strCname + strFname + " Start!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS); //disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			}
			if(strFlgp!=null && strFlgp.trim().length()>0
					&& objBa.altRunc!=null && objBa.altRunc.size()>0){
//				for(LinkedHashMap<String, String> mapRow : altRunc){
//					System.out.println(mapRow);
//				}
				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
				DbInfoSavepro objDbInfoSavepro = new DbInfoSavepro(DbproAttrs.strDbflg_Cmd, disGetBusname());
//				if(DbInfoSaveAttrs.strSaveFlg_Cp.equals(strFlgp.trim())){
//					int intNum = objDbInfoSavepro.disSaveCpinfo(objBa.altRunc);
//					if(intNum==1){
//						logger.info(strCname + strFname + " Cp完整存储!");
//					} else {
//						logger.info(strCname + strFname + " Cp存储异常!");
//					}
//				}
				if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
					int intNum = objDbInfoSavepro.disSaveRuninfo(objBa.altRunc);
					if(intNum==objBa.altRunc.size()){
						logger.info(strCname + strFname + " Run完整存储!");
					} else {
						logger.info(strCname + strFname + " Run存储异常!");
					}
				}
			}
			strInfo = strCname + strFname + " End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
		}
	}
	
	public void disSaveInfo_Cp(String strFlgp){
		String strFname = " disSaveInfo_Cp : ";
		String strInfo = "";
		try {
			if(objBa.lhpInfobase!=null && objBa.lhpInfobase.size()>0){
				objBa.lhpInfobase.put(ProcessAttrs.strInfoCType_Info, ProcessAttrs.strInfoFlgKey_OSaveruninfo);
				strInfo = strCname + strFname + " Start!" ;
				objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS); //disSetInfo(strInfo, lhpInfobase, altRunc, ProcessAttrs.strInfoFlg_PRS);
			}
			if(strFlgp!=null && strFlgp.trim().length()>0
					&& objBa.altRunc!=null && objBa.altRunc.size()>0){
//				for(LinkedHashMap<String, String> mapRow : altRunc){
//					System.out.println(mapRow);
//				}
				DbInfotablePro4Cmmd.disInfotablePro(disGetBusname());
				DbInfoSavepro objDbInfoSavepro = new DbInfoSavepro(DbproAttrs.strDbflg_Cmd, disGetBusname());
				if(DbInfoSaveAttrs.strSaveFlg_Cp.equals(strFlgp.trim())){
					int intNum = objDbInfoSavepro.disSaveCpinfo(objBa.altRunc);
					if(intNum==1){
						logger.info(strCname + strFname + " Cp完整存储!");
					} else {
						logger.info(strCname + strFname + " Cp存储异常!");
					}
				}
//				if(DbInfoSaveAttrs.strSaveFlg_Run.equals(strFlgp.trim())){
//					int intNum = objDbInfoSavepro.disSaveRuninfo(objBa.altRunc);
//					if(intNum==objBa.altRunc.size()){
//						logger.info(strCname + strFname + " Run完整存储!");
//					} else {
//						logger.info(strCname + strFname + " Run存储异常!");
//					}
//				}

			}
			strInfo = strCname + strFname + " End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
		}
	}
	private String disGetBusname(){
		String strFname = " disGetBusname : ";
		String strRe = "";
		try {
//			String strPackage = this.getClass().getPackage().getName();
			String strPackage = strCCname;
			String[] subTmp = strPackage.split("\\.");
			if(subTmp!=null && subTmp.length>1){
				strPackage = subTmp[subTmp.length-2];
			}
			if(strPackage.indexOf(".")==-1){
				strPackage = strPackage.toLowerCase();
			}
			strRe = strPackage;
		} catch(Exception ex) {
			strRe = "";
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
		}
		return strRe;
	}	
}
