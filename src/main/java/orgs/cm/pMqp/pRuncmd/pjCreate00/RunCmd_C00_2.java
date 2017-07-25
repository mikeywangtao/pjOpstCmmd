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
				StrCommand = StrCommand.split(",")[Integer.parseInt(strNowRunflg)];
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
创建VM STD line:     "stdout": "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+\r\n| ID                                   | Status    | Name                                 | Size | Volume Type | Bootable | Attached to |\r\n+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+\r\n| 30ff9ce4-290d-47a5-acd4-5073ca1639f0 | available | -                                    | 1    | -           | true     |             |\r\n| 37c50bb0-2538-4ad7-9c89-fdf33c0424b7 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |\r\n| 4dce04ba-2fab-4af7-bc60-44e9e912fc96 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |\r\n| 50041379-58f6-465f-a4c4-7e76fda0ff87 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |\r\n| 7b216755-b341-45f6-90ea-d46e6d9d8724 | available | -                                    | 1    | -           | true     |             |\r\n| aa90b0c2-f9c1-45e8-8201-48db06a01338 | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |\r\n| b38ed2d2-c21f-4331-8930-0fb74884699a | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |\r\n| b4e47dda-2a3c-43ba-bbc8-c80ecde70628 | available | -                                    | 1    | -           | true     |             |\r\n| ddcc7c2d-0c31-450c-b4c2-c6ee53f50e14 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |\r\n+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+\r\n", 
创建VM STD line:     "stdout_lines": [
创建VM STD line:         "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+", 
创建VM STD line:         "| ID                                   | Status    | Name                                 | Size | Volume Type | Bootable | Attached to |", 
创建VM STD line:         "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+", 
创建VM STD line:         "| 30ff9ce4-290d-47a5-acd4-5073ca1639f0 | available | -                                    | 1    | -           | true     |             |", 
创建VM STD line:         "| 37c50bb0-2538-4ad7-9c89-fdf33c0424b7 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |", 
创建VM STD line:         "| 4dce04ba-2fab-4af7-bc60-44e9e912fc96 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |", 
创建VM STD line:         "| 50041379-58f6-465f-a4c4-7e76fda0ff87 | available | dev-c36397e8f4bd491a9360130d89bd2eab | 1    | -           | true     |             |", 
创建VM STD line:         "| 7b216755-b341-45f6-90ea-d46e6d9d8724 | available | -                                    | 1    | -           | true     |             |", 
创建VM STD line:         "| aa90b0c2-f9c1-45e8-8201-48db06a01338 | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |", 
创建VM STD line:         "| b38ed2d2-c21f-4331-8930-0fb74884699a | available | dev-f5b0cea9169e44a8aaebb4238079fb84 | 1    | -           | true     |             |", 
创建VM STD line:         "| b4e47dda-2a3c-43ba-bbc8-c80ecde70628 | available | -                                    | 1    | -           | true     |             |", 
创建VM STD line:         "| ddcc7c2d-0c31-450c-b4c2-c6ee53f50e14 | available | dev-4bdd73081c5f4420946100e2a478d396 | 1    | -           | true     |             |", 
创建VM STD line:         "+--------------------------------------+-----------+--------------------------------------+------+-------------+----------+-------------+"
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
