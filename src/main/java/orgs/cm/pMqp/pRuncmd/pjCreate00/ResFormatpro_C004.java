package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSavepro;
import orgs.cm.pMqp.pDbpro.DbInfotablePro4Cmmd;
import orgs.cm.pMqp.pDbpro.DbproAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;

/**
 * 格式化img返回信息，结果用于request。
 * */
public class ResFormatpro_C004 {
	
	private final String strCname = ResFormatpro_C004.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private SaveInfoPro objSaveInfoPro = null;
	private ClsBaseAttrs objBa = null; 
	private HashMap<String, Object> hmpAll = null;
//	private LinkedHashMap<String, String> lhpInfobase = new LinkedHashMap<String, String>();
//	private ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();	

	private ResFormatpro_C004(){};
	
	private LinkedHashMap<String, String> lhpCol = new LinkedHashMap<>();
	private String strAnsId = "0";
	private ArrayList<LinkedHashMap<String, String>> altRes;
	public ResFormatpro_C004(HashMap<String, Object> hmpParp
			, ArrayList<LinkedHashMap<String, String>> altResp
			, String intAnsidp
			, ClsBaseAttrs objBap){
		disSetColmap();
		this.altRes = altResp;
		this.strAnsId = intAnsidp;
		this.hmpAll = hmpParp;
		this.objBa = objBap;
		if(objBa!=null && hmpAll!=null){
			objSaveInfoPro = new SaveInfoPro(strCname, objBa);
		}
	}
	
	private void disSetColmap(){
		String strFname = " disSetColmap : ";
		try {
			
//			lhpCol.put("name","strVmName"); //   ''
//			lhpCol.put("provider network","strVmIp"); //   ''
			lhpCol.put("strVmUser","strVmUser"); //   ''
			lhpCol.put("strVmPassword","strVmPassword"); //   ''
			lhpCol.put("strSshKey","strSshKey"); //   lhpCol.put("
			lhpCol.put("intNwId","intNwId"); //   ''
			lhpCol.put("intTemId","intTemId"); //   ''
			lhpCol.put("intImaId","intImaId"); //   ''
			lhpCol.put("OS-DCF:diskConfig","strDiskConfig"); //   ''
			lhpCol.put("OS-EXT-AZ:availability_zone","strAvailabilityZone"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:host","strHost"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:hostname","strHostname"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:hypervisor_hostname","strHypervisorHostname"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:instance_name","strInstanceName"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:kernel_id","strKernelId"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:launch_index","strLaunchIndex"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:ramdisk_id","strRamdiskId"); //   '' 
			lhpCol.put("OS-EXT-SRV-ATTR:reservation_id","strReservationId"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:root_device_name","strRootDeviceName"); //   ''
			lhpCol.put("OS-EXT-SRV-ATTR:user_data","strUserData"); //   ''
			lhpCol.put("OS-EXT-STS:power_state","strPowerState"); //   ''
			lhpCol.put("OS-EXT-STS:task_state","strTaskState"); //   ''
			lhpCol.put("OS-EXT-STS:vm_state","strVmState"); //   ''
			lhpCol.put("OS-SRV-USG:launched_at","strLaunchedAt"); //   ''
			lhpCol.put("OS-SRV-USG:terminated_at","strTerminatedAt"); //   ''
			lhpCol.put("accessIPv4","strAccessIPv4"); //   ''
			lhpCol.put("accessIPv6","strAccessIPv6"); //   '' 
			lhpCol.put("config_drive","strConfigDrive"); //   ''
			lhpCol.put("created","strCreated"); //   ''
			lhpCol.put("description","strDescription"); //   ''
			lhpCol.put("flavor","strFlavor"); //   ''
			lhpCol.put("id","strHostId"); //   ''
			lhpCol.put("host_status","strHostStatus"); //   ''
			lhpCol.put("image","strImage"); //   ''
			lhpCol.put("key_name","strKeyName"); //   ''
			lhpCol.put("locked","strLocked"); //   ''
			lhpCol.put("metadata","strMetadata"); //   '' 
			lhpCol.put("name","strName"); //   ''
			lhpCol.put("os-extended-volumes","strVolumesAttached"); //   ''
			lhpCol.put("progress","intProgress"); //   ''
			lhpCol.put("provider network","strProviderNetwork"); //   ''
			lhpCol.put("security_groups","strSecurityGroups"); //   ''
			lhpCol.put("status","strStatus"); //   ''
			lhpCol.put("tags","strTags"); //   ''
			lhpCol.put("tenant_id","strTenantId"); //   ''
			lhpCol.put("updated","strUpdated"); //   ''
			lhpCol.put("user_id","strUserId"); //   

		} catch(Exception ex) {
			objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);//disOutputLog(strFname, ex);
		}
	}
	
	public String disResCheck(){
		String strFname = "";
		boolean booFlg = false;
		String strRe = null;
		String strInfos = "";
		
		try {
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
							if(strInfo.indexOf("SUCCESS")>0){
								booFlg = true;
//								break;
							}
							if(booFlg && strInfo.indexOf("| ")>-1){
								String[] subInfo = strInfo.split("\\|");
								if(subInfo!=null && subInfo.length==4
										&& !"Property".equals(subInfo[1].trim())
										&& "id".equals(subInfo[1].trim())){
									strRe = "t}}}" + subInfo[2].trim();
								}
							}
						}
					}
				}
			}
			strInfos = strCname + strFname + " End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfos, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			strRe = null;
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} finally {
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
//			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}
		return strRe;
	}
	
	public LinkedHashMap<String, String> disFormatpro(){
		String strFname = " disFormatpro : ";
		boolean booSFlg = false;
		boolean booLFlg = false;
		LinkedHashMap<String, String> lmpRow = new LinkedHashMap<>();
		String strInfos = "";
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
							if(strInfo.indexOf("SUCCESS")>0){
								booSFlg = true;
								continue;
							}
							if(strInfo.indexOf("stdout_lines")>0){
								booLFlg = true;
								continue;
							}
							if(booSFlg && booLFlg
									&& strInfo.indexOf("| ")>-1){
								String[] subInfo = strInfo.split("\\|");
								if(subInfo!=null && subInfo.length==4
										&& !"Property".equals(subInfo[1].trim())){
									//20170724 strImgId,strName
									lmpRow.put(lhpCol.get(subInfo[1].trim()), subInfo[2].trim());
								}
							}
						}
					}
				}
			}
			strInfos = strCname + strFname + " End!" ;
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfos, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
		} catch(Exception ex) {
			lmpRow = null;
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);
			}
		} finally {
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
//			disSaveInfo(DbInfoSaveAttrs.strSaveFlg_Run);
		}
		return lmpRow;
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
//						logger.info(strCname + strFname + " Resf01完整存储!");
//					} else {
//						logger.info(strCname + strFname + " Resf01存储异常!");
//					}
//				}
//
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
//			}
//			strRe = strPackage;
//		} catch(Exception ex) {
//			strRe = "";
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
