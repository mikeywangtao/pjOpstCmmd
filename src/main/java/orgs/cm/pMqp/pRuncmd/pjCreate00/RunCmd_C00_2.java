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

public class RunCmd_C00_2 extends AbsRunCmd {

	private HashMap<String, Object> hmpAll;
	
	private final String strCname = RunCmd_C00_2.class.getName();
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
					){
					strFileroot = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFileroot).toString();
					strFilename = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilename).toString(); 
					StrCommand = hmpAll.get(ProcessAttrs.strParmapKey_Ppa_RunShCmmd).toString(); 
					strAnsCmmd = hmpAll.get("^anscmmd^").toString(); 
			}
			if(strFileroot!=null && strFileroot.trim().length()>0
					&& strFilename!=null && strFilename.trim().length()>0){
				StrCommand = StrCommand.replaceAll("\\^anscmmd\\^", strAnsCmmd);
				StrCommand = StrCommand.replaceAll("\\^shell_allpath\\^", strFileroot+strFilename);
			} else {
				strInfo = strCname + strFname + " Shell cmmd 构建失败!" ;
				altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
				return;
			}
			/* ------------------------------------------------------------------------------- */
//			String StrCommand = "ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u root "; //查看镜像
/* 11:17:09.345 [http-bio-8080-exec-10] INFO  orgs.cm.tst.model.RunCmmd002 - Run Cmmd ----> ansible openstack -m script -a  '/home/heaven/shtst001.sh' -u rootSTD line: 10.167.212.1 | SUCCESS => {
STD line:     "changed": true, 
STD line:     "rc": 0, 
STD line:     "stderr": "", 
STD line:     "stdout": "+--------------------------------------+------------+\r\n| ID                                   | Name       |\r\n+--------------------------------------+------------+\r\n| 4c367d93-fbe5-4c58-ac85-f7aab0310740 | centos6    |\r\n| 4c3b9963-bc32-4c38-a592-309e2da5722e | centos6.5  |\r\n| 20022a68-bc87-462d-ba6c-af6570ba839e | cirros     |\r\n| a7ff7cec-f187-4439-969e-a696020fb6a6 | fedora25   |\r\n| a1f55a3b-fd1c-4512-bbcf-15f4a231371c | ubuntu1404 |\r\n+--------------------------------------+------------+\r\n", 
STD line:     "stdout_lines": [
STD line:         "+--------------------------------------+------------+", 
STD line:         "| ID                                   | Name       |", 
STD line:         "+--------------------------------------+------------+", 
STD line:         "| 4c367d93-fbe5-4c58-ac85-f7aab0310740 | centos6    |", 
STD line:         "| 4c3b9963-bc32-4c38-a592-309e2da5722e | centos6.5  |", 
STD line:         "| 20022a68-bc87-462d-ba6c-af6570ba839e | cirros     |", 
STD line:         "| a7ff7cec-f187-4439-969e-a696020fb6a6 | fedora25   |", 
STD line:         "| a1f55a3b-fd1c-4512-bbcf-15f4a231371c | ubuntu1404 |", 
STD line:         "+--------------------------------------+------------+"
STD line:     ]
STD line: } */
			
			SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");
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
//							outputGobbler.setStop();
							outputGobbler = null;
						}
						if(errorGobbler!=null){
							strInfo = strCname + strFname + " ERR 超时！";
							altRunc = disSetInfo(strInfo, lhpInfo, altRunc);
//							errorGobbler.setStop();
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
