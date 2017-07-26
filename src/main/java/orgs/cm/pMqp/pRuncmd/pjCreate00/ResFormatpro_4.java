package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ProcessAttrs;

/**
 * 格式化img返回信息，结果用于request。
 * */
public class ResFormatpro_4 {
	
	private final String strCname = ResFormatpro_4.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);

	private ResFormatpro_4(){};
	
	private LinkedHashMap<String, String> lhpCol = new LinkedHashMap<>();
	private String strAnsId = "0";
	private ArrayList<LinkedHashMap<String, String>> altRes;
	public ResFormatpro_4(ArrayList<LinkedHashMap<String, String>> altResp, String intAnsidp){
		disSetColmap();
		this.altRes = altResp;
		this.strAnsId = intAnsidp;
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
			disOutputLog(strFname, ex);
		}
	}
	
	public String disResCheck(){
		String strFname = "";
		boolean booFlg = false;
		String strRe = null;
		try {
			if(altRes!=null && altRes.size()>0){
				for(LinkedHashMap<String, String> mapRow : altRes){
					if(mapRow.containsKey(ProcessAttrs.strInfoKey_Info)
							&& mapRow.get(ProcessAttrs.strInfoKey_Info)!=null){
						String strInfo = mapRow.get(ProcessAttrs.strInfoKey_Info)==null?
								null : mapRow.get(ProcessAttrs.strInfoKey_Info).toString();
						if(strInfo!=null && strInfo.trim().length()>0){
							if(strInfo.indexOf("SUCCESS")>0){
								booFlg = true;
								break;
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
		} catch(Exception ex) {
			strRe = null;
			disOutputLog(strFname, ex);
		}
		return strRe;
	}
	
	public LinkedHashMap<String, String> disFormatpro(){
		String strFname = " disFormatpro : ";
		boolean booSFlg = false;
		boolean booLFlg = false;
		LinkedHashMap<String, String> lmpRow = new LinkedHashMap<>();
		try{
			if(altRes!=null && altRes.size()>0){
				for(LinkedHashMap<String, String> mapRow : altRes){
					if(mapRow.containsKey(ProcessAttrs.strInfoKey_Info)
							&& mapRow.get(ProcessAttrs.strInfoKey_Info)!=null){
						String strInfo = mapRow.get(ProcessAttrs.strInfoKey_Info)==null?
								null : mapRow.get(ProcessAttrs.strInfoKey_Info).toString();
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
		} catch(Exception ex) {
			lmpRow = null;
			disOutputLog(strFname, ex);
		}
		return lmpRow;
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
