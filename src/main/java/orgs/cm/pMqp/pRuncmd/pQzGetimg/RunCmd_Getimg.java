package orgs.cm.pMqp.pRuncmd.pQzGetimg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import orgs.cm.pMqp.pComms.ClsBaseAttrs;
import orgs.cm.pMqp.pComms.CmdStreamGobbler;
import orgs.cm.pMqp.pComms.DatePro;
import orgs.cm.pMqp.pComms.ProcessAttrs;
import orgs.cm.pMqp.pDbpro.DbInfoSaveAttrs;
import orgs.cm.pMqp.pDbpro.SaveInfoPro;
import orgs.cm.pMqp.pRuncmd.comm.AbsRunCmd;

public class RunCmd_Getimg extends AbsRunCmd {
	private HashMap<String, Object> hmpAll;
	
	private final String strCname = RunCmd_Getimg.class.getName();
	private final Logger logger = LogManager.getLogger(strCname);
	
	private ClsBaseAttrs objBa = null; 
	
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
	public void disSetHmpall(HashMap<String, Object> hmpAllp){
		hmpAll = hmpAllp;
	}
	public void disSetClsBaseAttrs(ClsBaseAttrs objBap){
		objBa = objBap;
//		objBa.disClear_lhpInfobase();
		objBa.disClear_altRunc();
	}
//	public void disSetAll(HashMap<String, Object> hmpAllp){
//		this.hmpAll = hmpAllp;
//	}
	private void disRuncmd(){
		String strFname = " disRuncmd : ";
		long lonBasrDt = new Date().getTime();
		Process process = null;
		CmdStreamGobbler errorGobbler = null;
		CmdStreamGobbler outputGobbler = null;
		String strInfo = "";
		String strstrCpuuid = null;
		
		SaveInfoPro objSaveInfoPro = null;
		
//		LinkedHashMap<String, String> lhpInfo = new LinkedHashMap<String, String>();
//		ArrayList<LinkedHashMap<String, String>> altRunc = new ArrayList<LinkedHashMap<String, String>>();		
		try {
			if(objBa==null || hmpAll==null){
				return ;
			}
			objSaveInfoPro = new SaveInfoPro(strCname, objBa);
			
			logger.info(strCname + strFname + "  Start!");
			strInfo = strCname + strFname + " 查看镜像 Runcmd01 Start----" + DatePro.disGetStrdate4NowObjSdf001();
			objBa.altRunc = objBa.objSetInfoPro.disSetInfo_000(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRS );
			
			hmpAll.put(ProcessAttrs.strParmapKey_Runlst, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Reserr, null);
			hmpAll.put(ProcessAttrs.strInfoFlgKey_Resstd, null);
			objBa.lhpInfobase.put(ProcessAttrs.strInfoType_Info, ProcessAttrs.strInfoFlgKey_Runc);

			if(hmpAll!=null 
					&& hmpAll.containsKey(ProcessAttrs.strInfoKey_Cpuuid)
					){
				strstrCpuuid = hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid)==null?
						null:hmpAll.get(ProcessAttrs.strInfoKey_Cpuuid).toString();
			}
			if(strstrCpuuid==null || (strstrCpuuid!=null && strstrCpuuid.trim().length()==0)){
				strInfo = strCname + strFname + " CpUuid 异常!" ;
				objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
				return;
			}
			if(hmpAll.containsKey(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)
					&& hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg)!=null
					&& !("t".equals(hmpAll.get(ProcessAttrs.strParmapKey_Ppa_ShFilecflg).toString()))
					){
				strInfo = strCname + strFname + " Shell File create 失败!" ;
				objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
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
				objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
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
//			StrCommand = StrCommand.replaceAll(",", "");
			SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssS");
			strInfo = strCname + strFname + " 查看镜像 Start----" + DatePro.disGetStrdate4NowObjSdf001();
			objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
			strInfo = strCname + strFname + " 查看镜像 Cmmd----" + StrCommand;
			objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
			logger.info(strInfo);
			
			process = Runtime.getRuntime().exec(StrCommand);

			errorGobbler = new CmdStreamGobbler(process.getErrorStream(), StrCommand, "查看镜像 ERR", strstrCpuuid, this);
			outputGobbler = new CmdStreamGobbler(process.getInputStream(), StrCommand, "查看镜像 STD", strstrCpuuid, this);

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
							objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
							errorGobbler = null;
							super.strThrflg = "";
						} 
						if(super.strThrflg.equals("STD")){
							strInfo = strCname + strFname + " SDT 正常完成！";
							objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
							outputGobbler = null;
							super.strThrflg = "";
						}
						if(outputGobbler==null && errorGobbler==null){
							strInfo = strCname + strFname + " ER SDT 监听完成！";
							objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
							super.strThrflg = null;
						}
					} else {
						if(outputGobbler!=null){
							strInfo = strCname + strFname + " SDT 超时！";
							objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
//							outputGobbler.setStop();
							outputGobbler = null;
						}
						if(errorGobbler!=null){
							strInfo = strCname + strFname + " ERR 超时！";
							objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, null);
//							errorGobbler.setStop();
							errorGobbler = null;
						}
						break;
					}
				}
				super.strThrflg = null;
				strInfo = strCname + strFname + " 查看镜像 Runcmd01 End----" + DatePro.disGetStrdate4NowObjSdf001();
				objBa.altRunc = disSetInfo(strInfo, objBa.lhpInfobase, objBa.altRunc, ProcessAttrs.strInfoFlg_PRE);
				hmpAll.put(ProcessAttrs.strParmapKey_Runlst, objBa.altRunc);
//				for(int i=0; i<objBa.altRunc.size(); i++){
//					System.out.println(objBa.altRunc.get(i));
//				}
//				System.out.println(hmpAll);
			}
		} catch(Exception ex) {
			if(objBa!=null && objBa.objOutputLogPro!=null){
				objBa.objOutputLogPro.disErrOutputLog(logger, objBa.altRunc, objBa.lhpInfobase, strFname, ex);//disOutputLog(strFname, ex);
			}
		}  finally{
			process = null;
			if(objSaveInfoPro!=null){
				objSaveInfoPro.disSaveInfo_Run(DbInfoSaveAttrs.strSaveFlg_Run);
			}
		}

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
		long lonFlg = System.currentTimeMillis();
		logger.error(strCname + strFnamep + exp + "||" + lonFlg);
		StackTraceElement[] subSte = exp.getStackTrace();
		for(int i=0; i<subSte.length; i++){
			logger.error(
					subSte[i].getClassName() + subSte[i].getMethodName() + ":" + subSte[i].getLineNumber() + "||" + lonFlg );
		}
	}

}
