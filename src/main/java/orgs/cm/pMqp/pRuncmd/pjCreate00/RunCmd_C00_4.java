package orgs.cm.pMqp.pRuncmd.pjCreate00;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.CmdStreamGobbler;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;
import orgs.cm.pMqp.pRuncmd.pQzGetimg.RunCmd_Getimg;

public class RunCmd_C00_4 extends AbsRunCmd {

	private HashMap<String, Object> hmpAll;
	
	private final String strCname = RunCmd_C00_4.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	public void setRuncres(String strflgp, ArrayList<LinkedHashMap<String, String>> altRunc){
		if(strflgp!=null && strflgp.trim().length()>0){
			if("ERR".equals(strflgp)){
				hmpAll.put(ProcessAttrs.strInfoFlgKey_Reserr, altRunc);
			}
			if("STD".equals(strflgp)){
				hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstd, altRunc);
			}
		}
	}
	
	public void setStrThrflg(String strThrflgp){
		this.strThrflg = strThrflgp;
	}
	public HashMap<String, Object> disRunCmd() {
		disRuncmd();
		return hmpAll;
	}
	public void disSetAll(HashMap<String, Object> hmpAllp){
		this.hmpAll = hmpAllp;
	}
	private void disRuncmd(){
		String strFname = " disRuncmd : ";
		long lonBasrDt = new Date().getTime();
		Process process = null;
		CmdStreamGobbler errorGobbler = null;
		CmdStreamGobbler outputGobbler = null;
		String strInfo = "";
		String strstrCpuuid = null;
		
		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
		ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();		
		try {
			logger.info(strCname + strFname + "  Start!");
			hmpAll.put(ProcessAttrs.strParmapKey_Runlst, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Reserr, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstd, null);
			lhpInfo.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Runc);

			if(hmpAll!=null && hmpAll.containsKey(ProcessAttrs.strInfoKey_Cpuuid)){
				strstrCpuuid = hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid)==null?
						null:hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid).toString();
			}
			if(strstrCpuuid==null || (strstrCpuuid!=null && strstrCpuuid.trim().length()==0)){
				strInfo = strCname + strFname + " CpUuid 异常!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			if(hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)!=null
					&& !("t".equals(hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg).toString()))
					){
				strInfo = strCname + strFname + " Shell File create 失败!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			String strFileroot = null;
			String strFilename = null;
			String strPostfix = null;
			String strNowRunflg = null;
			String StrCommand = null;
			String strAnsCmmd = null;
			if(hmpAll.containsKey("^anscmmd^")
					&& hmpAll.get("^anscmmd^")!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFileroot)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFileroot)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFilename)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilename)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowPostfix)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowPostfix)!=null
					&& hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_NowRunflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg)!=null
					){
				strNowRunflg = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowRunflg).toString();
				strPostfix = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_NowPostfix).toString();
				strFileroot = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFileroot).toString();
				strFilename = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilename).toString(); 
				StrCommand = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd).toString(); 
				StrCommand = StrCommand.split(",")[Integer.parseInt(strNowRunflg)-1];
				strAnsCmmd = hmpAll.get("^anscmmd^").toString(); 
			}
			if(StrCommand!=null && StrCommand.trim().length()>0 && StrCommand.indexOf(",")==-1
					&& strFileroot!=null && strFileroot.trim().length()>0
					&& strFilename!=null && strFilename.trim().length()>0){
				StrCommand = StrCommand.replaceAll("\\^anscmmd\\^", strAnsCmmd);
				StrCommand = StrCommand.replaceAll("\\^shell_allpath\\^", strFileroot+strFilename+strPostfix);
			} else {
				strInfo = strCname + strFname + " Shell cmmd 构建失败!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			/* ------------------------------------------------------------------------------- */
//			String StrCommand = "ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u root "; //查看镜像
/* 
创建VM STD line: 10.167.212.1 | SUCCESS => {
创建VM STD line:     "changed": true, 
创建VM STD line:     "rc": 0, 
创建VM STD line:     "stderr": "", 
创建VM STD line:     "stdout": "+--------------------------------------+---------------------------------------------------------------------------------+\r\n| Property                             | Value                                                                           |\r\n+--------------------------------------+---------------------------------------------------------------------------------+\r\n| OS-DCF:diskConfig                    | MANUAL                                                                          |\r\n| OS-EXT-AZ:availability_zone          | nova                                                                            |\r\n| OS-EXT-SRV-ATTR:host                 | test-compute1                                                                   |\r\n| OS-EXT-SRV-ATTR:hostname             | vm-60bb22776fe84fe6a8aadbfe4158b593                                             |\r\n| OS-EXT-SRV-ATTR:hypervisor_hostname  | test-compute1                                                                   |\r\n| OS-EXT-SRV-ATTR:instance_name        | instance-00000030                                                               |\r\n| OS-EXT-SRV-ATTR:kernel_id            |                                                                                 |\r\n| OS-EXT-SRV-ATTR:launch_index         | 0                                                                               |\r\n| OS-EXT-SRV-ATTR:ramdisk_id           |                                                                                 |\r\n| OS-EXT-SRV-ATTR:reservation_id       | r-dertwx0p                                                                      |\r\n| OS-EXT-SRV-ATTR:root_device_name     | /dev/vda                                                                        |\r\n| OS-EXT-SRV-ATTR:user_data            | -                                                                               |\r\n| OS-EXT-STS:power_state               | 1                                                                               |\r\n| OS-EXT-STS:task_state                | -                                                                               |\r\n| OS-EXT-STS:vm_state                  | active                                                                          |\r\n| OS-SRV-USG:launched_at               | 2017-07-26T03:37:37.000000                                                      |\r\n| OS-SRV-USG:terminated_at             | -                                                                               |\r\n| accessIPv4                           |                                                                                 |\r\n| accessIPv6                           |                                                                                 |\r\n| config_drive                         |                                                                                 |\r\n| created                              | 2017-07-26T03:37:22Z                                                            |\r\n| description                          | -                                                                               |\r\n| flavor                               | m1.nano (0)                                                                     |\r\n| hostId                               | 4027383ec49c28b491306e42f5a804fa2215ef722fb70bf8dca16bd1                        |\r\n| host_status                          | UP                                                                              |\r\n| id                                   | b4aeefba-bcca-468e-b92e-90f4494bf209                                            |\r\n| image                                | cirros (20022a68-bc87-462d-ba6c-af6570ba839e)                                   |\r\n| key_name                             | -                                                                               |\r\n| locked                               | False                                                                           |\r\n| metadata                             | {}                                                                              |\r\n| name                                 | vm-60bb22776fe84fe6a8aadbfe4158b593                                             |\r\n| os-extended-volumes:volumes_attached | [{\"id\": \"88d1340a-4154-43ce-a1e8-7a7209fc2a3a\", \"delete_on_termination\": true}] |\r\n| progress                             | 0                                                                               |\r\n| provider network                     | 10.167.211.58                                                                   |\r\n| security_groups                      | default                                                                         |\r\n| status                               | ACTIVE                                                                          |\r\n| tags                                 | []                                                                              |\r\n| tenant_id                            | 0f50d3fbc4f64fb6b0e892d56ff47ced                                                |\r\n| updated                              | 2017-07-26T03:37:30Z                                                            |\r\n| user_id                              | fdb93da2369e494ea091004e9253013f                                                |\r\n+--------------------------------------+---------------------------------------------------------------------------------+\r\n", 
创建VM STD line:     "stdout_lines": [
创建VM STD line:         "+--------------------------------------+---------------------------------------------------------------------------------+", 
创建VM STD line:         "| Property                             | Value                                                                           |", 
创建VM STD line:         "+--------------------------------------+---------------------------------------------------------------------------------+", 
创建VM STD line:         "| OS-DCF:diskConfig                    | MANUAL                                                                          |", 
创建VM STD line:         "| OS-EXT-AZ:availability_zone          | nova                                                                            |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:host                 | test-compute1                                                                   |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:hostname             | vm-60bb22776fe84fe6a8aadbfe4158b593                                             |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:hypervisor_hostname  | test-compute1                                                                   |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:instance_name        | instance-00000030                                                               |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:kernel_id            |                                                                                 |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:launch_index         | 0                                                                               |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:ramdisk_id           |                                                                                 |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:reservation_id       | r-dertwx0p                                                                      |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:root_device_name     | /dev/vda                                                                        |", 
创建VM STD line:         "| OS-EXT-SRV-ATTR:user_data            | -                                                                               |", 
创建VM STD line:         "| OS-EXT-STS:power_state               | 1                                                                               |", 
创建VM STD line:         "| OS-EXT-STS:task_state                | -                                                                               |", 
创建VM STD line:         "| OS-EXT-STS:vm_state                  | active                                                                          |", 
创建VM STD line:         "| OS-SRV-USG:launched_at               | 2017-07-26T03:37:37.000000                                                      |", 
创建VM STD line:         "| OS-SRV-USG:terminated_at             | -                                                                               |", 
创建VM STD line:         "| accessIPv4                           |                                                                                 |", 
创建VM STD line:         "| accessIPv6                           |                                                                                 |", 
创建VM STD line:         "| config_drive                         |                                                                                 |", 
创建VM STD line:         "| created                              | 2017-07-26T03:37:22Z                                                            |", 
创建VM STD line:         "| description                          | -                                                                               |", 
创建VM STD line:         "| flavor                               | m1.nano (0)                                                                     |", 
创建VM STD line:         "| hostId                               | 4027383ec49c28b491306e42f5a804fa2215ef722fb70bf8dca16bd1                        |", 
创建VM STD line:         "| host_status                          | UP                                                                              |", 
创建VM STD line:         "| id                                   | b4aeefba-bcca-468e-b92e-90f4494bf209                                            |", 
创建VM STD line:         "| image                                | cirros (20022a68-bc87-462d-ba6c-af6570ba839e)                                   |", 
创建VM STD line:         "| key_name                             | -                                                                               |", 
创建VM STD line:         "| locked                               | False                                                                           |", 
创建VM STD line:         "| metadata                             | {}                                                                              |", 
创建VM STD line:         "| name                                 | vm-60bb22776fe84fe6a8aadbfe4158b593                                             |", 
创建VM STD line:         "| os-extended-volumes:volumes_attached | [{\"id\": \"88d1340a-4154-43ce-a1e8-7a7209fc2a3a\", \"delete_on_termination\": true}] |", 
创建VM STD line:         "| progress                             | 0                                                                               |", 
创建VM STD line:         "| provider network                     | 10.167.211.58                                                                   |", 
创建VM STD line:         "| security_groups                      | default                                                                         |", 
创建VM STD line:         "| status                               | ACTIVE                                                                          |", 
创建VM STD line:         "| tags                                 | []                                                                              |", 
创建VM STD line:         "| tenant_id                            | 0f50d3fbc4f64fb6b0e892d56ff47ced                                                |", 
创建VM STD line:         "| updated                              | 2017-07-26T03:37:30Z                                                            |", 
创建VM STD line:         "| user_id                              | fdb93da2369e494ea091004e9253013f                                                |", 
创建VM STD line:         "+--------------------------------------+---------------------------------------------------------------------------------+"
创建VM STD line:     ]
创建VM STD line: }
*/
			
//			StrCommand = StrCommand.substring(0, StrCommand.length()-1);
			strInfo = strCname + strFname + " 创建VM Start----" + DatePro.disGetStrdate4NowObjSdf001();
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
			strInfo = strCname + strFname + " 创建VM Cmmd----" + StrCommand;
			altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
			logger.info(strInfo);
			logger.info(strInfo);
			
			process = Runtime.getRuntime().exec(StrCommand);

			errorGobbler = new CmdStreamGobbler(process.getErrorStream(), StrCommand, "创建VM ERR", strstrCpuuid, this);
			outputGobbler = new CmdStreamGobbler(process.getInputStream(), StrCommand, "创建VM STD", strstrCpuuid, this);

			if(errorGobbler!=null && outputGobbler!=null){
				errorGobbler.start();
				while (!errorGobbler.isReady()) {
					Thread.sleep(100);
				}
				outputGobbler.start();
				while (!outputGobbler.isReady()) {
					Thread.sleep(50);
				}

				while(super.strThrflg!=null){
					if((new Date().getTime())-lonBasrDt<=15000){
						Thread.sleep(1010);
						if(super.strThrflg.equals("ERR")){
							strInfo = strCname + strFname + " ERR 正常完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							errorGobbler = null;
							super.strThrflg = "";
						} 
						if(super.strThrflg.equals("STD")){
							strInfo = strCname + strFname + " SDT 正常完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							outputGobbler = null;
							super.strThrflg = "";
						}
						if(outputGobbler==null && errorGobbler==null){
							strInfo = strCname + strFname + " ER SDT 监听完成！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							super.strThrflg = null;
						}
					} else {
						if(outputGobbler!=null){
							strInfo = strCname + strFname + " SDT 超时！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							outputGobbler = null;
						}
						if(errorGobbler!=null){
							strInfo = strCname + strFname + " ERR 超时！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
							errorGobbler = null;
						}
						break;
					}
				}
				super.strThrflg = null;
				strInfo = strCname + strFname + " 查看镜像 End----" + DatePro.disGetStrdate4NowObjSdf001();
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				hmpAll.put(ProcessAttrs.strParmapKey_Runlst, altRunc);
				for(int i=0; i<altRunc.size(); i++){
					System.out.println(altRunc.get(i));
				}
				System.out.println(hmpAll);
			}
		} catch(Exception ex) {
			disOutputLog(strFname, ex);
		}  finally{
			process = null;
		}

	}
	
	private ArrayList<LinkedHashMap<String, String>> disSetInfo(String strInfop
			, LinkedHashMap<String, String> lhpInfop
			, ArrayList<LinkedHashMap<String, String>> altRuncp){
		LinkedHashMap<String, String> lhpInfof = null;
		String strInfo = strInfop;
		lhpInfof = (LinkedHashMap<String, String>)lhpInfop.clone();
		lhpInfof.put(ProcessAttrs.strInfoKey_Info, strInfo);
		lhpInfof.put(ProcessAttrs.strInfoSubtype_Info, "info");
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
